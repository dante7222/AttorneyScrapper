package com.introlab_systems.attorney_scrapper.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attorney")
@Getter
@Setter
@NoArgsConstructor
public class Attorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "firm_name")
    private String firm_name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Column(name = "profile_photo_url")
    private String profile_photo_url;

    @Column(name = "profile_url")
    private String profile_url;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<Section> sections = new ArrayList<>();

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

}
