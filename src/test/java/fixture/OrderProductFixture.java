package fixture;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;

import java.math.BigDecimal;

public class OrderProductFixture {

    public static OrderProductPersistenceEntity createOrderProduct() {
        OrdersPersistenceEntity order = OrdersFixture.createOrders();
        ProductPersistenceEntity productPersistenceEntity = ProductFixture.createProduct();
        OrderProductPk pk = OrderProductPk.builder()
                .orderId(order.getOrderId())
                .productId(productPersistenceEntity.getProductId())
                .build();
        OrderProductPersistenceEntity op = new OrderProductPersistenceEntity();
        op.setId(pk);
        op.setOrdersPersistenceEntity(order);
        op.setProductPersistenceEntity(productPersistenceEntity);
        op.setProductQuantity(1);
        op.setProductTotalAmount(BigDecimal.ONE);
        return op;
    }

    public static OrderProduct createEOrderProduct() {
        return OrderProduct.builder()
                .orderId(1)
                .productId(1)
                .productQuantity(1)
                .productTotalAmount(BigDecimal.ONE)
                .build();
    }

    public static OrderProductRequestDto createRequest() {
        return OrderProductRequestDto.builder()
                .orderId(1)
                .productId(1)
                .productQuantity(1)
                .productTotalAmount(BigDecimal.ONE)
                .build();
    }

    public static OrderProductResponseDto createResponse() {
        return OrderProductResponseDto.builder()
                .orderId(1)
                .productId(1)
                .productQuantity(1)
                .productTotalAmount(BigDecimal.ONE)
                .build();
    }
}
