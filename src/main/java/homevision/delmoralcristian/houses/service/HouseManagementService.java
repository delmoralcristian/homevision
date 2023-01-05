package homevision.delmoralcristian.houses.service;

import homevision.delmoralcristian.houses.dto.HouseAPIResponseDTO;
import homevision.delmoralcristian.houses.dto.HousesAPIRootResponseDTO;

import java.util.List;

public interface HouseManagementService {

    HousesAPIRootResponseDTO getHouses(Integer page, Integer perPage);

}
