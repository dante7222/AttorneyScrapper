package com.introlab_systems.attorney_scrapper.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Firm")
@Data
public class Firm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;
}
