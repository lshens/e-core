package com.priceline.ecore.roles.integration.tests.controller;

import com.priceline.ecore.roles.RolesApplication;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = RolesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan("com.priceline.ecore")
public abstract class AbstractControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    protected WebTestClient client;

    @BeforeEach
    void setup() {
        client = MockMvcWebTestClient.bindToApplicationContext(context).build();
    }
}
