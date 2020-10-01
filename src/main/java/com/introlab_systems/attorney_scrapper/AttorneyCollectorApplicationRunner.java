package com.introlab_systems.attorney_scrapper;

import com.introlab_systems.attorney_scrapper.service.AttorneyCollectorService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.normalizeSpace;

@Service
public class AttorneyCollectorApplicationRunner implements CommandLineRunner {
    private final AttorneyCollectorService attorneyCollectorService;

    public AttorneyCollectorApplicationRunner(AttorneyCollectorService attorneyCollectorService) {
        this.attorneyCollectorService = attorneyCollectorService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ArrayUtils.isEmpty(args)) {
            throw new InvalidCommandLineException("No directives provided");
        }
        String directive = normalizeSpace(args[0]);
        if (equalsIgnoreCase(directive, "collect")) {
            attorneyCollectorService.collect();
        }
    }

    static class InvalidCommandLineException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        InvalidCommandLineException(String message) {
            super(message);
        }
    }
}
