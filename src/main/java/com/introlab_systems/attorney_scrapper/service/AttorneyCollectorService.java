package com.introlab_systems.attorney_scrapper.service;

import com.introlab_systems.attorney_scrapper.model.CollectedAttorneyProfileSource;
import com.introlab_systems.attorney_scrapper.model.ParsedAttorney;
import com.introlab_systems.attorney_scrapper.model.entity.Attorney;
import com.introlab_systems.attorney_scrapper.parsing.AttorneyParser;
import com.introlab_systems.attorney_scrapper.repository.AttorneyRepository;
import com.introlab_systems.attorney_scrapper.scraping.AttorneyListingScraper;
import com.introlab_systems.attorney_scrapper.scraping.AttorneyProfileScraper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class AttorneyCollectorService {
    private final AttorneyListingScraper listingScraper;
    private final AttorneyProfileScraper profileScraper;
    private final AttorneyParser parser;
    private final AttorneyMapper mapper;
    private final AttorneyRepository attorneyRepository;

    public AttorneyCollectorService(AttorneyListingScraper listingScraper,
                                    AttorneyProfileScraper profileScraper,
                                    AttorneyParser parser,
                                    AttorneyMapper mapper,
                                    AttorneyRepository attorneyRepository) {
        this.listingScraper = listingScraper;
        this.profileScraper = profileScraper;
        this.parser = parser;
        this.mapper = mapper;
        this.attorneyRepository = attorneyRepository;
    }

    public void collect() throws IOException {
        Collection<String> profileUrls = listingScraper.scrape();
        for (String profileUrl : profileUrls) {
            CollectedAttorneyProfileSource source = profileScraper.scrape(profileUrl);
            ParsedAttorney parsedAttorney = parser.parse(source);
            Attorney attorney = mapper.toAttorney(parsedAttorney);
            attorneyRepository.save(attorney);
        }
    }
}
