package org.ProfitablilityCalculatorForAlbion.repository;

import org.ProfitablilityCalculatorForAlbion.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
}
