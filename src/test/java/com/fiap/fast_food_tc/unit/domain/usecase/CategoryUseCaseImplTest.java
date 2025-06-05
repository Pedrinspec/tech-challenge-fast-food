package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.cross.mapper.CategoryMapper;
import com.fiap.fast_food_tc.domain.gateway.CategoryGateway;
import com.fiap.fast_food_tc.domain.usecase.impl.CategoryUseCaseImpl;
import fixture.CategoryFixture;
import com.fiap.fast_food_tc.domain.entity.ECategory;
import com.fiap.fast_food_tc.infra.db.model.Category;
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
class CategoryUseCaseImplTest {

    @Mock
    private CategoryGateway categoryGateway;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryUseCaseImpl useCase;

    @Test
    void getAllCategoriesSuccess() {
        List<Category> models = List.of(CategoryFixture.createCategoryModel());
        List<ECategory> entities = List.of(CategoryFixture.createECategory());

        Mockito.when(categoryGateway.findAllCategories()).thenReturn(models);
        Mockito.when(categoryMapper.toEntityList(models)).thenReturn(entities);

        var result = useCase.getAllCategories();

        assertEquals(entities, result);
    }


    @Test
    void createSuccess() {
        ECategory entity = CategoryFixture.createECategory();
        Category model = CategoryFixture.createCategoryModel();

        Mockito.when(categoryMapper.toModel(entity)).thenReturn(model);
        Mockito.when(categoryGateway.createCategory(model)).thenReturn(model);
        Mockito.when(categoryMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.create(entity);

        assertEquals(entity, result);
    }

    @Test
    void updateSuccess() {
        ECategory entity = CategoryFixture.createECategory();
        Category model = CategoryFixture.createCategoryModel();

        Mockito.when(categoryMapper.toModel(any())).thenReturn(model);
        Mockito.when(categoryGateway.updateCategory(model)).thenReturn(model);
        Mockito.when(categoryMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.update(1, entity);

        assertEquals(entity, result);
    }
}
