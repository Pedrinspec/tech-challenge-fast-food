package com.fiap.fast_food_tc.unit.cross.mapper;

import com.fiap.fast_food_tc.adapter.db.model.Category;
import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.dto.product.ProductRequest;
import com.fiap.fast_food_tc.adapter.dto.product.ProductResponse;
import com.fiap.fast_food_tc.cross.mapper.ProductMapper;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void toResponseSuccess() {
        EProduct eProduct = EProduct.builder()
                .productId(1)
                .name("Burger")
                .quantity(2)
                .productValue(BigDecimal.ONE)
                .isAvailable(true)
                .categoryId(1)
                .description("test")
                .imagePath("img")
                .build();

        ProductResponse response = mapper.toResponse(eProduct);

        assertEquals(eProduct.getProductId(), response.getProductId());
        assertEquals(eProduct.getName(), response.getName());
        assertEquals(eProduct.getQuantity(), response.getQuantity());
        assertEquals(eProduct.getProductValue(), response.getProductValue());
        assertEquals(eProduct.getIsAvailable(), response.getIsAvailable());
        assertEquals(eProduct.getCategoryId(), response.getCategoryId());
        assertEquals(eProduct.getDescription(), response.getDescription());
        assertEquals(eProduct.getImagePath(), response.getImagePath());
    }

    @Test
    void toModelSuccess() {
        EProduct eProduct = EProduct.builder()
                .productId(1)
                .name("Burger")
                .quantity(2)
                .productValue(BigDecimal.ONE)
                .isAvailable(true)
                .categoryId(1)
                .description("test")
                .imagePath("img")
                .build();

        Product model = mapper.toModel(eProduct);

        assertEquals(eProduct.getProductId(), model.getProductId());
        assertEquals(eProduct.getName(), model.getName());
        assertEquals(eProduct.getQuantity(), model.getQuantity());
        assertEquals(eProduct.getProductValue(), model.getProductValue());
        assertEquals(eProduct.getIsAvailable(), model.getIsAvailable());
        assertEquals(eProduct.getDescription(), model.getDescription());
        assertEquals(eProduct.getImagePath(), model.getImagePath());
        assertNull(model.getOrderProducts());
    }

    @Test
    void toEntitySuccess() {
        Category category = Category.builder().categoryId(2).build();
        Product product = Product.builder()
                .productId(1)
                .name("Burger")
                .quantity(2)
                .productValue(BigDecimal.ONE)
                .isAvailable(true)
                .description("test")
                .imagePath("img")
                .category(category)
                .build();

        EProduct entity = mapper.toEntity(product);

        assertEquals(product.getProductId(), entity.getProductId());
        assertEquals(product.getName(), entity.getName());
        assertEquals(product.getQuantity(), entity.getQuantity());
        assertEquals(product.getProductValue(), entity.getProductValue());
        assertEquals(product.getIsAvailable(), entity.getIsAvailable());
        assertEquals(product.getDescription(), entity.getDescription());
        assertEquals(product.getImagePath(), entity.getImagePath());
        assertEquals(category.getCategoryId(), entity.getCategoryId());
    }

    @Test
    void toEntityCreateSuccess() {
        ProductRequest request = ProductRequest.builder()
                .name("Burger")
                .quantity(2)
                .productValue(BigDecimal.ONE)
                .isAvailable(true)
                .categoryId(5)
                .description("test")
                .imagePath("img")
                .build();

        EProduct entity = mapper.toEntityCreate(request);

        assertNull(entity.getProductId());
        assertEquals(request.getName(), entity.getName());
        assertEquals(request.getQuantity(), entity.getQuantity());
        assertEquals(request.getProductValue(), entity.getProductValue());
        assertEquals(request.getIsAvailable(), entity.getIsAvailable());
        assertEquals(request.getCategoryId(), entity.getCategoryId());
        assertEquals(request.getDescription(), entity.getDescription());
        assertEquals(request.getImagePath(), entity.getImagePath());
    }

    @Test
    void toResponseListSuccess() {
        EProduct eProduct = EProduct.builder()
                .productId(1)
                .name("Burger")
                .quantity(2)
                .productValue(BigDecimal.ONE)
                .isAvailable(true)
                .categoryId(1)
                .description("test")
                .imagePath("img")
                .build();

        List<ProductResponse> responses = mapper.toResponseList(List.of(eProduct));

        assertEquals(1, responses.size());
        assertEquals(eProduct.getProductId(), responses.get(0).getProductId());
    }

    @Test
    void toEntityListSuccess() {
        Product product = Product.builder()
                .productId(1)
                .name("Burger")
                .quantity(2)
                .productValue(BigDecimal.ONE)
                .isAvailable(true)
                .description("test")
                .imagePath("img")
                .build();

        List<EProduct> entities = mapper.toEntityList(List.of(product));

        assertEquals(1, entities.size());
        assertEquals(product.getProductId(), entities.get(0).getProductId());
    }
}
