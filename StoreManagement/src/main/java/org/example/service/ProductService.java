package org.example.service;
import org.example.domain.Product;
import org.example.error.NotFoundException;
import org.example.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) {this.repo = repo;}
    public List<Product> findAll() {log.info("Fetching all products");return repo.findAll();}
    public Product findById(Long id) {log.info("Fetching product {}",id);return repo.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));}
    public Product add(Product p) {log.info("Adding product {}",p.getName());return repo.save(p);}
    public void delete(Long id) {log.warn("Deleting product {}",id);repo.deleteById(id);}
    public Product changeName(Long id, String name) {Product p=findById(id);p.setName(name);return repo.save(p);}
    public Product changePrice(Long id, int price) {Product p=findById(id);p.setPrice(price);return repo.save(p);}
}
