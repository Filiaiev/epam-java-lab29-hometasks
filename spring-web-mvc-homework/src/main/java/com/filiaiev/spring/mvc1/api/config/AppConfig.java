package com.filiaiev.spring.mvc1.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.filiaiev.spring.mvc1.dto.order.factory.ClientOrderDtoFactory;
import com.filiaiev.spring.mvc1.dto.order.factory.ManagerOrderDtoFactory;
import com.filiaiev.spring.mvc1.dto.order.factory.OrderDtoFactory;
import com.filiaiev.spring.mvc1.dto.order.factory.RepairerOrderDtoFactory;
import com.filiaiev.spring.mvc1.interceptor.LogInterceptor;
import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.model.User;
import com.filiaiev.spring.mvc1.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.sql.Driver;
import java.text.DateFormat;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName("org.h2.Driver"));
        dataSource.setUrl("jdbc:h2:mem:");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("uk", "UA"));
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.MEDIUM,
                new Locale("UK", "ua")));
        return objectMapper;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource rrbm = new ReloadableResourceBundleMessageSource();
        rrbm.setBasename("classpath:messages");
        rrbm.setDefaultEncoding("WINDOWS-1251");
        return rrbm;
    }

    @Bean
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    // Create OrderDtoFactory according to the authenticated user
    @Bean("orderDtoFactory")
    @Scope("session")
    public OrderDtoFactory getOrderDtoFactory() {
        Role authRole = SecurityUtil.getUserDetails(SecurityContextHolder.getContext().getAuthentication()).getRole();
        switch (authRole) {
            case ROLE_CLIENT:
                return new ClientOrderDtoFactory();
            case ROLE_MANAGER:
                return new ManagerOrderDtoFactory();
            case ROLE_REPAIRER:
                return new RepairerOrderDtoFactory();
            default:
                throw new RuntimeException("Current role is not available yet");
        }
    }

    @Bean("loggedUser")
    @Scope("session")
    public User getUserCredentials() {
        User user = new User();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails ud = (UserDetails)auth.getPrincipal();
        user.setLogin(ud.getUsername());
        return user;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(new LogInterceptor());
    }
}
