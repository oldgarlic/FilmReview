package com.lll.film_review;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.lll.film_review.mapper")
@EnableScheduling
public class FilmReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmReviewApplication.class, args);
    }

}
