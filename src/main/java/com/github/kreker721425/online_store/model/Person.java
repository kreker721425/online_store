package com.github.kreker721425.online_store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
@Accessors(chain = true)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "person_product",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnoreProperties("persons")
    private List<Product> cart = new ArrayList<>();

    @OneToMany(mappedBy="person", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("person")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy="person", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("person")
    private List<Order> orders = new ArrayList<>();
}
