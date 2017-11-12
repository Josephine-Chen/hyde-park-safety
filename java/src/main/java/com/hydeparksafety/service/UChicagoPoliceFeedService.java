package com.hydeparksafety.service;

import java.util.Date;
import java.util.List;

/**
 * Created by HSong on 11/11/2017.
 */
public interface UChicagoPoliceFeedService {
    List getFeeds(Date start, Date end);
}
