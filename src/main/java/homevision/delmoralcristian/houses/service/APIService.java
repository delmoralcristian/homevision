package homevision.delmoralcristian.houses.service;

import homevision.delmoralcristian.houses.dto.HousesAPIRootResponseDTO;

public interface APIService {

    HousesAPIRootResponseDTO getHouses(Integer page, Integer perPage);
}
