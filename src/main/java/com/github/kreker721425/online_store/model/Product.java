package com.github.kreker721425.online_store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.kreker721425.online_store.pojo.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    private String img;
    private String description;
    @Column(name = "count_product")
    private Long count;
    private String manufacturer;

    private String category;

    @ManyToMany
    @JoinTable(
            name = "person_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    @JsonIgnoreProperties("cart")
    private List<Person> persons = new ArrayList<>();

    @OneToMany(mappedBy="product", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("product")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnoreProperties("cart")
    private List<Order> orders = new ArrayList<>();
}
