package homevision.delmoralcristian.houses.service.impl;

import homevision.delmoralcristian.houses.dto.HousesAPIRootResponseDTO;
import homevision.delmoralcristian.houses.service.APIService;
import homevision.delmoralcristian.houses.service.HouseManagementService;
import homevision.delmoralcristian.houses.thread.ConsumerThread;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.Executors;

@Service
@Slf4j
// TODO: Missing unit test
public class HouseManagementServiceImpl implements HouseManagementService {

    @Autowired
    private APIService apiService;

    @Value("${threads.numbers}")
    private Integer numberOfThreads;

    @Value("${house.photos.download.extension}")
    private String fileExtension;

    @Value("${house.photos.download.directory}")
    private String directory;

    @Override
    public HousesAPIRootResponseDTO getHouses(Integer page, Integer perPage) {
        log.info("Getting houses information");
        return this.apiService.getHouses(page, perPage);
    }

    @Override
    public void downloadHousePhotos(Integer page, Integer perPage) {
        var housesAPIRootResponseDTO = this.apiService.getHouses(page, perPage);
        var houses = housesAPIRootResponseDTO.getHouses();
        var executor = Executors.newFixedThreadPool(numberOfThreads);
        var logMessage = new StringBuilder("Preparing to download house photos to ")
                .append(directory)
                .append(" directory.");
        log.info(logMessage.toString());
        houses.stream().forEach(house -> {
            var fileName = new StringBuilder(String.valueOf(house.getId()))
                    .append("-")
                    .append(house.getAddress())
                    .append(fileExtension);
            executor.execute(new ConsumerThread(fileName.toString(), house.getPhotoURL(), new File(directory + fileName)));
        });
    }

}
