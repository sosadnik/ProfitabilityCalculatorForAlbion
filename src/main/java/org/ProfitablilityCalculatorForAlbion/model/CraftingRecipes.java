package org.ProfitablilityCalculatorForAlbion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crafting_recipes")
public class CraftingRecipes {

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer amountIngredients;

    private Integer itemValue;

    private String category;

    @ElementCollection
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "craftingRecipes")
    private List<Ingredients> ingredients;


}

