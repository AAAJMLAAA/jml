package com.jml.mybatis.inter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodProxy implements InvocationHandler {
	// 传如的实现类的对象
	private Object  object;
	
	public <T> MethodProxy(T t)
	{
		this.object = t;
	}
	
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  throws Throwable {        
        // 该类已有的实现类，走自己的实现了
    	if (this.object != null)
          {
        	  return method.invoke(this.object, args);
          }else
          {
        	 return run(method,args); 
          }
    }
    
    /**
     * 实现接口的核心方法 
     * @param method
     * @param args
     * @return
     */
    public Object run(Method method,Object[] args){  
    	String msg = "method call success!";
    	if (args != null)
    	{
    		for (Object object : args)
        	{
        		// 进行业务处理
        		msg = msg + object;
        	}
    	}
    	
       return msg;
    }  

}