package com.comparison.jalo.config;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import org.apache.log4j.Logger;

/**
 * The type Product comparison property.
 */
public class ProductComparisonProperty extends GeneratedProductComparisonProperty
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger( ProductComparisonProperty.class.getName() );

    /**
     * Create item item.
     *
     * @param ctx           the ctx
     * @param type          the type
     * @param allAttributes the all attributes
     * @return the item
     * @throws JaloBusinessException the jalo business exception
     */
    @Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem( ctx, type, allAttributes );
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}
	
}
