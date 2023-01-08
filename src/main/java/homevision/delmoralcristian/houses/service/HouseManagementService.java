package homevision.delmoralcristian.houses.service;

import homevision.delmoralcristian.houses.dto.HouseAPIResponseDTO;
import homevision.delmoralcristian.houses.dto.HousesAPIRootResponseDTO;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public interface HouseManagementService {

    HousesAPIRootResponseDTO getHouses(Integer page, Integer perPage);

    void downloadHousePhotos(Integer page, Integer perPage) throws MalformedURLException;

}
