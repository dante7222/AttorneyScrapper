package com.introlab_systems.attorney_scrapper.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParsedAttorney {
    String profileUrl;
    String fullName;
    String firmName;
    String phone;
    String website;
    String profilePhotoUrl;
    String address;
    List<String> categories;
    List<String> educations;
    List<String> sections;
}
