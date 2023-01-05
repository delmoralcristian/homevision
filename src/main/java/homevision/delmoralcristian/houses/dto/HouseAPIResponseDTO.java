package homevision.delmoralcristian.houses.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class HouseAPIResponseDTO {

    private long id;
    private String address;
    private String homeowner;
    private int price;
    private String photoUrl;

}
