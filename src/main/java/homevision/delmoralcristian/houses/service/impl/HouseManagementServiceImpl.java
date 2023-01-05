package homevision.delmoralcristian.houses.service.impl;

import homevision.delmoralcristian.houses.dto.HousesAPIRootResponseDTO;
import homevision.delmoralcristian.houses.service.APIService;
import homevision.delmoralcristian.houses.service.HouseManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
// TODO: Missing unit test
public class HouseManagementServiceImpl implements HouseManagementService {

    @Autowired
    private APIService apiService;

    @Override
    public HousesAPIRootResponseDTO getHouses(Integer page, Integer perPage) {
        log.info("Getting houses information");
        return this.apiService.getHouses(page, perPage);
    }

}
