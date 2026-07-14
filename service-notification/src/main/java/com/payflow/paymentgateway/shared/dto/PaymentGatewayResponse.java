package com.payflow.paymentgateway.shared.dto;

import com.payflow.paymentgateway.shared.enums.PaymentStatus;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
public class PaymentGatewayResponse {
    private PaymentStatus status;
    private String message;
}
