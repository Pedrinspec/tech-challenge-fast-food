package fixture;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.Product;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;

import java.math.BigDecimal;

public class OrderProductFixture {

    public static com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct createOrderProduct() {
        Orders order = OrdersFixture.createOrders();
        Product product = ProductFixture.createProduct();
        OrderProductPk pk = OrderProductPk.builder()
                .orderId(order.getOrderId())
                .productId(product.getProductId())
                .build();
        com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct op = new com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct();
        op.setId(pk);
        op.setOrders(order);
        op.setProduct(product);
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
