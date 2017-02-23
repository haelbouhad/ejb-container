package fr.isima.ejb.container.annotations;

import fr.isima.ejb.container.interceptors.LogInterceptor;

@Behaviour(interceptor=LogInterceptor.class)
public @interface Log {

}



