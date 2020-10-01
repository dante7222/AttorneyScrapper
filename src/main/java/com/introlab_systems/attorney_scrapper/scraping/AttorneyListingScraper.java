package com.introlab_systems.attorney_scrapper.scraping;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AttorneyListingScraper {
    /*private static final String URL = "https://intus.austinbar.org/directory/default.aspx?page=";
    private static final String DOMAIN = "https://intus.austinbar.org/";

    public List<String> getPageAttorneysURL(int pageNumber) {
        Document document = null;
        try {
            document = Jsoup.connect("https://intus.austinbar.org/directory/default.aspx?page=" + pageNumber).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements container = document.getElementsByClass("row");

        List<String> attorneysURIList = container.first()
                .getElementsByClass("span3 col-sm-6 col-md-3 directory-search-item")
                .stream()
                .map(element -> element.getElementsByTag("a").attr("href")
                .replaceFirst("/", DOMAIN))
                .collect(Collectors.toList());

        return attorneysURIList;
    }*/
    private static final String LISTING_URL_TEMPLATE = "https://intus.austinbar.org/directory/default.aspx?page=%d";

    public Set<String> scrape() throws IOException {
        int pageNumber = 1;
        Set<String> profileUrls = new HashSet<>();
        Set<String> currentPageUrls;
        do {
            currentPageUrls = collectUrlsFromOnePage(pageNumber);
            profileUrls.addAll(currentPageUrls);
            pageNumber++;
        } while (!currentPageUrls.isEmpty());
        return profileUrls;
    }

    private Set<String> collectUrlsFromOnePage(int pageNumber) throws IOException {
        return Jsoup.connect(String.format(LISTING_URL_TEMPLATE, pageNumber))
                .get()
                .select(".row .caption a")
                .stream()
                .filter(linkElement -> linkElement.hasAttr("href"))
                .map(linkElement -> linkElement.absUrl("href"))
                .collect(Collectors.toSet());
    }
}
