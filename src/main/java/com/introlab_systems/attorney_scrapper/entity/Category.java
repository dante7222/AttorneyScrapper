package com.introlab_systems.attorney_scrapper.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Category implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attorney_id",
            referencedColumnName = "id")
    private Attorney attorneyId;

    @Column(name = "name")
    private String name;

}
