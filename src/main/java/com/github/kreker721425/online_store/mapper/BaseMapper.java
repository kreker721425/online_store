package com.github.kreker721425.online_store.mapper;

import com.github.kreker721425.online_store.dto.OrderDto;
import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;
import com.github.kreker721425.online_store.model.Order;
import com.github.kreker721425.online_store.model.Person;
import com.github.kreker721425.online_store.model.Product;
import com.github.kreker721425.online_store.model.Review;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper {

    ReviewMapper reviewMapper = new ReviewMapperImpl();
    ProductMapper productMapper = new ProductMapperImpl();
    OrderMapper orderMapper = new OrderMapperImpl();
    PersonMapper personMapper = new PersonMapperImpl();

    default Person personDtoToPerson(PersonDto personDto) {
        return personMapper.toPerson(personDto);
    }

    default PersonDto personToPersonDto(Person person) {
        return personMapper.toPersonDto(person);
    }



    default Product productDtoToProduct(ProductDto productDto) {
        return productMapper.toProduct(productDto);
    }

    default ProductDto productToProductDto(Product product) {
        return productMapper.toProductDto(product);
    }



    default List<Person> personsDtoToPersons(List<PersonDto> personsDto) {
        return personsDto.stream()
                .map(personMapper::toPerson)
                .collect(Collectors.toList());
    }

    default List<PersonDto> personsToPersonsDto(List<Person> persons) {
        return persons.stream()
                .map(personMapper::toPersonDto)
                .collect(Collectors.toList());
    }



    default List<Product> productsDtoToProducts(List<ProductDto> productsDto) {
        return productsDto.stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());
    }

    default List<ProductDto> productsToProductsDto(List<Product> products) {
        return products.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }



    default List<Review> reviewsDtoToReviews(List<ReviewDto> reviewsDto) {
        return reviewsDto.stream()
                .map(reviewMapper::toReview)
                .collect(Collectors.toList());
    }

    default List<ReviewDto> reviewsToReviewsDto(List<Review> reviews) {
        return reviews.stream()
                .map(reviewMapper::toReviewDto)
                .collect(Collectors.toList());
    }



    default List<Order> ordersDtoToOrders(List<OrderDto> ordersDto) {
        return ordersDto.stream()
                .map(orderMapper::toOrder)
                .collect(Collectors.toList());
    }

    default List<OrderDto> ordersToOrdersDto(List<Order> orders) {
        return orders.stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }
}
