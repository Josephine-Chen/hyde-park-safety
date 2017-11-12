package com.hydeparksafety.service;

import com.hydeparksafety.entity.Incident;

import java.util.Date;
import java.util.List;

/**
 * Created by HSong on 11/11/2017.
 */
public interface UChicagoPoliceFeedService {
    List<Incident> getFeeds(Date start, Date end);
}
