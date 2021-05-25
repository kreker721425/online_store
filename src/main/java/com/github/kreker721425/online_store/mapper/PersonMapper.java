package com.github.kreker721425.online_store.mapper;

import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper {

    /*ReviewMapper reviewMapper = new ReviewMapperImpl();
    ProductMapper productMapper = new ProductMapperImpl();
    OrderMapper orderMapper = new OrderMapperImpl();*/

    @Mapping(source = "reviews", target = "reviews", qualifiedByName = "reviewsDtoToReviews")
    @Mapping(source = "cart", target = "cart", qualifiedByName = "productsDtoToProducts")
    @Mapping(source = "orders", target = "orders", qualifiedByName = "ordersDtoToOrders")
    Person toPerson(PersonDto personDto);

    @Mapping(source = "reviews", target = "reviews", qualifiedByName = "reviewsToReviewsDto")
    @Mapping(source = "cart", target = "cart", qualifiedByName = "productsToProductsDto")
    @Mapping(source = "orders", target = "orders", qualifiedByName = "ordersToOrdersDto")
    PersonDto toPersonDto(Person person);

    /*default List<Review> reviewsDtoToReviews(List<ReviewDto> reviewsDto) {
        return reviewsDto.stream()
                .map(reviewMapper::toReview)
                .collect(Collectors.toList());
    }

    default List<ReviewDto> reviewsToReviewsDto(List<Review> reviews) {
        return reviews.stream()
                .map(reviewMapper::toReviewDto)
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



    default List<Order> ordersDtoToOrders(List<OrderDto> ordersDto) {
        return ordersDto.stream()
                .map(orderMapper::toOrder)
                .collect(Collectors.toList());
    }

    default List<OrderDto> ordersToOrdersDto(List<Order> orders) {
        return orders.stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }*/
}
