package com.hydeparksafety;

import com.hydeparksafety.service.UChicagoPoliceFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by HSong on 11/11/2017.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired private UChicagoPoliceFeedService ucpFeedService;
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        ucpFeedService.getFeeds();
    }
}
