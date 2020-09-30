package com.introlab_systems.attorney_scrapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Firm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Firm {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Firm(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
