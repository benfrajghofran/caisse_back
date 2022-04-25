package caisse_back.controllers;

import caisse_back.dto.ProductDto;
import caisse_back.models.Product;
import caisse_back.services.ProductServiceImpl;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductDto> getProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id){
        return productService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestParam("file") MultipartFile file, @RequestParam("product") String product) throws JsonParseException,Exception, JsonMappingException {
        log.info("Rest request to create product");
        ProductDto productDto= productService.create(file,product);
        return ResponseEntity.created(new URI("/api/products")).body(productDto);
    }
    @GetMapping("/image/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        return productService.getPhoto(id);
    }
    @PutMapping("/{id}")
    public Product save(@PathVariable Long id,@RequestParam("file") MultipartFile file, @RequestParam("product") String product) throws JsonParseException,Exception, JsonMappingException{
        return productService.save(id,file,product) ;
    }
}
