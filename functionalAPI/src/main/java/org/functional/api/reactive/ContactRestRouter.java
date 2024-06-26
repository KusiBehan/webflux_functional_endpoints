package org.functional.api.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class ContactRestRouter {

    @Bean
    public RouterFunction<ServerResponse> routeContact(ContactRestHandler contactRestHandler) {
        return RouterFunctions
                .route(GET("/functional/contacts")
                        , contactRestHandler::getAllContacts)
                .andRoute(GET("/functional/contacts/{id}")
                        , contactRestHandler::getContactById)
                .andRoute(GET("/functional/contacts/byEmail/{email}")
                        , contactRestHandler::getContactByEmail)
                .andRoute(POST("/functional/contacts")
                        , contactRestHandler::insertContact)
                .andRoute(PUT("functional/contacts/{id}")
                        , contactRestHandler::updateContact)
                .andRoute(DELETE("/functional/contacts/{id}")
                        , contactRestHandler::deleteContact);
    }

    }