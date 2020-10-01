package com.introlab_systems.attorney_scrapper.loader;

import com.introlab_systems.attorney_scrapper.entity.Attorney;
import com.introlab_systems.attorney_scrapper.service.AttorneyService;
import org.springframework.stereotype.Component;

@Component
public class AttorneyLoader {
    AttorneyService attorneyService;

    public AttorneyLoader(AttorneyService attorneyService) {
        this.attorneyService = attorneyService;
    }

    public void load(Attorney attorney) {
        attorneyService.save(attorney);
    }
}
