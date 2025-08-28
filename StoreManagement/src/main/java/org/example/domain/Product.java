package org.example.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @Min(0) private int price;
    public Product() {}
    public Product(String name, int price) {this.name=name;this.price=price;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}
    public String getName() {return name;}
    public void setName(String name) {this.name=name;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price=price;}
}
