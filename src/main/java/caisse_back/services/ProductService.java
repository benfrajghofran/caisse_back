package caisse_back.services;

import caisse_back.dto.ProductDto;
import caisse_back.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public List<ProductDto> findAll();
    public ProductDto findById(Long id);
    public void delete(Long id);

    public Product save(Long id,MultipartFile file, String product) throws Exception;
    public byte[] getPhoto(Long id) throws Exception;
    public ProductDto create(MultipartFile file, String product) throws Exception;
}
