package fr.isima.ejb.container.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import fr.isima.ejb.container.interceptors.LogInterceptor;


public @interface Behaviour {

	Class<LogInterceptor> interceptor();

}
