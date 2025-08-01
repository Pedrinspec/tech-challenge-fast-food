package fixture;

import com.fiap.fast_food_tc.application.dto.product.in.ProductRequest;
import com.fiap.fast_food_tc.application.dto.product.out.ProductResponse;
import com.fiap.fast_food_tc.domain.entity.Product;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;

import java.math.BigDecimal;

public class ProductFixture {



    public static ProductPersistenceEntity createProduct() {
        return ProductPersistenceEntity.builder()
                .productId(1)
                .name("Hamburguer")
                .description("Delicious hamburguer")
                .productValue(BigDecimal.ONE)
                .build();
    }

    public static Product createEProduct() {
        return Product.builder()
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
