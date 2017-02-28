package fr.isima.ejb.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.annotations.Interceptors;
import fr.isima.ejb.container.annotations.PostConstruct;
import fr.isima.ejb.container.annotations.Preferred;
import fr.isima.ejb.container.annotations.Singleton;
import fr.isima.ejb.container.annotations.Stateless;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;




public class Container {
	
	private static Map<String, List<Class<?>> > interfaceToImpl;
	
	private static BeanManager beanManager;
	
	static {
		interfaceToImpl = new HashMap<String, List<Class<?>> >();
		beanManager = (BeanManager) BeanManager.getInstance();
		assignInterfaceToImpl();
    }
	
	public static void inject(Object ctx) throws NoExistingImplementation, MultipleExistingImplementation {
		
		// get all fields having @Inject annotation
		Set<Field> fields = AnnotationsHelper.getFieldsAnnotatedWith(ctx.getClass(), Inject.class);
		
		for(Field field : fields){
			//get object Class
			String fieldInterfaceName = field.getType().getName();
			
			// check to see if we have implementations for the class
			if (interfaceToImpl.containsKey(fieldInterfaceName)) {
				
				// get all implementations for one class
				List<Class<?>> serviceClass = interfaceToImpl.get(fieldInterfaceName);			
				
				int index = 0;
				if(serviceClass.size() >1){
					
					Set<Class<?>> preferedClasses = AnnotationsHelper.getClassesAnnotatedWith(Preferred.class);
					int nbImplementations = 0;

					
					for(int i = 0; i < serviceClass.size(); i++){
						if(preferedClasses.contains(serviceClass.get(i)))
						{
							nbImplementations++;
							index = i;
						}
					}
					if(nbImplementations > 1 || nbImplementations == 0){
						throw new MultipleExistingImplementation(fieldInterfaceName);
					}
					
				}
				
				Object beanProxy = null;
				try {
					
					// get an instance of the class
					beanProxy = EJBHandler.newInstance(serviceClass.get(index), getInterfacesOf(serviceClass.get(index)) );

					// set it to be accessible from the outside
					field.setAccessible(true);
					
					// Inject nested beans -- beanProxy.getHandler().getBeanClass()
					Container.inject(getBean(beanProxy));
					
					//return the instance object to the context that needed it
					field.set(ctx, beanProxy);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else {
				throw new NoExistingImplementation(fieldInterfaceName);
			}
			
		}
		
	}

	
	public static Object getBean(Object proxy){
		return EJBHandler.getBeanOf(proxy);
	}



	private static Class<?>[] getInterfacesOf(Class<?> beanClass) {
		
		return beanClass.getInterfaces();
				
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
			for(Class<?> ejbInterface : interfaces){
				List<Class<?>> ejbClasses = interfaceToImpl.get(ejbInterface.getName());
				if(ejbClasses == null){
					ejbClasses = new ArrayList<Class<?>>();
				}
				if(ejbClass.getAnnotation(Preferred.class) != null){
					
					ejbClasses.add(0, ejbClass);
					
				}else{
					
					ejbClasses.add(ejbClass);
				}
				interfaceToImpl.put(ejbInterface.getName(), ejbClasses);
			}
				
		}
		
		
	}


	public static void invokePostConstructOf(Object bean, Class<?> beanClass) {
		
		if(AnnotationsHelper.isAnnotatedWith(beanClass, Interceptors.class)){

			Interceptors interceptors = beanClass.getAnnotation(Interceptors.class);
			
			for(Class<?> interceptor : interceptors.value() ){
					
				Set<Method> methods = AnnotationsHelper.getMethodsAnnotatedWith(interceptor, PostConstruct.class);
				
				for(Method method : methods){
					
					
						
						//method.invoke(bean, new Object[]{});
						

					
				}
				
			}
			
		}
		
	}
	
	

}
