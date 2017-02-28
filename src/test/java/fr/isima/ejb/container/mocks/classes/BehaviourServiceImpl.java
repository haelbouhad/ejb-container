package fr.isima.ejb.container.mocks.classes;

import fr.isima.ejb.container.annotations.Behaviour;
import fr.isima.ejb.container.annotations.Stateless;
import fr.isima.ejb.container.mocks.interfaces.BehaviourService;


@Stateless
@Behaviour({FirstInterceptor.class})
public class BehaviourServiceImpl implements BehaviourService {

}
