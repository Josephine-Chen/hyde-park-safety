package com.hydeparksafety.controller;

import com.hydeparksafety.entity.Incident;
import com.hydeparksafety.entity.UserIncident;
import com.hydeparksafety.repo.IncidentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by HSong on 11/11/2017.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping(value = "/incident", produces = MediaType.APPLICATION_JSON_VALUE)
public class IncidentController {
    @Autowired private IncidentRepo incidentRepo;

    private ConnectionRepository connectionRepository;

    public IncidentController(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    Incident fileIncident(Model model, UserIncident incident) {
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection == null)
            throw new RuntimeException("connection is null");
        Facebook facebook = connection.getApi();
        String id = facebook.userOperations().getUserProfile().getId();
        incident.setUserId(id);
        return incidentRepo.save(incident);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Incident> listAllIncident() {
        return incidentRepo.findAll();
    }

    @RequestMapping(value = "/recent", method = RequestMethod.GET)
    List<Incident> listRecent10() {
        return incidentRepo.findFirst5ByOrderByOccurredDesc();
    }
}

