package springftl.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class RequestXssFilter implements Filter{

	/**
	 * @ServletComponentScan
	 * //@Order(1)
	 * @Slf4j
	 * @WebFilter(urlPatterns = "/*", filterName = "reqResFilter")
	 *  @WebFilter + @ServletComponentScan 注解
	 *  这种是最原始的不建议采用
	 *  还是使用注解类的
	 */
	@Override
	public void destroy() {
		System.out.println("destroy===================");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest hrequest = (HttpServletRequest)request;
		XSSRequestWrapper xSSRequestWrapper = new XSSRequestWrapper(hrequest);
        HttpServletResponseWrapper wrapperResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
		// 判断是否是白名单
        if(hrequest.getRequestURI().indexOf("/test") != -1 || hrequest.getRequestURI().indexOf("/check") != -1) {
        	// 进行参数转义
        	filterChain.doFilter(xSSRequestWrapper,response);
        }else
        {
        	wrapperResponse.sendRedirect("/controller/check");
        }

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init===================");
	}

}
