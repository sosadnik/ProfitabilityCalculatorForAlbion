package org.ProfitablilityCalculatorForAlbion.repository;

import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftingRecipesRepository extends JpaRepository<CraftingRecipes, Long> {

    boolean existsByName(String name);

}
