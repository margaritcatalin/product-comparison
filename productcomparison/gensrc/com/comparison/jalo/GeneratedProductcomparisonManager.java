/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 26, 2022, 4:12:06 PM                    ---
 * ----------------------------------------------------------------
 */
package com.comparison.jalo;

import com.comparison.constants.ProductcomparisonConstants;
import com.comparison.jalo.actions.AddToCartAction;
import com.comparison.jalo.actions.ListAddToCompareAction;
import com.comparison.jalo.cms.ProductComparisonComponent;
import com.comparison.jalo.cms.ProductComparisonInfoComponent;
import com.comparison.jalo.config.ProductComparisonProperty;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>ProductcomparisonManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedProductcomparisonManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public AddToCartAction createAddToCompareAction(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductcomparisonConstants.TC.ADDTOCOMPAREACTION );
			return (AddToCartAction)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating AddToCompareAction : "+e.getMessage(), 0 );
		}
	}
	
	public AddToCartAction createAddToCompareAction(final Map attributeValues)
	{
		return createAddToCompareAction( getSession().getSessionContext(), attributeValues );
	}
	
	public ListAddToCompareAction createListAddToCompareAction(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductcomparisonConstants.TC.LISTADDTOCOMPAREACTION );
			return (ListAddToCompareAction)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ListAddToCompareAction : "+e.getMessage(), 0 );
		}
	}
	
	public ListAddToCompareAction createListAddToCompareAction(final Map attributeValues)
	{
		return createListAddToCompareAction( getSession().getSessionContext(), attributeValues );
	}
	
	public ProductComparisonComponent createProductComparisonComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductcomparisonConstants.TC.PRODUCTCOMPARISONCOMPONENT );
			return (ProductComparisonComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductComparisonComponent : "+e.getMessage(), 0 );
		}
	}
	
	public ProductComparisonComponent createProductComparisonComponent(final Map attributeValues)
	{
		return createProductComparisonComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public ProductComparisonInfoComponent createProductComparisonInfoComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductcomparisonConstants.TC.PRODUCTCOMPARISONINFOCOMPONENT );
			return (ProductComparisonInfoComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductComparisonInfoComponent : "+e.getMessage(), 0 );
		}
	}
	
	public ProductComparisonInfoComponent createProductComparisonInfoComponent(final Map attributeValues)
	{
		return createProductComparisonInfoComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public ProductComparisonProperty createProductComparisonProperty(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductcomparisonConstants.TC.PRODUCTCOMPARISONPROPERTY );
			return (ProductComparisonProperty)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductComparisonProperty : "+e.getMessage(), 0 );
		}
	}
	
	public ProductComparisonProperty createProductComparisonProperty(final Map attributeValues)
	{
		return createProductComparisonProperty( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return ProductcomparisonConstants.EXTENSIONNAME;
	}
	
}
