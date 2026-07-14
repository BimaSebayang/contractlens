package com.contractlens.service.analyzer.integration.merchant.service;

import com.payflow.paymentgateway.shared.dto.PaymentGatewayResponse;
import com.payflow.paymentgateway.shared.dto.PaymentRequest;
import com.payflow.paymentgateway.shared.enums.MerchantType;

public interface MerchantGateway {

    MerchantType getMerchantType();

    PaymentGatewayResponse process(PaymentRequest request);

    PaymentGatewayResponse refund(PaymentRequest request);

}
