package com.fiap.fast_food_tc.unit.infra.provider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.CategoryPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.CategoryRepository;
import com.fiap.fast_food_tc.infrastructure.persistence.dataprovider.CategoryDataProvider;
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
class CategoryPersistenceEntityDataProviderTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryDataProvider provider;

    @Test
    void findAllCategoriesSuccess() {
        List<CategoryPersistenceEntity> categories = List.of(CategoryFixture.createCategoryModel());
        when(repository.findAll()).thenReturn(categories);

        var result = provider.findAllCategories();

        assertEquals(categories, result);
    }

    @Test
    void findCategoryByIdSuccess() {
        CategoryPersistenceEntity categoryPersistenceEntity = CategoryFixture.createCategoryModel();
        when(repository.findById(1)).thenReturn(Optional.of(categoryPersistenceEntity));

        var result = provider.findCategoryById(1);

        assertEquals(categoryPersistenceEntity, result);
    }

    @Test
    void createCategorySuccess() {
        CategoryPersistenceEntity categoryPersistenceEntity = CategoryFixture.createCategoryModel();
        when(repository.save(categoryPersistenceEntity)).thenReturn(categoryPersistenceEntity);

        var result = provider.createCategory(categoryPersistenceEntity);

        assertEquals(categoryPersistenceEntity, result);
    }

    @Test
    void deleteCategorySuccess() {
        provider.deleteCategory(1);

        verify(repository).deleteById(1);
    }

    @Test
    void updateCategorySuccess() {
        CategoryPersistenceEntity categoryPersistenceEntity = CategoryFixture.createCategoryModel();
        when(repository.save(categoryPersistenceEntity)).thenReturn(categoryPersistenceEntity);

        var result = provider.updateCategory(categoryPersistenceEntity);

        assertEquals(categoryPersistenceEntity, result);
    }
}
