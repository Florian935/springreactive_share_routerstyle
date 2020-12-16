package com.reactive.share.router.springbootreactivesharerouter.router;

import com.reactive.share.router.springbootreactivesharerouter.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    RouterFunction<ServerResponse> usersRoute(UserHandler userHandler) {
        return nest(
                path("/users"),
                route()
                        .GET("", userHandler::fetchUser)
                        .GET("/SSE", userHandler::fetchUsersSSE)
                        .build()
                );
    }
}
