package com.example.online_store.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 30)
    private String name;

    @Column(name = "brand", nullable = false, length = 30)
    private String brand;

    @Column(name = "made_in", nullable = false, length = 30)
    private String madeIn;

    @Column(name = "price", nullable = false, length = 15)
    private double price;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    transient private Long noId;

    public Product() {
    }

    public Product(String name, String brand, String madeIn, double price, String image, String description, Category category) {
        this.name = name;
        this.brand = brand;
        this.madeIn = madeIn;
        this.price = price;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getNoId() {
        return noId;
    }

    public void setNoId(Long noId) {
        this.noId = noId;
    }
}
