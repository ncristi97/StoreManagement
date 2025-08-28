package org.example.service;

import org.example.domain.Product;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    @Test
    void testFindByIdNotFound() {
        ProductRepository repo = Mockito.mock(ProductRepository.class);
        Mockito.when(repo.findById(1L)).thenReturn(Optional.empty());
        ProductService service = new ProductService(repo);
        assertThrows(RuntimeException.class, () -> service.findById(1L));
    }
    @Test
    void testAdd() {
        ProductRepository repo = Mockito.mock(ProductRepository.class);
        Product p = new Product("Headphones", 175);
        Mockito.when(repo.save(p)).thenReturn(p);
        ProductService service = new ProductService(repo);
        assertEquals("Headphones", service.add(p).getName());
    }
}
