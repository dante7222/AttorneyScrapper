package com.introlab_systems.attorney_scrapper.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "attorney")
@Data
@NoArgsConstructor
public class Attorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "firm_id")
    private Firm firm;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Column(name = "education")
    private String education;

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
    private Set<Section> sections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "attorney_categories",
            joinColumns = @JoinColumn(name = "attorney_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public Attorney(String fullName, String phone,
                    String website, String profile_url, String profile_photo_url) {
        this.fullName = fullName;
        this.phone = phone;
        this.website = website;
        this.profile_url = profile_url;
        this.profile_photo_url = profile_photo_url;
    }
}
