package com.github.kreker721425.online_store.repository;

import com.github.kreker721425.online_store.model.Order;
import com.github.kreker721425.online_store.model.Person;
import com.github.kreker721425.online_store.model.Product;
import com.github.kreker721425.online_store.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p order by p.id desc limit ?1 offset ?2", nativeQuery=true)
    List<Product> findAny(Long begin, Long limit);

    Product findByReviews(Review review);

    List<Product> findByPersons(Person person);

    List<Product> findByOrders(Order order);
}
