/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 26, 2022, 4:12:06 PM                    ---
 * ----------------------------------------------------------------
 */
package com.comparison.jalo.config;

import com.comparison.constants.ProductcomparisonConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.comparison.jalo.config.ProductComparisonProperty ProductComparisonProperty}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedProductComparisonProperty extends GenericItem
{
	/** Qualifier of the <code>ProductComparisonProperty.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>ProductComparisonProperty.fieldValueProvider</code> attribute **/
	public static final String FIELDVALUEPROVIDER = "fieldValueProvider";
	/** Qualifier of the <code>ProductComparisonProperty.fieldNameProvider</code> attribute **/
	public static final String FIELDNAMEPROVIDER = "fieldNameProvider";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(FIELDVALUEPROVIDER, AttributeMode.INITIAL);
		tmp.put(FIELDNAMEPROVIDER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductComparisonProperty.fieldNameProvider</code> attribute.
	 * @return the fieldNameProvider - fieldNameProvider used by this property
	 */
	public String getFieldNameProvider(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FIELDNAMEPROVIDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductComparisonProperty.fieldNameProvider</code> attribute.
	 * @return the fieldNameProvider - fieldNameProvider used by this property
	 */
	public String getFieldNameProvider()
	{
		return getFieldNameProvider( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductComparisonProperty.fieldNameProvider</code> attribute. 
	 * @param value the fieldNameProvider - fieldNameProvider used by this property
	 */
	public void setFieldNameProvider(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FIELDNAMEPROVIDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductComparisonProperty.fieldNameProvider</code> attribute. 
	 * @param value the fieldNameProvider - fieldNameProvider used by this property
	 */
	public void setFieldNameProvider(final String value)
	{
		setFieldNameProvider( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductComparisonProperty.fieldValueProvider</code> attribute.
	 * @return the fieldValueProvider - fieldValueProvider used by this property
	 */
	public String getFieldValueProvider(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FIELDVALUEPROVIDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductComparisonProperty.fieldValueProvider</code> attribute.
	 * @return the fieldValueProvider - fieldValueProvider used by this property
	 */
	public String getFieldValueProvider()
	{
		return getFieldValueProvider( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductComparisonProperty.fieldValueProvider</code> attribute. 
	 * @param value the fieldValueProvider - fieldValueProvider used by this property
	 */
	public void setFieldValueProvider(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FIELDVALUEPROVIDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductComparisonProperty.fieldValueProvider</code> attribute. 
	 * @param value the fieldValueProvider - fieldValueProvider used by this property
	 */
	public void setFieldValueProvider(final String value)
	{
		setFieldValueProvider( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductComparisonProperty.name</code> attribute.
	 * @return the name - name of the property
	 */
	public String getName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductComparisonProperty.name</code> attribute.
	 * @return the name - name of the property
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductComparisonProperty.name</code> attribute. 
	 * @param value the name - name of the property
	 */
	protected void setName(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		// initial-only attribute: make sure this attribute can be set during item creation only
		if ( ctx.getAttribute( "core.types.creation.initial") != Boolean.TRUE )
		{
			throw new JaloInvalidParameterException( "attribute '"+NAME+"' is not changeable", 0 );
		}
		setProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductComparisonProperty.name</code> attribute. 
	 * @param value the name - name of the property
	 */
	protected void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
}
