package fixture;

import com.fiap.fast_food_tc.adapter.db.model.Category;
import com.fiap.fast_food_tc.domain.entity.ECategory;

public class CategoryFixture {

    public static Category createCategory() {
        return Category.builder()
                .categoryId(1)
                .categoryName("Burgers")
                .categoryDescription("Delicious burgers")
                .build();
    }

    public static ECategory createECategory() {
        return ECategory.builder()
                .categoryId(1)
                .categoryName("Burgers")
                .categoryDescription("Delicious burgers")
                .build();
    }
}
