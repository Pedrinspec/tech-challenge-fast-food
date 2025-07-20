package fixture;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Product;
import com.fiap.fast_food_tc.application.dto.product.ProductRequest;
import com.fiap.fast_food_tc.application.dto.product.ProductResponse;
import com.fiap.fast_food_tc.domain.entity.EProduct;

import java.math.BigDecimal;

public class ProductFixture {



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


    public static ProductResponse createProductResponse() {
        return ProductResponse.builder()
                .productId(1)
                .name("Hamburguer")
                .description("Delicious hamburguer")
                .productValue(BigDecimal.ONE)
                .build();
    }

    public static ProductRequest createProductRequest() {
        return ProductRequest.builder()
                .name("Hamburguer")
                .description("Delicious hamburguer")
                .productValue(BigDecimal.ONE)
                .build();
    }

}
