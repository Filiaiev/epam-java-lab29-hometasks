package com.filiaiev.spring.mvc1.test.config;

import com.filiaiev.spring.mvc1.controller.assembler.ManagerOrderAssembler;
import com.filiaiev.spring.mvc1.repository.UserRepository;
import com.filiaiev.spring.mvc1.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    private final UserRepository userRepository;

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

//    @Bean
//    public UserDetailsServiceImpl getUserDetailsService() {
//        return new UserDetailsServiceImpl(userRepository);
//    }

    @Bean
    public ManagerOrderAssembler getManagerOrderAssembler() {
        return new ManagerOrderAssembler();
    }
}
