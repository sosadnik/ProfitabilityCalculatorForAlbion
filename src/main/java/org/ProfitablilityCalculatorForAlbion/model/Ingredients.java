package org.ProfitablilityCalculatorForAlbion.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crafting_recipes_id")
    private CraftingRecipes craftingRecipes;

    public Ingredients(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }
}
