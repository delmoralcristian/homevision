package homevision.delmoralcristian.houses.controller;

import homevision.delmoralcristian.houses.dto.HousesAPIRootResponseDTO;
import homevision.delmoralcristian.houses.service.HouseManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api")
@Api(value = "Houses Management Microservice")
@Slf4j
// TODO: Missing integration test
public class HouseManagementController {

    @Autowired
    private HouseManagementService houseManagementService;

    @RequestMapping(value = "/houses", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get houses by pagination")
    public ResponseEntity<?> getHouses(@ApiParam(value = "The page number you want to retrieve (default is 1)")
                                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                              @ApiParam(value = "The number of houses per page (default is 100)")
                                                              @RequestParam(value = "perPage", required = false, defaultValue = "100") Integer perPage) {
        log.info("Executing got houses controller");
        return ResponseEntity.ok(houseManagementService.getHouses(page, perPage));
    }

    @RequestMapping(value = "/houses/photo/download", method = RequestMethod.GET)
    @ApiOperation(value = "Download houses photo")
    //TODO: for simplicity the images are downloaded in the tmp/ directory
    public ResponseEntity<?> downloadHousePhotos(@ApiParam(value = "The page number you want to retrieve (default is 1)")
                                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                      @ApiParam(value = "The number of houses per page (default is 100)")
                                                              @RequestParam(value = "perPage", required = false, defaultValue = "100") Integer perPage) throws Exception {
        log.info("Executing download house photos controller");
        houseManagementService.downloadHousePhotos(page, perPage);
        return ResponseEntity.ok().build();
    }

}
