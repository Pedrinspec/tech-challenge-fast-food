package com.fiap.fast_food_tc.unit.infra.provider;

import com.fiap.fast_food_tc.infra.db.model.Category;
import com.fiap.fast_food_tc.infra.db.repository.CategoryRepository;
import com.fiap.fast_food_tc.infra.provider.CategoryDataProvider;
import fixture.CategoryFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryDataProviderTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryDataProvider provider;

    @Test
    void findAllCategoriesSuccess() {
        List<Category> categories = List.of(CategoryFixture.createCategoryModel());
        when(repository.findAll()).thenReturn(categories);

        var result = provider.findAllCategories();

        assertEquals(categories, result);
    }

    @Test
    void findCategoryByIdSuccess() {
        Category category = CategoryFixture.createCategoryModel();
        when(repository.findById(1)).thenReturn(Optional.of(category));

        var result = provider.findCategoryById(1);

        assertEquals(category, result);
    }

    @Test
    void createCategorySuccess() {
        Category category = CategoryFixture.createCategoryModel();
        when(repository.save(category)).thenReturn(category);

        var result = provider.createCategory(category);

        assertEquals(category, result);
    }

    @Test
    void deleteCategorySuccess() {
        provider.deleteCategory(1);

        verify(repository).deleteById(1);
    }

    @Test
    void updateCategorySuccess() {
        Category category = CategoryFixture.createCategoryModel();
        when(repository.save(category)).thenReturn(category);

        var result = provider.updateCategory(category);

        assertEquals(category, result);
    }
}
