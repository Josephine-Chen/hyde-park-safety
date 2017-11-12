package com.hydeparksafety.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HSong on 11/11/2017.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping(value = "/incident", produces = MediaType.APPLICATION_JSON_VALUE)
public class IncidentController {
    
}
