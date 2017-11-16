package com.untzuntz.ustackserverapi.params.types;

import com.untzuntz.ustackserverapi.APIException;
import com.untzuntz.ustackserverapi.params.exceptions.ParamValueException;

/**
 * Validates a string type with a min and max length (optional)
 */
public class StringParam extends BaseParam implements ParameterDefinitionInt<String> {
	
	private Integer minLen;
	private Integer maxLen;

	/**
	 * Setup core string parameter
	 * 
	 * @param n
	 * @param d
	 */
	public StringParam(String n, String d) {
		super(n, d);
	}
	
	/**
	 * Setup a string parameter with a min and max length
	 * @param minLen
	 * @param maxLen
	 */
	public StringParam(String n, String d, Integer minLen, Integer maxLen)
	{
		super(n, d);
		this.minLen = minLen;
		this.maxLen = maxLen;
	}

	/**
	 * Setup a string parameter with just a minimum length
	 * @param minLen
	 */
	public StringParam(String n, String d, Integer minLen)
	{
		super(n, d);
		this.minLen = minLen;
	}
	
	public String getTypeDescription() {
		
		String msg = "";
		if (minLen != null && maxLen == null)
			msg = " at least " + minLen + " chars long";
		else if (minLen == null && maxLen != null)
			msg = " at max " + minLen + " chars long";
		else if (minLen != null && maxLen != null)
			msg = " between " + minLen + " and " + maxLen + " chars long";
		
		return "A string of text" + msg;
	}
	
	public Integer getMinLen() {
		return minLen;
	}

	public void setMinLen(Integer minLen) {
		this.minLen = minLen;
	}

	public Integer getMaxLen() {
		return maxLen;
	}

	public void setMaxLen(Integer maxLen) {
		this.maxLen = maxLen;
	}

	@Override
	public void validate(String data) throws APIException {
		
		if (data == null && minLen != null)
			throw new ParamValueException(this, "String must be at least " + minLen + " characters");

		if (data == null) {
			data = "";
		}

		if (minLen != null && data.length() < minLen)
			throw new ParamValueException(this, "String must be at least " + minLen + " characters");
	
		if (maxLen != null && data.length() > maxLen)
			throw new ParamValueException(this, "String cannot exceed " + maxLen + " characters");
		
	}

	@Override
	public String getValue(String data) {
		
		if (maxLen != null && data != null && data.length() > maxLen)
			data = data.substring(0, maxLen);
		
		return data;
	}

}
