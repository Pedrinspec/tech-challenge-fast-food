package fixture;

import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;

import java.util.List;

public class CheckoutFixture {

    public static CheckoutOrderRequest createRequest() {
        CheckoutOrderRequest.Item item = CheckoutOrderRequest.Item.builder()
                .productId(1)
                .quantity(1)
                .build();
        return CheckoutOrderRequest.builder()
                .customerId(1)
                .items(List.of(item))
                .build();
    }

}
