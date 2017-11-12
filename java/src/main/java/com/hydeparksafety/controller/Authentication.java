package com.hydeparksafety.controller;

import org.springframework.http.MediaType;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HSong on 12/11/2017.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class Authentication {
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public Authentication(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(value = "/fb", method = RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";
    }
}
