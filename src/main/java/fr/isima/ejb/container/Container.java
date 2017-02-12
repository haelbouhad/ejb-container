package fr.isima.ejb.container;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.annotations.Preferred;
import fr.isima.ejb.container.annotations.Singleton;
import fr.isima.ejb.container.annotations.Stateless;



public class Container {
	
	private static Map<String, List<Class<?>> > interfaceToImpl;
	
	private static BeanManager beanManager;
	
	static {
		interfaceToImpl = new HashMap<String, List<Class<?>> >();
		beanManager = (BeanManager) BeanManager.getInstance();
		assignInterfaceToImpl();
    }
	
	public static void inject(Object ctx) {
		
		// get all fields having @Inject annotation
		Set<Field> fields = AnnotationsHelper.getFieldsAnnotatedWith(ctx.getClass(), Inject.class);
		
		for(Field field : fields){
			String fieldInterfaceName = field.getType().getName();
			
			if (interfaceToImpl.containsKey(fieldInterfaceName)) {
				
				List<Class<?>> serviceClass = interfaceToImpl.get(fieldInterfaceName);			
				
				Object bean;
				try {		
					System.out.println("it   : " + fieldInterfaceName);
					System.out.println("impl : " + serviceClass.get(0));
					bean = beanManager.getBeanOfClass(serviceClass.get(0));
					//System.out.println(bean.getClass().getName());
					field.setAccessible(true);
					field.set(ctx, bean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else {
				//throw new NoExistingImplementation(fieldInterfaceName);
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
			for(Class<?> ejbInterface : interfaces){
				List<Class<?>> ejbClasses = interfaceToImpl.get(ejbInterface.getName());
				if(ejbClasses == null){
					ejbClasses = new ArrayList<Class<?>>();
				}
				if(ejbClass.getAnnotation(Preferred.class) != null){
					
					ejbClasses.add(0, ejbClass);
				}else{
					System.out.print("Annotations: " + ejbClass.getAnnotations().length);
					System.out.println(" "+ ejbClass.getName());
					ejbClasses.add(ejbClass);
				}
				interfaceToImpl.put(ejbInterface.getName(), ejbClasses);
			}
				
		}
		
		/*for(Entry<String, List<Class<?>>> interf : interfaceToImpl.entrySet()){
			System.out.println("--- Interface : " + interf.getKey());
			for(Class<?> ejbClass : interf.getValue()){
				System.out.println(ejbClass.getName());
			}
			
		}*/
		
		
	}
	
	

}
