package com.introlab_systems.attorney_scrapper.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectedAttorneyProfileSource {
    String profileUrl;
    String pageSource;
}
