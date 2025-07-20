package fixture;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Category;
import com.fiap.fast_food_tc.application.dto.category.CategoryRequest;
import com.fiap.fast_food_tc.application.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.domain.entity.ECategory;

public class CategoryFixture {

    public static ECategory createECategory() {
        return ECategory.builder()
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

    public static Category createCategoryModel() {
        return Category.builder()
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
