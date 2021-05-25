package com.github.kreker721425.online_store.mapper;

import com.github.kreker721425.online_store.dto.OrderDto;
import com.github.kreker721425.online_store.model.Order;
import com.github.kreker721425.online_store.pojo.StatusOrder;
import com.github.kreker721425.online_store.pojo.Store;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.Objects;


@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper {

    /*PersonMapper personMapper = new PersonMapperImpl();
    ProductMapper productMapper = new ProductMapperImpl();*/

    @Mapping(source = "status", target = "status", qualifiedByName = "statusEnumToString")
    @Mapping(source = "store", target = "store", qualifiedByName = "storeEnumToString")
    @Mapping(source = "person", target = "person", qualifiedByName = "personDtoToPerson")
    @Mapping(source = "cart", target = "cart", qualifiedByName = "productsDtoToProducts")
    Order toOrder(OrderDto orderDto);

    @Mapping(source = "status", target = "status", qualifiedByName = "statusStringToEnum")
    @Mapping(source = "store", target = "store", qualifiedByName = "storeStringToEnum")
    @Mapping(source = "person", target = "person", qualifiedByName = "personToPersonDto")
    @Mapping(source = "cart", target = "cart", qualifiedByName = "productsToProductsDto")
    OrderDto toOrderDto(Order order);

    /*default List<Product> productsDtoToProducts(List<ProductDto> productsDto) {
        return productsDto.stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());
    }

    default List<ProductDto> productsToProductsDto(List<Product> products) {
        return products.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }



    default Person personDtoToPerson(PersonDto personDto) {
        return personMapper.toPerson(personDto);
    }

    default PersonDto personToPersonDto(Person person) {
        return personMapper.toPersonDto(person);
    }*/



    default String statusEnumToString(StatusOrder status) {
        if (Objects.isNull(status)) return null;
        return status.getValue();
    }

    default StatusOrder statusStringToEnum(String status) {
        if (Objects.isNull(status)) {
            return null;
        }
        return Arrays.stream(StatusOrder.values())
                .filter(value -> StringUtils.equals(value.getValue(), status))
                .findFirst()
                .orElse(null);
    }



    default String storeEnumToString(Store store) {
        if (Objects.isNull(store)) return null;
        return store.getValue();
    }

    default Store storeStringToEnum(String store) {
        if (Objects.isNull(store)) {
            return null;
        }
        return Arrays.stream(Store.values())
                .filter(value -> StringUtils.equals(value.getValue(), store))
                .findFirst()
                .orElse(null);
    }
}
