package com.suchness.realscene.common.utils;



import com.suchness.realscene.common.exception.ToolBoxException;

import java.math.BigDecimal;


/**
 * 类型转换器
 * 
 * @author xiaoleilu
 * 
 */
public class Convert {
	

	private Convert() {
		// 静态类不可实例化
	}



	/**
	 * 转换为字符串<br>
	 * 如果给定的值为null，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static String toStr(Object value, String defaultValue) {
		if (null == value) {
			return defaultValue;
		}
		if (value instanceof String) {
			return (String) value;
		} else if (CollectionKit.isArray(value)) {
			return CollectionKit.toString(value);
		}
		return value.toString();
	}

	/**
	 * 转换为字符串<br>
	 * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static String toStr(Object value)
	{
		return toStr(value, null);
	}


	/**
	 * 转换为int<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Integer toInt(Object value, Integer defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Integer) {
			return (Integer) value;
		}
		if (value instanceof Number) {
			return ((Number) value).intValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isEmpty(valueStr)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 转换为int<br>
	 * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static Integer toInt(Object value)
	{
		return toInt(value, null);
	}
	
	/**
	 * 转换为Integer数组<br>
	 * 
	 * @param str 被转换的值
	 * @return 结果
	 */
	public static Integer[] toIntArray(String str) {
		return toIntArray(",", str);
	}
	
	/**
	 * 转换为Integer数组<br>
	 * 
	 * @param split 分隔符
	 * @param split 被转换的值
	 * @return 结果
	 */
	public static Integer[] toIntArray(String split, String str) {
		if (StringUtil.isEmpty(str)) {
			return new Integer[] {};
		}
		String[] arr = str.split(split);
		final Integer[] ints = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			final Integer v = toInt(arr[i], 0);
			ints[i] = v;
		}
		return ints;
	}
	public static Long[] toLongArray(String split, String str) {
		if (StringUtil.isEmpty(str)) {
			return new Long[] {};
		}
		String[] arr = str.split(split);
		final Long[] ints = new Long[arr.length];
		for (int i = 0; i < arr.length; i++) {
			final Long v = StringUtil.isNotEmpty(arr[i])?Long.valueOf(arr[i]):0L;
			ints[i] = v;
		}
		return ints;
	}

	
	/**
	 * 转换为String数组<br>
	 * 
	 * @param split 分隔符
	 * @param split 被转换的值
	 * @return 结果
	 */
	public static String[] toStrArray(String split, String str) {
		return str.split(split);
	}

	/**
	 * 转换为long<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Long toLong(Object value, Long defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Long) {
			return (Long) value;
		}
		if (value instanceof Number) {
			return ((Number) value).longValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isEmpty(valueStr)) {
			return defaultValue;
		}
		try {
			// 支持科学计数法
			return new BigDecimal(valueStr.trim()).longValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 转换为long<br>
	 * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static Long toLong(Object value)
	{
		return toLong(value, null);
	}


	/**
	 * 转换为Long数组<br>
	 * 
	 * @param isIgnoreConvertError 是否忽略转换错误，忽略则给值null
	 * @param values 被转换的值
	 * @return 结果
	 */
	public static Long[] toLongArray(boolean isIgnoreConvertError, Object... values) {
		if (CollectionKit.isEmpty(values)) {
			return new Long[] {};
		}
		final Long[] longs = new Long[values.length];
		for (int i = 0; i < values.length; i++) {
			final Long v = toLong(values[i], null);
			if (null == v && isIgnoreConvertError == false) {
				throw new ToolBoxException(StringUtil.format("Convert [{}] to Long error!", values[i]));
			}
			longs[i] = v;
		}
		return longs;
	}

	/**
	 * 转换为double<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Double toDouble(Object value, Double defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}
		if (value instanceof Double)
		{
			return (Double) value;
		}
		if (value instanceof Number)
		{
			return ((Number) value).doubleValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isEmpty(valueStr))
		{
			return defaultValue;
		}
		try
		{
			// 支持科学计数法
			return new BigDecimal(valueStr.trim()).doubleValue();
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 转换为double<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static Double toDouble(Object value)
	{
		return toDouble(value, null);
	}

	/**
	 * 转换为Float<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Float toFloat(Object value, Float defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}
		if (value instanceof Float)
		{
			return (Float) value;
		}
		if (value instanceof Number)
		{
			return ((Number) value).floatValue();
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isEmpty(valueStr))
		{
			return defaultValue;
		}
		try
		{
			return Float.parseFloat(valueStr.trim());
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 转换为Float<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static Float toFloat(Object value)
	{
		return toFloat(value, null);
	}

	/**
	 * 转换为boolean<br>
	 * String支持的值为：true、false、yes、ok、no，1,0 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Boolean toBool(Object value, Boolean defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}
		if (value instanceof Boolean)
		{
			return (Boolean) value;
		}
		String valueStr = toStr(value, null);
		if (StringUtil.isEmpty(valueStr))
		{
			return defaultValue;
		}
		valueStr = valueStr.trim().toLowerCase();
		switch (valueStr)
		{
			case "true":
				return true;
			case "false":
				return false;
			case "yes":
				return true;
			case "ok":
				return true;
			case "no":
				return false;
			case "1":
				return true;
			case "0":
				return false;
			default:
				return defaultValue;
		}
	}

	/**
	 * 转换为boolean<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static Boolean toBool(Object value)
	{
		return toBool(value, null);
	}


	/**
	 * 转换为BigDecimal<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static BigDecimal toBigDecimal(Object value)
	{
		return toBigDecimal(value, null);
	}
	/**
	 * 转换为BigDecimal<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 *
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}
		if (value instanceof BigDecimal)
		{
			return (BigDecimal) value;
		}
		if (value instanceof Long)
		{
			return new BigDecimal((Long) value);
		}
		if (value instanceof Double)
		{
			return new BigDecimal((Double) value);
		}
		if (value instanceof Integer)
		{
			return new BigDecimal((Integer) value);
		}
		final String valueStr = toStr(value, null);
		if (StringUtil.isEmpty(valueStr))
		{
			return defaultValue;
		}
		try
		{
			return new BigDecimal(valueStr);
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}
}
