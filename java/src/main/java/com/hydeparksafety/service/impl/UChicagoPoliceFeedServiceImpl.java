package com.hydeparksafety.service.impl;

import com.hydeparksafety.service.UChicagoPoliceFeedService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by HSong on 11/11/2017.
 */
@Service
public class UChicagoPoliceFeedServiceImpl implements UChicagoPoliceFeedService {
    final private static String URL = "https://incidentreports.uchicago.edu/rss.php?ReportType=incident";
    @Override
    public List getFeeds() {
        List result = null;
        try {
            System.out.println("hi");
            Document document = Jsoup.connect("https://incidentreports.uchicago.edu//incidentReportArchive.php?startDate=10%2F18%2F2017&endDate=10%2F21%2F2017").get();
            Elements elements = document.getElementsByTag("tbody");
            System.out.println(elements);
//            String s = elements.attr("t");

//            System.out.println(s);
//            String s = elements.attr("tr");
//            System.out.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
