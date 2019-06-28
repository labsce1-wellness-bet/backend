package com.lambdaschool.wellness;

import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class WellnessApplication
{

    public static void main(String[] args)
    {
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
        SpringApplication.run(WellnessApplication.class, args);
    }


}
