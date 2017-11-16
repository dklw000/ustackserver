package com.untzuntz.ustackserverapi.params;

import com.untzuntz.ustackserverapi.APIException;
import com.untzuntz.ustackserverapi.CallParameters;
import com.untzuntz.ustackserverapi.params.types.ParameterDefinitionInt;
import com.untzuntz.ustackserverapi.version.VersionInt;

/**
 * An API call parameter definition
 * 
 * @author jdanner
 *
 */
public class APICallParam implements Validated {

	private ParameterDefinitionInt<?> paramDetails;
	private String defaultValue;
	private VersionInt since;
	private String desc;

	public void setParamDetails(ParameterDefinitionInt<?> paramDetails) {
		this.paramDetails = paramDetails;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public VersionInt getSince() {
		return since;
	}

	public void setSince(VersionInt since) {
		this.since = since;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public APICallParam(ParameterDefinitionInt<?> param)
	{
		this.paramDetails = param;
	}
	
	public APICallParam(ParameterDefinitionInt<?> param, String defaultValue, String desc)
	{
		this.paramDetails = param;
		this.defaultValue = defaultValue;
		this.desc = desc;
	}
	
	public APICallParam(ParameterDefinitionInt<?> param, String defaultValue)
	{
		this.paramDetails = param;
		this.defaultValue = defaultValue;
	}
	
	public APICallParam(ParameterDefinitionInt<?> param, VersionInt since)
	{
		this.paramDetails = param;
		this.since = since;
	}
	
	public ParameterDefinitionInt<?> getParamDetails() {
		return paramDetails;
	}
	
	public VersionInt getVersion() {
		return since;
	}
	
	public APICallParam setDescription(String desc) {
		this.desc = desc;
		return this;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public String getDescription() {
		if (desc != null)
			return desc;
		
		return paramDetails.getDescription();
	}
	
	@Override
	public void validate(CallParameters data) throws APIException {
		paramDetails.validate(data.getParameter(paramDetails.getName()));
	}

}
