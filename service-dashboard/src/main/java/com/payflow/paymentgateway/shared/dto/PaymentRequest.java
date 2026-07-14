package com.payflow.paymentgateway.shared.dto;


import com.payflow.paymentgateway.shared.enums.Currency;
import com.payflow.paymentgateway.shared.enums.MerchantType;
import com.payflow.paymentgateway.shared.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PaymentRequest {

    @NotNull
    private MerchantType merchantType;

    @NotNull
    private Long userId;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    private PaymentMethod paymentMethod;

    private String description;

}
