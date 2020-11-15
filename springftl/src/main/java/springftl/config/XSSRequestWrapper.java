package springftl.config;
 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StringUtils;
 
 
/**
 * @version V1.0
 * @Title: Request的包装类
 * @ClassName: com.newcapec.cloudpay.filter.XSSRequestWrapper.java
 * @Description: XSSRequestWrapper是Request的包装类, 用于修改Request请求，这是拦截器Interceptor所不能做到的
 * @Copyright 2016-2018  Powered By 研发中心
 * @author: 王延飞
 * @date: 2019-01-25 9:15
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {
 
 
    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }
 
 
    /**
     * @param name
     * @return java.lang.String[]
     * @Title: 对数组参数进行特殊字符过滤
     * @methodName: getParameterValues
     * @Description:
     * @author: 王延飞
     * @date: 2019-01-25 10:23
     */
    @Override
    public String[] getParameterValues(String name) {
 
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = clearXss(values[i]);
        }
        return encodedValues;
    }
 
    /**
     * @param name
     * @return java.lang.String
     * @Title: 对参数中特殊字符进行过滤
     * @methodName: getParameter
     * @Description:
     * @author: 王延飞
     * @date: 2019-01-25 10:23
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value == null) {
            return null;
        }
        return clearXss(value);
    }
 
    /**
     * @Title: 覆盖getParameterMap方法，将参数名和参数值都做xss & sql过滤
     * @methodName:  getParameterMap
     * @param
     * @return java.util.Map
     * @Description: 覆盖getParameterMap方法，将参数名和参数值都做xss & sql过滤
     * 【一般post表单请求，或者前台接收为实体需要这样处理】
     *
     * @author: 王延飞
     * @date:  2019-02-20 11:12
     */
    @Override
    public Map getParameterMap() {
 
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String[]> requestMap = super.getParameterMap();
        Iterator<Entry<String, String[]>> it = requestMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String[]> entry = it.next();
            if(entry.getValue().length==1){
                paramMap.put(xssEncode(entry.getKey()), xssEncode(entry.getValue()[0]));
            } else {
                String[] values = entry.getValue();
                String value = "";
                for(int i=0; i<values.length; i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
                paramMap.put(xssEncode(entry.getKey()), xssEncode(entry.getValue()[0]));
            }
        }
        return paramMap;
    }
 
 
    /**
     * @param name
     * @return java.lang.Object
     * @Title: 获取attribute, 特殊字符过滤
     * @methodName: getAttribute
     * @Description:
     * @author: 王延飞
     * @date: 2019-01-25 10:24
     */
    @Override
    public Object getAttribute(String name) {
        Object value = super.getAttribute(name);
        if (value != null && value instanceof String) {
            clearXss((String) value);
        }
        return value;
    }
 
    /**
     * @Title: 对请求头部进行特殊字符过滤
     * @methodName: getHeader
     * @param name
     * @return java.lang.String
     * @Description:
     *
     * @author: 王延飞
     * @date: 2019-01-25 10:34
     */
    /*@Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return clearXss(value);
    }*/
 
    /**
     * @param value
     * @return java.lang.String
     * @Title: 特殊字符处理（转义或删除）
     * @methodName: clearXss
     * @Description:
     * @author: 王延飞
     * @date: 2019-01-25 9:16
     */
    private static String clearXss(String value) {
 
        if (StringUtils.isEmpty(value)) {
            return value;
        }
 
        return XssFilterUtil.stripXss(value);
    }
 
   
   /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符 在保证不删除数据的情况下保存
     *
     * @param s
     * @return 过滤后的值
     */
    private static String xssEncode(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("<","&lt;");
        value = value.replaceAll(">","&gt;");
        value = value.replaceAll("'","&apos;");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
//        value = value.replaceAll("[<>{}\\[\\];\\&]","");
        return value;
    }
    public static void main(String[] args) {
    	 String clearXss = clearXss("<img src='1.jpg'");
    	 System.out.println(clearXss);
	}
}