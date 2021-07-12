package org.ProfitablilityCalculatorForAlbion.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "locations")
public class Locations {

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Locations(String name) {
        this.name = name;
    }
}
