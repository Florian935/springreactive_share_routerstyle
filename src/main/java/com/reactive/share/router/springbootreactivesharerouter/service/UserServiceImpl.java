package com.reactive.share.router.springbootreactivesharerouter.service;

import com.reactive.share.router.springbootreactivesharerouter.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Random;
import java.util.function.BiFunction;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public Flux<User> userStream() {
        return Flux.generate(
                () -> 0,
                (BiFunction<Integer, SynchronousSink<User>, Integer>) (index, sink) -> {
                    User user = userGenerator(index);
                    sink.next(user);
                    return ++index;
                    })
                .zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map((Tuple2::getT1))
                .share();
    }

    private User userGenerator(int ticketNumber) {
        final String[] names = "Jean,Paul,Tintin,Gerard,Lindsay,Flo,Ludo,Nico,Clem,Alex,Justine,Melvin".split(",");
        final String randomName = names[new Random().nextInt(names.length)];
        final int randomAge = new Random().nextInt(100);
        return new User(randomName, randomAge, ticketNumber);
    }
}
