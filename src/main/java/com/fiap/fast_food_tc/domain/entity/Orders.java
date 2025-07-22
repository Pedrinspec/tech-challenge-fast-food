package com.fiap.fast_food_tc.domain.entity;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    private Integer orderId;
    private LocalDateTime orderDatetime;
    private StatusOrder statusOrder;
    private Short orderCode;
    private BigDecimal totalAmount;
    private Integer customerId;

    public void confirmPayment() {
        if (this.statusOrder != StatusOrder.PAYMENT_PENDING) {
            throw new IllegalStateException("Invalid action. Payment is not pending. Current status: " + this.statusOrder);
        }
        this.statusOrder = StatusOrder.RECEIVED;
    }

    public void startPreparation() {
        if (this.statusOrder != StatusOrder.RECEIVED) {
            throw new IllegalStateException("Invalid action. Order has not been received yet. Current status: " + this.statusOrder);
        }
        this.statusOrder = StatusOrder.IN_PREPARATION;
    }

    public void readyForPickup() {
        if (this.statusOrder != StatusOrder.IN_PREPARATION) {
            throw new IllegalStateException("Invalid action. Order is not in preparation. Current status: " + this.statusOrder);
        }
        this.statusOrder = StatusOrder.READY_FOR_PICKUP;
    }

    public void finishOrder() {
        if (this.statusOrder != StatusOrder.READY_FOR_PICKUP) {
            throw new IllegalStateException("Invalid action. Order is not ready for pickup. Current status: " + this.statusOrder);
        }
        this.statusOrder = StatusOrder.FINISHED;
    }

    public void cancelOrder() {
        if (this.statusOrder == StatusOrder.FINISHED) {
            throw new IllegalStateException("Cannot cancel an order that has already been finished.");
        }
        this.statusOrder = StatusOrder.CANCELED;
    }
}
