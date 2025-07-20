package fixture;

import com.fiap.fast_food_tc.application.dto.category.CategoryRequest;
import com.fiap.fast_food_tc.application.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.domain.entity.Category;

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

    public static com.fiap.fast_food_tc.infrastructure.persistence.entity.Category createCategoryModel() {
        return com.fiap.fast_food_tc.infrastructure.persistence.entity.Category.builder()
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
