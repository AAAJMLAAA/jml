package com.jml.mybatis.inter;

public class InterTest {
	public static void main(String[] args) {
		// 代理
		Fruit invoker=(Fruit)new FruitInvoker().getInstance(Fruit.class,null);
		System.out.println(invoker.test());
		System.out.println(invoker.test("jml"));
		System.out.println(invoker.test("jml","123"));
		
		Fruit2 invoker2=(Fruit2)new FruitInvoker().getInstance(Fruit2.class,new Fruit2Impl());
		System.out.println(invoker2.test());
		System.out.println(invoker2.test("jml"));
		System.out.println(invoker2.test("jml","123"));
		
/*		Fruit2 fruit2 = (Fruit2) Proxy.newProxyInstance(  
				  Fruit2.class.getClassLoader(),  
				  new Class[] {Fruit2.class},
				  new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							
							System.out.println(method.getDeclaringClass());
							Object object = method.invoke(new Fruit2Impl(), args);
							return object;
						}
						
					}); 
		  
		System.out.println(fruit2.test());
		System.out.println(fruit2.test("jml"));
		System.out.println(fruit2.test("jml","123"));*/
	}
}
