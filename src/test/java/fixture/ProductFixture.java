package fixture;

import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.cross.mapper.ProductMapper;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import com.fiap.fast_food_tc.domain.usecase.ProductUseCase;

import java.math.BigDecimal;

public class ProductFixture {

    private static ProductUseCase ProductUseCase;

    public static Product createProduct() {
        return Product.builder()
                .productId(1)
                .name("Hamburguer")
                .description("Delicious hamburguer")
                .productValue(BigDecimal.ONE)
                .build();
    }

    public static EProduct createEProduct() {
        return EProduct.builder()
                .productId(1)
                .name("Hamburguer")
                .description("Delicious hamburguer")
                .productValue(BigDecimal.ONE)
                .build();
    }


}
