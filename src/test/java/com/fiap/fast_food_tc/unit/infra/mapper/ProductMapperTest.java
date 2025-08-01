package com.fiap.fast_food_tc.unit.infra.mapper;

import com.fiap.fast_food_tc.domain.entity.Product;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.CategoryPersistenceEntity;
import com.fiap.fast_food_tc.application.dto.product.in.ProductRequest;
import com.fiap.fast_food_tc.application.dto.product.out.ProductResponse;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.web.mapper.ProductMapper;
import fixture.CategoryFixture;
import fixture.ProductFixture;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void toResponseSuccess() {
        Product product = ProductFixture.createEProduct();
        product.setQuantity(2);
        product.setIsAvailable(true);
        product.setCategoryId(1);
        product.setDescription("test");
        product.setImagePath("img");

        ProductResponse response = mapper.toResponse(product);

        assertEquals(product.getProductId(), response.getProductId());
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getQuantity(), response.getQuantity());
        assertEquals(product.getProductValue(), response.getProductValue());
        assertEquals(product.getIsAvailable(), response.getIsAvailable());
        assertEquals(product.getCategoryId(), response.getCategoryId());
        assertEquals(product.getDescription(), response.getDescription());
        assertEquals(product.getImagePath(), response.getImagePath());
    }

    @Test
    void toModelSuccess() {
        Product product = ProductFixture.createEProduct();
        product.setQuantity(2);
        product.setIsAvailable(true);
        product.setCategoryId(1);
        product.setDescription("test");
        product.setImagePath("img");

        ProductPersistenceEntity model = mapper.toModel(product);

        assertEquals(product.getProductId(), model.getProductId());
        assertEquals(product.getName(), model.getName());
        assertEquals(product.getQuantity(), model.getQuantity());
        assertEquals(product.getProductValue(), model.getProductValue());
        assertEquals(product.getIsAvailable(), model.getIsAvailable());
        assertEquals(product.getDescription(), model.getDescription());
        assertEquals(product.getImagePath(), model.getImagePath());
        assertNull(model.getOrderProductPersistenceEntities());
    }

    @Test
    void toEntitySuccess() {
        CategoryPersistenceEntity categoryPersistenceEntity = CategoryFixture.createCategoryModel();
        ProductPersistenceEntity productPersistenceEntity = ProductFixture.createProduct();
        productPersistenceEntity.setQuantity(2);
        productPersistenceEntity.setIsAvailable(true);
        productPersistenceEntity.setDescription("test");
        productPersistenceEntity.setImagePath("img");
        productPersistenceEntity.setCategoryPersistenceEntity(categoryPersistenceEntity);

        Product entity = mapper.toEntity(productPersistenceEntity);

        assertEquals(productPersistenceEntity.getProductId(), entity.getProductId());
        assertEquals(productPersistenceEntity.getName(), entity.getName());
        assertEquals(productPersistenceEntity.getQuantity(), entity.getQuantity());
        assertEquals(productPersistenceEntity.getProductValue(), entity.getProductValue());
        assertEquals(productPersistenceEntity.getIsAvailable(), entity.getIsAvailable());
        assertEquals(productPersistenceEntity.getDescription(), entity.getDescription());
        assertEquals(productPersistenceEntity.getImagePath(), entity.getImagePath());
        assertEquals(categoryPersistenceEntity.getCategoryId(), entity.getCategoryId());
    }

    @Test
    void toEntityCreateSuccess() {
        ProductRequest request = ProductFixture.createProductRequest();
        request.setQuantity(2);
        request.setIsAvailable(true);
        request.setCategoryId(5);
        request.setDescription("test");
        request.setImagePath("img");

        Product entity = mapper.toEntityCreate(request);

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
        Product product = ProductFixture.createEProduct();
        product.setQuantity(2);
        product.setIsAvailable(true);
        product.setCategoryId(1);
        product.setDescription("test");
        product.setImagePath("img");

        List<ProductResponse> responses = mapper.toResponseList(List.of(product));

        assertEquals(1, responses.size());
        assertEquals(product.getProductId(), responses.getFirst().getProductId());
    }

    @Test
    void toEntityListSuccess() {
        ProductPersistenceEntity productPersistenceEntity = ProductFixture.createProduct();
        productPersistenceEntity.setQuantity(2);
        productPersistenceEntity.setIsAvailable(true);
        productPersistenceEntity.setDescription("test");
        productPersistenceEntity.setImagePath("img");

        List<Product> entities = mapper.toEntityList(List.of(productPersistenceEntity));

        assertEquals(1, entities.size());
        assertEquals(productPersistenceEntity.getProductId(), entities.getFirst().getProductId());
    }
}
