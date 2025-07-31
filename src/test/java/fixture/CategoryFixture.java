package fixture;

import com.fiap.fast_food_tc.application.dto.category.in.CategoryRequest;
import com.fiap.fast_food_tc.application.dto.category.out.CategoryResponseDTO;
import com.fiap.fast_food_tc.domain.entity.Category;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.CategoryPersistenceEntity;

public class CategoryFixture {

    public static Category createECategory() {
        return Category.builder()
                .categoryId(1)
                .categoryName("Burgers")
                .categoryDescription("Delicious burgers")
                .build();
    }

    public static CategoryResponseDTO createCategoryDTO() {
        return CategoryResponseDTO.builder()
                .categoryId(1)
                .categoryName("Burgers")
                .categoryDescription("Delicious burgers")
                .build();
    }

    public static CategoryPersistenceEntity createCategoryModel() {
        return CategoryPersistenceEntity.builder()
                .categoryId(1)
                .categoryName("Burgers")
                .categoryDescription("Delicious burgers")
                .build();
    }

    public static CategoryRequest createCategoryRequest() {
        return CategoryRequest.builder()
                .categoryName("Burgers")
                .categoryDescription("Delicious burgers")
                .build();
    }
}
