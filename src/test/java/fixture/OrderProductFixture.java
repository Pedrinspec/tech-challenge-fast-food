package fixture;

import com.fiap.fast_food_tc.adapter.db.model.OrderProduct;
import com.fiap.fast_food_tc.adapter.db.model.Orders;
import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.db.model.ids.OrderProductPk;
import com.fiap.fast_food_tc.adapter.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.adapter.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.domain.entity.EOrderProduct;

import java.math.BigDecimal;

public class OrderProductFixture {

    public static OrderProduct createOrderProduct() {
        Orders order = OrdersFixture.createOrders();
        Product product = ProductFixture.createProduct();
        OrderProductPk pk = OrderProductPk.builder()
                .orderId(order.getOrderId())
                .productId(product.getProductId())
                .build();
        OrderProduct op = new OrderProduct();
        op.setId(pk);
        op.setOrders(order);
        op.setProduct(product);
        op.setProductQuantity(1);
        op.setProductTotalAmount(BigDecimal.ONE);
        return op;
    }

    public static EOrderProduct createEOrderProduct() {
        return EOrderProduct.builder()
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
