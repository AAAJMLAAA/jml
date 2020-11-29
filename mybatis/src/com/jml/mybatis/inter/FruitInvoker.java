package com.jml.mybatis.inter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class FruitInvoker {
	
	  public <T> Object getInstance(Class<?> cls,T t){        
	        MethodProxy invocationHandler = new MethodProxy(t);        
	        Object newProxyInstance = Proxy.newProxyInstance(  
	                cls.getClassLoader(),  
	                new Class[] { cls }, 
	                (InvocationHandler) invocationHandler); 
	        return (Object)newProxyInstance;
	    }
}
