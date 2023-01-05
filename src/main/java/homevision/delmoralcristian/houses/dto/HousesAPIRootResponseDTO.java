package homevision.delmoralcristian.houses.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class HousesAPIRootResponseDTO {
    private List<HouseAPIResponseDTO> houses;
}
