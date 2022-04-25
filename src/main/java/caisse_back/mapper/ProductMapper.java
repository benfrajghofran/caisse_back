package caisse_back.mapper;

import caisse_back.dto.ProductDto;
import caisse_back.models.Product;
import org.springframework.stereotype.Component;
@Component
public class ProductMapper {

        public ProductDto toDTO(Product product) {
            return ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .image(product.getImage())
                    .build();
        }
    }

