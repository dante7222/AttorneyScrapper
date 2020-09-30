
package com.introlab_systems.attorney_scrapper;

import com.introlab_systems.attorney_scrapper.scratcher.AttorneyScrapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AttorneyScrapperApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(AttorneyScrapperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        AttorneyScrapper bean = applicationContext.getBean(AttorneyScrapper.class);
        bean.saveAttorneys();
    }

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }
}
