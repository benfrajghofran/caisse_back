package caisse_back.services;

import caisse_back.dto.ProductDto;
import caisse_back.mapper.ProductMapper;
import caisse_back.models.Product;
import caisse_back.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.boot.json.JsonParseException;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    ServletContext context;

    public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/webapp/imagedata";

    @Override
    public List<ProductDto> findAll() {
        return this.productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Product save(Long id,MultipartFile file, String product) throws JsonParseException,Exception,JsonMappingException{
        Product productupdate=new ObjectMapper().readValue(product,Product.class);
        productupdate.setId(id);
        if(productupdate.getName()!=""){
            String filename= file.getOriginalFilename();
            String newFileName= FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
            FileOutputStream output=new FileOutputStream(uploadDirectory+ File.separator+newFileName);
            output.write(file.getBytes());
            productupdate.setImage(newFileName);
        }
        return this.productRepository.save(productupdate);
    }

    @Override
    public ProductDto findById(Long id) {
        return  this.productRepository.findById(id).map(productMapper::toDTO).get();
    }

    @Override
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public byte[] getPhoto(Long id) throws Exception {
        ProductDto productDto  = this.productRepository.findById(id).map(productMapper::toDTO).get();
        System.out.println("C:/Users/Dell/Desktop/caisse_back/src/main/webapp/imagedata/"+productDto.getImage());
        return Files.readAllBytes(Paths.get("C:/Users/Dell/Desktop/caisse_back/src/main/webapp/imagedata/"+productDto.getImage()));
    }

    @Override
    public ProductDto create(MultipartFile file, String product) throws JsonParseException,Exception,JsonMappingException{

        ProductDto productDto=new ObjectMapper().readValue(product,ProductDto.class);
        if(productDto.getName()!=""){
        String filename= file.getOriginalFilename();
        String newFileName= FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        FileOutputStream output=new FileOutputStream(uploadDirectory+ File.separator+newFileName);
        output.write(file.getBytes());
        productDto.setImage(newFileName);
        }

        Product product1 = productRepository.save(Product.builder()
        .name(productDto.getName())
        .description(productDto.getDescription())
        .category(productDto.getCategory())
        .price(productDto.getPrice())
        .image(productDto.getImage())
        .build());

        return productMapper.toDTO(product1);
    }
}
