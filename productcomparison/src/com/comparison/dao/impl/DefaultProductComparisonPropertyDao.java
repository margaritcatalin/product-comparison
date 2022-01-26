package com.comparison.dao.impl;

import com.comparison.dao.ProductComparisonPropertyDao;
import com.comparison.model.config.ProductComparisonPropertyModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

/**
 * The type Default product comparison property dao.
 *
 * @author Catalin Margarit
 */
public class DefaultProductComparisonPropertyDao extends DefaultGenericDao<ProductComparisonPropertyModel> implements ProductComparisonPropertyDao {
    /**
     * Instantiates a new Default product comparison property dao.
     */
    public DefaultProductComparisonPropertyDao() {
        super(ProductComparisonPropertyModel._TYPECODE);
    }
}
