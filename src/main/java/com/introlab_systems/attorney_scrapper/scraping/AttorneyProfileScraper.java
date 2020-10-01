package com.introlab_systems.attorney_scrapper.scraping;

import com.introlab_systems.attorney_scrapper.model.CollectedAttorneyProfileSource;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AttorneyProfileScraper {
    public CollectedAttorneyProfileSource scrape(String profileUrl) throws IOException {
        CollectedAttorneyProfileSource source = new CollectedAttorneyProfileSource();
        String pageSource = Jsoup.connect(profileUrl).execute().body();
        source.setPageSource(pageSource);
        source.setProfileUrl(profileUrl);
        return source;
    }
}
