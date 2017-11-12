package com.hydeparksafety;

import com.hydeparksafety.repo.IncidentRepo;
import com.hydeparksafety.service.UChicagoPoliceFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;

/**
 * Created by HSong on 11/11/2017.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired private UChicagoPoliceFeedService ucpFeedService;
    @Autowired private IncidentRepo incidentRepo;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

    }

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);

    @Override
    public void run(String... args) throws Exception {
//        Date start = formatter.parse("2016-10-18");
//        Date end = formatter.parse("2017-11-09");
//        List<Incident> incidents = ucpFeedService.getFeeds(start, end);
//        incidentRepo.save(incidents);
    }
}
