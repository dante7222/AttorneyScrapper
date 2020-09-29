package com.introlab_systems.attorney_scrapper.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attorney")
@Data
public class Attorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private int phone;

    @Column(name = "email")
    private String email;

    @Column(name = "education")
    private String education;

    @ManyToOne
    @JoinColumn(name = "firm_id")
    private Firm firm;

    @Column(name = "website")
    private String website;

    @Column(name = "profile_photo_url")
    private String profile_photo_url;

    @Column(name = "profile_url")
    private String profile_url;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "attorney_sections",
            joinColumns = @JoinColumn(name = "attorney_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private List<Section> sections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "attorney_categories",
            joinColumns = @JoinColumn(name = "attorney_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}
