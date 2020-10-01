package com.introlab_systems.attorney_scrapper.parsing;

import com.introlab_systems.attorney_scrapper.model.CollectedAttorneyProfileSource;
import com.introlab_systems.attorney_scrapper.model.ParsedAttorney;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.normalizeSpace;

@Component
public class AttorneyParser {
    /*private static final String DOMAIN = "https://intus.austinbar.org/";
    private static final String WEBSITE_SELECTOR = ".form-group:has(label:contains(Website))";
    private static final String NAME_SELECTOR = ".form-group:has(label:contains(Name))";
    private static final String FIRM_SELECTOR = ".form-group:has(label:contains(Firm))";
    private static final String EDUCATION_SELECTOR = ".form-group:has(label:contains(Law School)) li";
    private static final String SECTION_SELECTOR = ".form-group:has(label:contains(Sections)) li";
    private static final String CATEGORY_SELECTOR = ".form-group:has(label:contains(Categories)) li";
    private static final String ADDRESS_SELECTOR = ".form-group:has(label:contains(Address))";
    private static final String PHONE_SELECTOR = ".form-group:has(label:contains(Phone))";


    public Attorney getAttorneyObject(String profileURL) {
        Document document = null;
        try {
            document = Jsoup.connect(profileURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String profilePhotoURL = document.select("img")
                .attr("src")
                .replaceFirst("/", DOMAIN);

        Attorney attorney = new Attorney();
        attorney.setProfile_photo_url(profilePhotoURL);
        attorney.setProfile_url(profileURL);
        attorney.setFirm_name(StringUtils.remove(
                document.select(FIRM_SELECTOR).text(), "Firm"));
        attorney.setFullName(StringUtils.remove(
                document.select(NAME_SELECTOR).text(), "Name"));
        attorney.setAddress(StringUtils.remove(
                document.select(ADDRESS_SELECTOR).text(), "Address"));
        attorney.setPhone(StringUtils.remove(
                document.select(PHONE_SELECTOR).text(), "Phone"));
        attorney.setWebsite(StringUtils.remove(
                document.select(WEBSITE_SELECTOR).text(), "Website"));


        Elements education = document.select(EDUCATION_SELECTOR);
        if (education != null) {
            attorney.setEducations(new ArrayList<>());

            for (int i = 0; i < education.size(); i++) {
                attorney.getEducations().add(new Education((long) i + 1, attorney, education.get(i).text()));
            }
        }

        Elements sections = document.select(SECTION_SELECTOR);
        if (sections != null) {
            attorney.setSections(new ArrayList<>());

            for (int i = 0; i < sections.size(); i++) {
                System.out.println("HELLLOOOOOOOOOO!!!!!");
                attorney.getSections().add(new Section((long) i + 1, attorney, sections.get(i).text()));
            }
        }

        Elements catergories = document.select(CATEGORY_SELECTOR);
        if (catergories != null) {
            attorney.setCategories(new ArrayList<>());

            for (int i = 0; i < catergories.size(); i++) {
                attorney.getCategories().add(new Category((long) i + 1, attorney, catergories.get(i).text()));
            }
        }
        return attorney;
    }*/
    private static final String DOMAIN = "https://intus.austinbar.org/";
    private static final String WEBSITE_SELECTOR = ".form-group:has(label:contains(Website))";
    private static final String NAME_SELECTOR = ".form-group:has(label:contains(Name))";
    private static final String FIRM_SELECTOR = ".form-group:has(label:contains(Firm))";
    private static final String EDUCATION_SELECTOR = ".form-group:has(label:contains(Law School)) li";
    private static final String SECTION_SELECTOR = ".form-group:has(label:contains(Sections)) li";
    private static final String CATEGORY_SELECTOR = ".form-group:has(label:contains(Categories)) li";
    private static final String ADDRESS_SELECTOR = ".form-group:has(label:contains(Address))";
    private static final String PHONE_SELECTOR = ".form-group:has(label:contains(Phone))";
    private static final String PROFILE_PHOTO_SELECTOR = ".thumbnail img";

    public ParsedAttorney parse(CollectedAttorneyProfileSource source) {
        Document pageDocument = Jsoup.parse(source.getPageSource(), source.getProfileUrl());
        ParsedAttorney parsedAttorney = new ParsedAttorney();
        parsedAttorney.setProfileUrl(source.getProfileUrl());
        parsedAttorney.setFullName(fetchFullName(pageDocument));
        parsedAttorney.setFirmName(fetchFirmName(pageDocument));
        parsedAttorney.setPhone(fetchPhone(pageDocument));
        parsedAttorney.setWebsite(fetchWebsite(pageDocument));
        parsedAttorney.setProfilePhotoUrl(fetchProfilePhotoUrl(pageDocument));
        parsedAttorney.setAddress(fetchAddress(pageDocument));
        parsedAttorney.setCategories(fetchCategories(pageDocument));
        parsedAttorney.setEducations(fetchEducations(pageDocument));
        parsedAttorney.setSections(fetchSections(pageDocument));
        return parsedAttorney;
    }

    private String fetchFullName(Document pageDocument) {
        return text(pageDocument.selectFirst(NAME_SELECTOR));
    }

    private String fetchFirmName(Document pageDocument) {
        return text(pageDocument.selectFirst(FIRM_SELECTOR));
    }

    private String fetchPhone(Document pageDocument) {
        return text(pageDocument.selectFirst(PHONE_SELECTOR));
    }

    private String fetchWebsite(Document pageDocument) {
        return text(pageDocument.selectFirst(WEBSITE_SELECTOR));
    }

    private String fetchProfilePhotoUrl(Document pageDocument) {
        Element photoElement = pageDocument.selectFirst(PROFILE_PHOTO_SELECTOR);
        if (Objects.nonNull(photoElement) && photoElement.hasAttr("src")) {
            return photoElement.absUrl("src");
        }
        return null;
    }

    private String fetchAddress(Document pageDocument) {
        return text(pageDocument.selectFirst(ADDRESS_SELECTOR));
    }

    private List<String> fetchCategories(Document pageDocument) {
        return toStringList(pageDocument.select(CATEGORY_SELECTOR));
    }

    private List<String> fetchEducations(Document pageDocument) {
        return toStringList(pageDocument.select(EDUCATION_SELECTOR));
    }

    private List<String> fetchSections(Document pageDocument) {
        return toStringList(pageDocument.select(SECTION_SELECTOR));
    }

    private List<String> toStringList(Elements elements) {
        return elements
                .stream()
                .map(Element::text)
                .map(StringUtils::normalizeSpace)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .collect(Collectors.toList());
    }

    private String text(Element element) {
        String text = null;
        if (Objects.nonNull(element)) {
            text = normalizeSpace(element.text());
        }
        return text;
    }
}
