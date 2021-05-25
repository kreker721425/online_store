package com.github.kreker721425.online_store.mapper;

import com.github.kreker721425.online_store.dto.ReviewDto;
import com.github.kreker721425.online_store.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends BaseMapper {

    @Mapping(source = "product", target = "product", qualifiedByName = "productDtoToProduct")
    @Mapping(source = "person", target = "person", qualifiedByName = "personDtoToPerson")
    Review toReview(ReviewDto reviewDto);

    @Mapping(source = "product", target = "product", qualifiedByName = "productToProductDto")
    @Mapping(source = "person", target = "person", qualifiedByName = "personToPersonDto")
    ReviewDto toReviewDto(Review review);
}
