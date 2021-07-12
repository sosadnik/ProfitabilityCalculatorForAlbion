package org.ProfitablilityCalculatorForAlbion.repository;

import org.ProfitablilityCalculatorForAlbion.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Long> {

    boolean existsByName(String name);
}
