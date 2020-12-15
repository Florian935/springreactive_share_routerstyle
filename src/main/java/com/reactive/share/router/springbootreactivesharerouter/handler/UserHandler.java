package com.reactive.share.router.springbootreactivesharerouter.handler;

import com.reactive.share.router.springbootreactivesharerouter.model.User;
import com.reactive.share.router.springbootreactivesharerouter.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandler {

    private final Flux<User> userStream;

    public UserHandler(UserService userService) {
        this.userStream = userService.userStream();
    }

    public Mono<ServerResponse> fetchUsersSSE(ServerRequest request) {
        return ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(this.userStream, User.class);
    }

    public Mono<ServerResponse> fetchUser(ServerRequest request) {
        return ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(userStream, User.class);
    }
}
