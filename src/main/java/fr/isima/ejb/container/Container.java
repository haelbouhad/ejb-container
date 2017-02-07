package fr.isima.ejb.container;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.annotations.Singleton;
import fr.isima.ejb.container.annotations.Stateless;


public class Container {
	
	private static Map<String, Class<?>> interfaceToImpl;
	
	private static BeanManager beanManager;
	
	static {
		interfaceToImpl = new HashMap<String, Class<?>>();
		beanManager = (BeanManager) BeanManager.getInstance();
		assignInterfaceToImpl();
    }
	
	public static void inject(Object ctx){
		
		// get all fields having @Inject annotation
		Set<Field> fields = AnnotationsHelper.getFieldsAnnotatedWith(ctx.getClass(), Inject.class);
		
		for(Field field : fields){
			String fieldInterfaceName = field.getType().getName();
			
			if (interfaceToImpl.containsKey(fieldInterfaceName)) {
				
				Class<?> serviceClass = interfaceToImpl.get(fieldInterfaceName);			
				
				Object bean;
				try {					
					bean = beanManager.getBeanOfClass(serviceClass);
					System.out.println(bean.getClass().getName());
					field.setAccessible(true);
					field.set(ctx, bean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}
		
	}

	private static void assignInterfaceToImpl() {
		
		Set<Class<?>> statelessClasses = AnnotationsHelper.getClassesAnnotatedWith(Stateless.class);
		Set<Class<?>> singletonClasses = AnnotationsHelper.getClassesAnnotatedWith(Singleton.class);
		
		for(Class<?> ejbClass : statelessClasses)
			beanManager.addStatelessClass(ejbClass);
		for(Class<?> ejbClass : singletonClasses)
			beanManager.addSingletonClass(ejbClass);
		
		statelessClasses.addAll(singletonClasses);
		for(Class<?> ejbClass : statelessClasses){
			Class<?> interfaces[] = ejbClass.getInterfaces();
			for(Class<?> ejbInterface : interfaces)
				interfaceToImpl.put(ejbInterface.getName(), ejbClass);
		}
		
		
	}
	
	

}
