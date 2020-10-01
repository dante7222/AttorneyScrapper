package com.introlab_systems.attorney_scrapper.service;

import com.introlab_systems.attorney_scrapper.model.ParsedAttorney;
import com.introlab_systems.attorney_scrapper.model.entity.Attorney;
import com.introlab_systems.attorney_scrapper.model.entity.Category;
import com.introlab_systems.attorney_scrapper.model.entity.Education;
import com.introlab_systems.attorney_scrapper.model.entity.Section;
import org.springframework.stereotype.Service;

@Service
public class AttorneyMapper {
    public Attorney toAttorney(ParsedAttorney parsedAttorney) {
        Attorney attorney = new Attorney();
        attorney.setProfile_url(parsedAttorney.getProfileUrl());
        attorney.setFullName(parsedAttorney.getFullName());
        attorney.setProfile_photo_url(parsedAttorney.getProfilePhotoUrl());
        attorney.setFirm_name(parsedAttorney.getFirmName());
        attorney.setPhone(parsedAttorney.getPhone());
        attorney.setWebsite(parsedAttorney.getWebsite());
        attorney.setAddress(parsedAttorney.getAddress());
        putCategories(parsedAttorney, attorney);
        putSections(parsedAttorney, attorney);
        putEducations(parsedAttorney, attorney);
        return attorney;
    }

    private void putEducations(ParsedAttorney source, Attorney target) {
        for (String educationRaw : source.getEducations()) {
            Education education = new Education();
            education.setName(educationRaw);
            education.setAttorneyId(target);
            target.getEducations().add(education);
        }
    }

    private void putSections(ParsedAttorney source, Attorney target) {
        for (String sectionRaw : source.getSections()) {
            Section section = new Section();
            section.setName(sectionRaw);
            section.setAttorneyId(target);
            target.getSections().add(section);
        }
    }

    private void putCategories(ParsedAttorney source, Attorney target) {
        for (String categoryRaw : source.getCategories()) {
            Category category = new Category();
            category.setName(categoryRaw);
            category.setAttorneyId(target);
            target.getCategories().add(category);
        }
    }
}
