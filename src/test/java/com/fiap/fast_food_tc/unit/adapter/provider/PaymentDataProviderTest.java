package com.fiap.fast_food_tc.unit.adapter.provider;

import com.fiap.fast_food_tc.adapter.db.model.Payment;
import com.fiap.fast_food_tc.adapter.db.repository.PaymentRepository;
import com.fiap.fast_food_tc.adapter.provider.PaymentDataProvider;
import com.fiap.fast_food_tc.cross.enums.PaymentMethod;
import com.fiap.fast_food_tc.cross.enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentDataProviderTest {

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentDataProvider provider;

    @Test
    void saveSuccess() {
        Payment payment = Payment.builder()
                .paymentId(1)
                .paymentMethod(PaymentMethod.MERCADO_PAGO)
                .paymentStatus(PaymentStatus.PENDING)
                .paymentValue(BigDecimal.TEN)
                .createdAt(LocalDateTime.now())
                .build();
        when(repository.save(payment)).thenReturn(payment);

        var result = provider.save(payment);

        assertEquals(payment, result);
    }
}
