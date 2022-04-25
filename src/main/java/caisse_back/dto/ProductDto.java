package caisse_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String category;

    private float price;

    private String image;

}


