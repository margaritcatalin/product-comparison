package com.comparison.dao.impl;

import com.comparison.dao.ProductComparisonPropertyDao;
import com.comparison.model.config.ProductComparisonPropertyModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

/**
 * @author Catalin Margarit
 */
public class DefaultProductComparisonPropertyDao extends DefaultGenericDao<ProductComparisonPropertyModel> implements ProductComparisonPropertyDao {
    public DefaultProductComparisonPropertyDao() {
        super(ProductComparisonPropertyModel._TYPECODE);
    }
}
