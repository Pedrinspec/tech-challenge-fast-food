package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.cross.mapper.CategoryMapper;
import com.fiap.fast_food_tc.domain.gateway.CategoryGateway;
import com.fiap.fast_food_tc.domain.usecase.impl.CategoryUseCaseImpl;
import fixture.CategoryFixture;
import com.fiap.fast_food_tc.domain.entity.ECategory;
import com.fiap.fast_food_tc.adapter.db.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
