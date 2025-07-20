package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.CategoryPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.CategoryMapper;
import com.fiap.fast_food_tc.application.gateway.CategoryGateway;
import com.fiap.fast_food_tc.application.usecase.impl.CategoryUseCaseImpl;
import fixture.CategoryFixture;
import com.fiap.fast_food_tc.domain.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryPersistenceEntityUseCaseImplTest {

    @Mock
    private CategoryGateway categoryGateway;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryUseCaseImpl useCase;

    @Test
    void getAllCategoriesSuccess() {
        List<CategoryPersistenceEntity> models = List.of(CategoryFixture.createCategoryModel());
        List<Category> entities = List.of(CategoryFixture.createECategory());

        Mockito.when(categoryGateway.findAllCategories()).thenReturn(models);
        Mockito.when(categoryMapper.toEntityList(models)).thenReturn(entities);

        var result = useCase.getAllCategories();

        assertEquals(entities, result);
    }


    @Test
    void createSuccess() {
        Category entity = CategoryFixture.createECategory();
        CategoryPersistenceEntity model = CategoryFixture.createCategoryModel();

        Mockito.when(categoryMapper.toModel(entity)).thenReturn(model);
        Mockito.when(categoryGateway.createCategory(model)).thenReturn(model);
        Mockito.when(categoryMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.create(entity);

        assertEquals(entity, result);
    }

    @Test
    void updateSuccess() {
        Category entity = CategoryFixture.createECategory();
        CategoryPersistenceEntity model = CategoryFixture.createCategoryModel();

        Mockito.when(categoryMapper.toModel(any())).thenReturn(model);
        Mockito.when(categoryGateway.updateCategory(model)).thenReturn(model);
        Mockito.when(categoryMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.update(1, entity);

        assertEquals(entity, result);
    }
}
