package com.github.kreker721425.online_store.repository;

import com.github.kreker721425.online_store.model.Order;
import com.github.kreker721425.online_store.model.Person;
import com.github.kreker721425.online_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select p from Order p order by p.id desc limit ?1 offset ?2", nativeQuery=true)
    List<Order> findAny(Long begin, Long limit);

    List<Order> findByCart(Product product);

    List<Order> findByPerson(Person person);
}
