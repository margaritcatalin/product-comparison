/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.comparison.jalo;

import de.hybris.platform.core.Registry;
import de.hybris.platform.util.JspContext;

import java.util.Map;

import org.apache.log4j.Logger;

import com.comparison.constants.ProductcomparisonConstants;


/**
 * This is the extension manager of the Productcomparison extension.
 */
public class ProductcomparisonManager extends GeneratedProductcomparisonManager
{
	/** Edit the local|project.properties to change logging behavior (properties 'log4j.*'). */
	private static final Logger LOG = Logger.getLogger(ProductcomparisonManager.class.getName());

	/*
	 * Some important tips for development:
	 * 
	 * Do NEVER use the default constructor of manager's or items. => If you want to do something whenever the manger is
	 * created use the init() or destroy() methods described below
	 * 
	 * Do NEVER use STATIC fields in your manager or items! => If you want to cache anything in a "static" way, use an
	 * instance variable in your manager, the manager is created only once in the lifetime of a "deployment" or tenant.
	 */


    /**
     * Get the valid instance of this manager.
     *
     * @return the current instance of this manager
     */
    public static ProductcomparisonManager getInstance()
	{
		return (ProductcomparisonManager) Registry.getCurrentTenant().getJaloConnection().getExtensionManager().getExtension(
				ProductcomparisonConstants.EXTENSIONNAME);
	}


    /**
     * Never call the constructor of any manager directly, call getInstance() You can place your business logic here -
     * like registering a jalo session listener. Each manager is created once for each tenant.
     */
    public ProductcomparisonManager() 
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("constructor of ProductcomparisonManager called.");
		}
	}

    /**
     * Use this method to do some basic work only ONCE in the lifetime of a tenant resp. "deployment". This method is
     * called after manager creation (for example within startup of a tenant). Note that if you have more than one tenant
     * you have a manager instance for each tenant.
     */
    @Override
	public void init()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("init() of ProductcomparisonManager called. " + getTenant().getTenantID());
		}
	}

    /**
     * Use this method as a callback when the manager instance is being destroyed (this happens before system
     * initialization, at redeployment or if you shutdown your VM). Note that if you have more than one tenant you have a
     * manager instance for each tenant.
     */
    @Override
	public void destroy()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("destroy() of ProductcomparisonManager called, current tenant: " + getTenant().getTenantID());
		}
	}

    /**
     * Implement this method to create initial objects. This method will be called by system creator during
     * initialization and system update. Be sure that this method can be called repeatedly.
     * <p>
     * An example usage of this method is to create required cronjobs or modifying the type system (setting e.g some
     * default values)
     *
     * @param params the parameters provided by user for creation of objects for the extension
     * @param jspc   the jsp context; you can use it to write progress information to the jsp page during creation
     */
    @Override
	public void createEssentialData(final Map<String, String> params, final JspContext jspc)
	{
		// implement here code creating essential data
	}

    /**
     * Implement this method to create data that is used in your project. This method will be called during the system
     * initialization.
     * <p>
     * An example use is to import initial data like currencies or languages for your project from an csv file.
     *
     * @param params the parameters provided by user for creation of objects for the extension
     * @param jspc   the jsp context; you can use it to write progress information to the jsp page during creation
     */
    @Override
	public void createProjectData(final Map<String, String> params, final JspContext jspc)
	{
		// implement here code creating project data
	}
}
