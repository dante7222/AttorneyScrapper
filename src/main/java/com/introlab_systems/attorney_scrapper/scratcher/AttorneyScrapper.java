package com.introlab_systems.attorney_scrapper.scratcher;

import com.introlab_systems.attorney_scrapper.entity.Attorney;
import com.introlab_systems.attorney_scrapper.entity.Firm;
import com.introlab_systems.attorney_scrapper.service.AttorneyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttorneyScrapper {
    private static final String DOMAIN = "https://intus.austinbar.org/";
    private PageScrapper pageScrapper;
    private AttorneyService attorneyService;


    public AttorneyScrapper(PageScrapper pageScrapper, AttorneyService attorneyService) {
        this.pageScrapper = pageScrapper;
        this.attorneyService = attorneyService;
    }

    public List<String> getAttorneysProfileLink(int pageNumber) {
        final List<String> pageAttorneysURI = pageScrapper.getPageAttorneysURI(pageNumber);

        final List<String> collect = pageAttorneysURI.stream()
                .map(uri -> uri.replaceFirst("/", DOMAIN))
                .collect(Collectors.toList());

        return collect;
    }

    public void saveAttorneys() {
        List<String> attorneysProfileLink = getAttorneysProfileLink(1);

        Document document = null;
        try {
            document = Jsoup.connect(attorneysProfileLink.get(2)).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements profileContainer = document.getElementsByClass("col-lg-12 row");

        String profilePhotoURL = profileContainer.first().getElementsByTag("img")
                .attr("src").replaceFirst("/", DOMAIN);


        Elements profileData = profileContainer.select(".form-group");

        Attorney attorney = new Attorney();
        attorney.setProfile_photo_url(profilePhotoURL);
        attorney.setProfile_url(attorneysProfileLink.get(2));

        for (Element element : profileData) {
            String labelName = element
                    .select("label.control-label")
                    .text();

            if (labelName.equals("Name")) {
                attorney.setFullName(element.text());
            } else if (labelName.equals("Phone")) {
                attorney.setPhone(element.text());
            } else if (labelName.equals("Website")) {
                attorney.setWebsite(element.text());
            } else if (labelName.equals("Law School")) {
                attorney.setEducation(element.text());
            }
        }

        System.out.println(attorney);
           /* if (profileData.size() > 4) {
                String labelNames = profileData.get(4)
                        .select("label.control-label")
                        .text();

                if (labelNames.equals("Law School")) {
                    attorney.setEducation(profileData.get(5).text());
                }
            }*/


        attorney.setFirm(new Firm(1L,profileData.get(1).text(),profileData.get(4).text()));
        //System.out.println(attorney.getFirm());
        attorneyService.save(attorney);


    }
}
