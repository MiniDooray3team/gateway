package com.nhnacademy.springboot.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "server.task")
public class TaskProperties {

    String address;
}
