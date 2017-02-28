package fr.isima.ejb.container.mocks.classes;

import fr.isima.ejb.container.annotations.Interceptors;
import fr.isima.ejb.container.annotations.Stateless;
import fr.isima.ejb.container.mocks.interfaces.InterceptedService;


@Stateless
@Interceptors({FirstInterceptor.class})
public class InterceptedServiceImpl implements InterceptedService {

}
