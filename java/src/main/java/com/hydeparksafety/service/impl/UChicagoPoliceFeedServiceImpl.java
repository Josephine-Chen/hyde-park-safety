package com.hydeparksafety.service.impl;

import com.hydeparksafety.entity.Incident;
import com.hydeparksafety.service.ReadIncidentStringService;
import com.hydeparksafety.service.UChicagoPoliceFeedService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.contains;

/**
 * Created by HSong on 11/11/2017.
 */
@Service
public class UChicagoPoliceFeedServiceImpl implements UChicagoPoliceFeedService {
    final private static String PATTERN = "MM%2'F'dd%2'F'yyyy";
    final private static String START_DATE = "startDate=";
    final private static String END_DATE = "&endDate=";
    final private static String OFFSET = "&offset=";
    final private static String BASE =
            "https://incidentreports.uchicago.edu//incidentReportArchive.php?";
    private SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);

    @Override
    public List<Incident> getFeeds(Date start, Date end) {
        List<Incident> result = new ArrayList(1700);
        try {
            int offset = 0;
            while (offset < 1700) {
                String url = getUrl(start, end, offset);

                ReadIncidentStringService service = new ReadIncidentStringService();

                URL oracle = new URL(url);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));

                String inputLine;
                StringBuilder html = new StringBuilder();
                boolean body = false;
                boolean row = false;
                String incidentRow = "";
                String first = "";
                while ((inputLine = in.readLine()) != null) {
                    if (contains(inputLine, "<tbody>"))
                        body = true;
                    if (contains(inputLine, "<tr>"))
                        row = true;
                    if (body) {
                        if (row) {
                            if (first.equals("") && contains(inputLine, "<td>"))
                                first = inputLine;
                            incidentRow += inputLine + "\n";
                        } else {
                            if (!contains(first, "<td>:</td>"))
                                html.append(incidentRow);
                            incidentRow = "";
                            first = "";
                        }
                    } else {
                        html.append(inputLine).append("\n");
                    }
                    if (contains(inputLine, "</tr>"))
                        row = false;
                    if (contains(inputLine, "</tbody>"))
                        body = false;
                }
                in.close();

                Document doc = Jsoup.parse(html.toString());
                Elements tbody = doc.getElementsByTag("tbody");
                List<Incident> incidents = service.readString(tbody.toString());
                offset += 5;
                result.addAll(incidents);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private String getUrl(Date start, Date end, int offset) {
        return BASE + START_DATE + formatter.format(start) + END_DATE + formatter.format(end) + OFFSET + offset;
    }

}
