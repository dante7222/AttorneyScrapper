package com.introlab_systems.attorney_scrapper.scratcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageScrapper {
    private static final String url = "https://intus.austinbar.org/directory/default.aspx?page=";

    public List<String> getPageAttorneysURI(int pageNumber) {
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
                .map(element -> element.getElementsByTag("a").attr("href"))
                .collect(Collectors.toList());

        return attorneysURIList;
    }
}
