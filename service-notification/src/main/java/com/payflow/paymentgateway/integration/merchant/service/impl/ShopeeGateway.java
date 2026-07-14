package com.payflow.paymentgateway.integration.merchant.service.impl;

import com.payflow.paymentgateway.integration.merchant.service.MerchantGateway;
import com.payflow.paymentgateway.shared.dto.PaymentGatewayResponse;
import com.payflow.paymentgateway.shared.dto.PaymentRequest;
import com.payflow.paymentgateway.shared.enums.MerchantType;
import com.payflow.paymentgateway.shared.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class ShopeeGateway implements MerchantGateway {
    @Override
    public MerchantType getMerchantType() {
        return MerchantType.TOKOPEDIA;
    }

    @Override
    public PaymentGatewayResponse process(PaymentRequest request) {

        return PaymentGatewayResponse.builder()
                .status(PaymentStatus.SUCCESS)
                .message("Success")
                .build();

    }

    @Override
    public PaymentGatewayResponse refund(PaymentRequest request) {
        return PaymentGatewayResponse.builder()
                .status(PaymentStatus.SUCCESS)
                .message("Success")
                .build();
    }
}
