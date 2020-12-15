package com.reactive.share.router.springbootreactivesharerouter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String username;
    private int age;
    private int ticketNumber;
}
