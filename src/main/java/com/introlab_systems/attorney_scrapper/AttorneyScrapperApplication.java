
package com.introlab_systems.attorney_scrapper;

import com.introlab_systems.attorney_scrapper.loader.AttorneyLoader;
import com.introlab_systems.attorney_scrapper.parser.AttorneyParser;
import com.introlab_systems.attorney_scrapper.scrapper.PageScrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class AttorneyScrapperApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(AttorneyScrapperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PageScrapper pageScrapper = applicationContext.getBean(PageScrapper.class);
        AttorneyParser attorneyParser = applicationContext.getBean(AttorneyParser.class);
        AttorneyLoader attorneyLoader = applicationContext.getBean(AttorneyLoader.class);

        int pageNumber = 1;
        while (!pageScrapper.getPageAttorneysURL(pageNumber).isEmpty()) {
            List<String> pageAttorneysURL = pageScrapper.getPageAttorneysURL(pageNumber);
            pageAttorneysURL.forEach(
                    (attorneyURL) -> {
                        attorneyLoader.load(
                                attorneyParser.getAttorneyObject(attorneyURL)
                        );
                    }
            );
            pageNumber++;
        }
    }
}
