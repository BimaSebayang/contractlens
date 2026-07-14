package com.payflow.paymentgateway.shared.dto;


import com.payflow.paymentgateway.shared.enums.Currency;
import com.payflow.paymentgateway.shared.enums.MerchantType;
import com.payflow.paymentgateway.shared.enums.PaymentMethod;
import com.payflow.paymentgateway.shared.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String referenceNo;
    private PaymentStatus status;

    private MerchantType merchantType;

    private Long userId;

    private BigDecimal amount;


    private Currency currency;

    private PaymentMethod paymentMethod;

    private String description;

    private LocalDateTime transactionTime;
}
