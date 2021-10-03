package com.filiaiev.spring.mvc1.service.impl;

import com.filiaiev.spring.mvc1.dto.order.factory.ClientOrderDtoFactory;
import com.filiaiev.spring.mvc1.dto.order.factory.ManagerOrderDtoFactory;
import com.filiaiev.spring.mvc1.dto.order.factory.OrderDtoFactory;
import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.model.User;
import com.filiaiev.spring.mvc1.model.UserDetailsImpl;
import com.filiaiev.spring.mvc1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByLogin(login);

        // TODO: rewrite this awful bullshit
        if(!user.isPresent())
            throw new UsernameNotFoundException("Wrong login");

//        try{
//            Role role = user.get().getRole();
//            OrderDtoFactory dtoFactory = null;
//            switch (role){
//                case ROLE_CLIENT:
//                    dtoFactory = new ClientOrderDtoFactory();
//                    break;
//                case ROLE_MANAGER:
//                    dtoFactory = new ManagerOrderDtoFactory();
//                    break;
//            }
//
//            GenericBeanDefinition bd = new GenericBeanDefinition();
//            bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
//            bd.setScope("session");
//            bd.setBeanClassName("dtoFactory");
//            bd.setBeanClass(dtoFactory.getClass());
//
//            beanFactory.registerBeanDefinition("dtoFactory", bd);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

//
//        BeanDefinitionRegistry registry =
//                (BeanDefinitionRegistry)applicationContext.getAutowireCapableBeanFactory();
//
//        GenericBeanDefinition gbd = new GenericBeanDefinition();
//        gbd.setBeanClassName(dtoFactory.getClass().getName());
//        gbd.setScope("singleton");
//        gbd.setAutowireCandidate(true);
//        gbd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
//        registry.registerBeanDefinition("dtoFactory", gbd);
//
//        System.out.println("=========================");
//        System.out.println("registering bean " + dtoFactory.getClass().getSimpleName());
//        System.out.println("=========================");

        return new UserDetailsImpl(user.get());
    }

    private void destroyMethod() {
        System.out.println("Bean destroy");
    }
}
