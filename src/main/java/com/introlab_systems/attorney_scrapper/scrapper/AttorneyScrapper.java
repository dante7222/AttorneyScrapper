package com.introlab_systems.attorney_scrapper.scrapper;

import com.introlab_systems.attorney_scrapper.entity.Attorney;
import com.introlab_systems.attorney_scrapper.entity.Category;
import com.introlab_systems.attorney_scrapper.entity.Education;
import com.introlab_systems.attorney_scrapper.entity.Section;
import com.introlab_systems.attorney_scrapper.service.AttorneyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AttorneyScrapper {
    private static final String DOMAIN = "https://intus.austinbar.org/";
    private static final String WEBSITE_SELECTOR = ".form-group:has(label:contains(Website))";
    private static final String NAME_SELECTOR = ".form-group:has(label:contains(Name))";
    private static final String FIRM_SELECTOR = ".form-group:has(label:contains(Firm))";
    private static final String EDUCATION_SELECTOR = ".form-group:has(label:contains(Law School)) li";
    private static final String SECTION_SELECTOR = ".form-group:has(label:contains(Sections)) li";
    private static final String CATEGORY_SELECTOR = ".form-group:has(label:contains(Categories)) li";
    private static final String ADDRESS_SELECTOR = ".form-group:has(label:contains(Address))";
    private static final String PHONE_SELECTOR = ".form-group:has(label:contains(Phone))";


    private PageScrapper pageScrapper;
    private AttorneyService attorneyService;


    public AttorneyScrapper(PageScrapper pageScrapper, AttorneyService attorneyService) {
        this.pageScrapper = pageScrapper;
        this.attorneyService = attorneyService;
    }

    /*public List<String> getAttorneysProfileLink(int pageNumber) {
        final List<String> pageAttorneysURI = pageScrapper.getPageAttorneysURI(pageNumber);

        final List<String> collect = pageAttorneysURI.stream()
                .map(uri -> uri.replaceFirst("/", DOMAIN))
                .collect(Collectors.toList());

        return collect;
    }*/

    public void saveAttorneys(int pageNumber) {
        List<String> attorneysProfileLink = pageScrapper.getPageAttorneysURL(pageNumber);

        Document document = null;
        try {
            document = Jsoup.connect("https://intus.austinbar.org/directory/details/?id=816566").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String profilePhotoURL = document.select("img").attr("src").replaceFirst("/", DOMAIN);

        Attorney attorney = new Attorney();
        attorney.setProfile_photo_url(profilePhotoURL);
        attorney.setProfile_url(attorneysProfileLink.get(2));
        attorney.setFirm_name(document.select(FIRM_SELECTOR).text());
        attorney.setFullName(document.select(NAME_SELECTOR).text());
        attorney.setAddress(document.select(ADDRESS_SELECTOR).text());
        attorney.setPhone(document.select(PHONE_SELECTOR).text());
        attorney.setWebsite(document.select(WEBSITE_SELECTOR).text());


        Elements education = document.select(EDUCATION_SELECTOR);
        if (education != null) {
            attorney.setEducations(new ArrayList<>());

            for (int i = 0; i < education.size(); i++) {
                attorney.getEducations().add(new Education((long) i, attorney, education.get(i).text()));
            }
        }

        Elements sections = document.select(SECTION_SELECTOR);
        if (sections != null) {
            attorney.setSections(new ArrayList<>());

            for (int i = 0; i < sections.size(); i++) {
                attorney.getSections().add(new Section((long) i, attorney, sections.get(i).text()));
            }
        }

        Elements catergories = document.select(CATEGORY_SELECTOR);
        if (catergories != null) {
            attorney.setCategories(new ArrayList<>());

            for (int i = 0; i < catergories.size(); i++) {
                attorney.getCategories().add(new Category((long) i, attorney, catergories.get(i).text()));
            }
        }

        attorneyService.save(attorney);

    }
}
