ACC.productcomparison = {

    _autoload: [
        ["bindProductComparisonButton", $(".js-add-to-compare").length > 0],
        ["bindClearProductComparisonButton", $(".js-add-to-compare").length > 0],
        ["bindRemoveProductComparisonButton", $(".js-add-to-compare").length > 0],
        ["initProductComparisonCheckbox", $(".js-add-to-compare").length > 0],
        ["bindProductComparisonHideDiffButton", $(".js-compare-hide-difference").length > 0],

    ],
    bindProductComparisonHideDiffButton: function () {
        $(".js-compare-hide-difference").on("click", function (e) {
            if ($(this).data("action-type") == "hide") {
                $(".difference-column").each(function () {
                    $(this).removeClass("table-compare-diff");
                });
                $(this).removeClass("btn-active-highlight");
                $(this).addClass("btn-default-highlight");
                $(this).data("action-type", "show");
            } else {
                $(".difference-column").each(function () {
                    $(this).addClass("table-compare-diff");
                });
                $(this).addClass("btn-active-highlight");
                $(this).removeClass("btn-default-highlight");
                $(this).data("action-type", "hide");
            }
        });
    },
    initButtons: function () {
        ACC.productcomparison.bindRemoveProductComparisonButton();
        ACC.productcomparison.bindClearProductComparisonButton();
    },
    initProductComparisonCheckbox: function () {
        $(document).ready(function () {
            $.ajax({
                url: ACC.config.encodedContextPath + "/product-comparison/comparison-items",
                type: "GET",
                success: function (data) {
                    $.each(data, function (index, code) {
                        var compareButtonCode = "#compare-button-" + code;
                        if ($(compareButtonCode).length != 0) {
                            $(compareButtonCode).prop('checked', true);
                        }
                    });
                },
                error: function (jqXHR, textStatus) {
                    alert(
                        "Failed to switch sold to. Error details [" +
                        textStatus +
                        "]"
                    );
                },
            });
        });
    },
    bindProductComparisonButton: function () {
        $(".js-add-to-compare").on("click", function (e) {
            var productCode = $(this).data("product");
            var compareButtonCode = "#compare-button-" + productCode;
            if ($(compareButtonCode).length != 0) {
                var currentValue = $(compareButtonCode).is(":checked");
                if (currentValue) {
                    $(compareButtonCode).prop('checked', true);
                } else {
                    $(compareButtonCode).prop('checked', false);
                }
            }
            $.ajax({
                url: ACC.config.encodedContextPath + "/product-comparison/add",
                type: "POST",
                data: {code: productCode},
                success: function (data) {
                    ACC.productcomparison.constructCompareComponentElements(data);
                    if (data.compareItems.length > 0 && $("#comparison-bar").hasClass("hidden")) {
                        $("#comparison-bar").removeClass("hidden");
                    }
                    if (data.compareItems.length > 1 && $("#compare-submit").hasClass("disabled")) {
                        $("#compare-submit").removeClass("disabled");
                    }
                    if (data.compareItems.length == 0) {
                        $("#comparison-bar").addClass("hidden");
                    }
                    if (data.compareItems.length <= 1 && !$("#compare-submit").hasClass("disabled")) {
                        $("#compare-submit").addClass("disabled");
                    }
                },
                error: function (jqXHR, textStatus) {
                    alert(
                        "Failed to switch sold to. Error details [" +
                        textStatus +
                        "]"
                    );
                },
            });
        });
    },
    bindRemoveProductComparisonButton: function () {
        $(".js-remove-compare-item").on("click", function (e) {
            var productCode = $(this).data("product");
            $.ajax({
                url: ACC.config.encodedContextPath + "/product-comparison/remove",
                type: "POST",
                data: {code: productCode},
                success: function (result) {
                    var compareButtonCode = "#compare-button-" + productCode;
                    if ($(compareButtonCode).length != 0) {
                        var currentValue = $(compareButtonCode).is(":checked");
                        if (currentValue) {
                            $(compareButtonCode).prop('checked', false);
                        }
                    }
                    ACC.productcomparison.constructCompareComponentElements(result);
                    if (result.compareItems.length <= 1 && !$("#compare-submit").hasClass("disabled")) {
                        $("#compare-submit").addClass("disabled");
                    }
                    if (result.compareItems.length == 0) {
                        $("#comparison-bar").addClass("hidden");
                    }
                },
                error: function (jqXHR, textStatus) {
                    alert(
                        "Failed to switch sold to. Error details [" +
                        textStatus +
                        "]"
                    );
                },
            });
        });
    },
    bindClearProductComparisonButton: function () {
        $(".js-clear-compare-items").on("click", function (e) {
            $.ajax({
                url: ACC.config.encodedContextPath + "/product-comparison/comparison-items",
                type: "GET",
                success: function (data) {
                    $.each(data, function (index, code) {
                        var compareButtonCode = "#compare-button-" + code;
                        if ($(compareButtonCode).length != 0) {
                            $(compareButtonCode).prop('checked', false);
                        }
                    });
                    $.ajax({
                        url: ACC.config.encodedContextPath + "/product-comparison/clear",
                        type: "POST",
                        success: function (data) {
                            ACC.productcomparison.constructCompareComponentElements(data);
                            $("#compare-submit").addClass("disabled");
                            $("#comparison-bar").addClass("hidden");
                        },
                        error: function (jqXHR, textStatus) {
                            alert(
                                "Failed to switch sold to. Error details [" +
                                textStatus +
                                "]"
                            );
                        },
                    });
                },
                error: function (jqXHR, textStatus) {
                    alert(
                        "Failed to switch sold to. Error details [" +
                        textStatus +
                        "]"
                    );
                },
            });
        });
    },
    constructCompareComponentElements: function (result) {
        $("#compare-elements").html($('#compare-element-li').tmpl(result.compareItems));
        ACC.productcomparison.initButtons();
    },
};