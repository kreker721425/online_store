package com.github.kreker721425.online_store.mapper;

import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.model.Product;
import com.github.kreker721425.online_store.pojo.Category;
import com.github.kreker721425.online_store.pojo.Store;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper {

    /*ReviewMapper reviewMapper = new ReviewMapperImpl();
    PersonMapper personMapper = new PersonMapperImpl();
    OrderMapper orderMapper = new OrderMapperImpl();*/

    @Mapping(source = "reviews", target = "reviews", qualifiedByName = "reviewsDtoToReviews")
    @Mapping(source = "persons", target = "persons", qualifiedByName = "personsDtoToPersons")
    @Mapping(source = "orders", target = "orders", qualifiedByName = "ordersDtoToOrders")
    @Mapping(source = "category", target = "category", qualifiedByName = "categoryEnumToString")
    Product toProduct(ProductDto productDto);

    @Mapping(source = "reviews", target = "reviews", qualifiedByName = "reviewsToReviewsDto")
    @Mapping(source = "persons", target = "persons", qualifiedByName = "personsToPersonsDto")
    @Mapping(source = "orders", target = "orders", qualifiedByName = "ordersToOrdersDto")
    @Mapping(source = "category", target = "category", qualifiedByName = "categoryStringToEnum")
    ProductDto toProductDto(Product product);


    default String categoryEnumToString(Category category) {
        if (Objects.isNull(category)) return null;
        return category.getValue();
    }

    default Category categoryStringToEnum(String category) {
        if (Objects.isNull(category)) {
            return null;
        }
        return Arrays.stream(Category.values())
                .filter(value -> StringUtils.equals(value.getValue(), category))
                .findFirst()
                .orElse(null);
    }

}
