package org.ProfitablilityCalculatorForAlbion.repository;

import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CraftingRecipesRepository extends JpaRepository<CraftingRecipes, Long> {

    boolean existsByName(String name);

    List<CraftingRecipes> findByCategory(String category);

    List<CraftingRecipes> findByName(String item);
}
