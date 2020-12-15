package com.reactive.share.router.springbootreactivesharerouter.service;

import com.reactive.share.router.springbootreactivesharerouter.model.User;
import reactor.core.publisher.Flux;

public interface UserService {

    Flux<User> userStream();
}
