package com.github.kreker721425.online_store.repository;

import com.github.kreker721425.online_store.model.Order;
import com.github.kreker721425.online_store.model.Person;
import com.github.kreker721425.online_store.model.Product;
import com.github.kreker721425.online_store.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "select p from Person p order by p.id desc limit ?1 offset ?2", nativeQuery=true)
    List<Person> findAny(Long begin, Long limit);

    Person findByReviews(Review review);

    List<Person> findByCart(Product product);

    Person findByOrders(Order order);
}
