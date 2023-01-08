package homevision.delmoralcristian.houses.service.impl;

import homevision.delmoralcristian.houses.aspect.RetryAPICall;
import homevision.delmoralcristian.houses.dto.HousesAPIRootResponseDTO;
import homevision.delmoralcristian.houses.enums.CommonMessage;
import homevision.delmoralcristian.houses.exceptions.InternalServerErrorException;
import homevision.delmoralcristian.houses.service.APIService;
import homevision.delmoralcristian.houses.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.stream.Collectors;

@Service
@Slf4j
public class APIServiceImpl implements APIService {

    @Value("${houses.api.url}")
    private String apiHousesUrl;

    @Override
    @RetryAPICall
    public HousesAPIRootResponseDTO getHouses(Integer page, Integer perPage) {
        HousesAPIRootResponseDTO responseDTO;
        try {
            log.info("Calling get houses endpoint API");
            var urlFormatted = MessageFormat.format(apiHousesUrl, String.valueOf(page), String.valueOf(perPage));
            var url = new URL(urlFormatted);
            var http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Accept", "application/json");
            var br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            var responseBody = br.lines().collect(Collectors.joining());
            responseDTO = JsonUtils.fromJsonRequest(responseBody, HousesAPIRootResponseDTO.class);
            http.disconnect();

        } catch (Exception e) {
            throw new InternalServerErrorException(CommonMessage.API_CALL_ERROR.getMessage());
        }
        return responseDTO;
    }
}
