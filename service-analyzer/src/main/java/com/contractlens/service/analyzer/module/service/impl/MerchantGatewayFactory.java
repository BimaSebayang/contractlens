package com.contractlens.service.analyzer.module.service.impl;

import com.contractlens.service.analyzer.integration.merchant.service.MerchantGateway;
import com.payflow.paymentgateway.shared.enums.MerchantType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MerchantGatewayFactory {

    private final List<MerchantGateway> gateways;

    public MerchantGateway getGateway(MerchantType merchantType) {

        return gateways.stream()
                .filter(gateway -> gateway.getMerchantType() == merchantType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

    }

}
