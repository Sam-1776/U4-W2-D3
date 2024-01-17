package it.sam.be.classi;

import it.sam.be.categorie.Categorie;

public class Product {
    public Long id;
    public String name;
    public Categorie category;
    public Double price;

    public Product(Long id, String name, Categorie category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
