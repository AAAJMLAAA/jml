package springftl.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.text.StringEscapeUtils;

public class XSSRequestWrapper extends HttpServletRequestWrapper{
	
	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
    // 定义script的正则表达式
    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    // 定义style的正则表达式
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    // 定义HTML标签的正则表达式
    private static final String REGEX_HTML = "<[^>]+>";
    // 定义空格回车换行符
    private static final String REGEX_SPACE = "\\s*|\t|\r|\n";
    //定义所有w标签
    private static final String REGEX_W = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }

        int count = values.length;

        String[] encodedValues = new String[count];

        for (int i = 0; i < count; i++) {
            // 将特殊字符进行转义处理  spring的HtmlUtils  apache StringEscapeUtils
            encodedValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
        }

        return encodedValues;

    }
    
    /**
     * 移除HTML标签
     * @param htmlStr
     * @return
     */
    private static String removeHtml(String htmlStr){
        Pattern p_w = Pattern.compile(REGEX_W, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll(""); // 过滤script标签
        Pattern p_script = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签
        Pattern p_style = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签
        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        Pattern p_space = Pattern.compile(REGEX_SPACE, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        htmlStr = htmlStr.replaceAll(" ", ""); //过滤
        return htmlStr.trim(); // 返回文本字符串
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        
        return removeHtml(value);
    }







}
