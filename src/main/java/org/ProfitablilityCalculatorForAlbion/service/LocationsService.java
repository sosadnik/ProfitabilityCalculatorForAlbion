package org.ProfitablilityCalculatorForAlbion.service;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.Locations;
import org.ProfitablilityCalculatorForAlbion.repository.LocationsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationsService {

    private final LocationsRepository locationsRepository;

    public void saveLocations(String locationsName) {
        if (!locationsRepository.existsByName(locationsName)){
            locationsRepository.save(new Locations(locationsName));
        }
    }
}
