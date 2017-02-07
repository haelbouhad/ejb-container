package fr.isima.ejb.container;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.annotations.Stateless;


public class Container {
	
	private static Map<String, Class<?>> interfaceToImpl;
	
	static {
		interfaceToImpl = new HashMap<String, Class<?>>();
		assignInterfaceToImpl();
    }
	
	public static void inject(Object ctx){
		
		// get all fields having @Inject annotation
		Set<Field> fields = AnnotationsHelper.getFieldsAnnotatedWith(ctx.getClass(), Inject.class);
		
		for(Field field : fields){
			String fieldInterfaceName = field.getType().getName();
			if (interfaceToImpl.containsKey(fieldInterfaceName)) {
				Class<?> serviceClass = interfaceToImpl.get(fieldInterfaceName);			
				
				
				Object beanProxy;
				try {
					//beanProxy = Proxy.newProxyInstance(serviceClass.getClassLoader(), getProxyInterfacesOf(serviceClass), new EJBHandler());
					beanProxy = serviceClass.newInstance();
					field.setAccessible(true);
					field.set(ctx, beanProxy);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}
		
	}

	private static void assignInterfaceToImpl() {
		
		Set<Class<?>> statelessClasses = AnnotationsHelper.getClassesAnnotatedWith(Stateless.class);
		
		for(Class<?> ejbClass : statelessClasses){
			Class<?> interfaces[] = ejbClass.getInterfaces();
			for(Class<?> ejbInterface : interfaces)
				interfaceToImpl.put(ejbInterface.getName(), ejbClass);
		}
		
		
	}
	
	private static Class<?>[] getProxyInterfacesOf(Class<?> beanClass){
		Class<?>[] ints = beanClass.getInterfaces();
		Class<?>[] res = new Class<?>[ints.length + 1];
		for(int i = 0; i < ints.length; i++)
			res[i] = ints[i];
		res[ints.length] = ProxyInterface.class;
		return res;
	}
	
	

}
