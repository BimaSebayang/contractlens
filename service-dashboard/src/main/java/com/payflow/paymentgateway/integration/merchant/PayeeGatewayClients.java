package com.payflow.paymentgateway.integration.merchant;

import com.payflow.paymentgateway.shared.dto.PaymentRequest;
import com.payflow.paymentgateway.shared.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${external.gatewaypayee.name}", url = "${external.gatewaypayee.url}")
public interface PayeeGatewayClients {

    @PostMapping("/api-gateway/v1/payments")
    PaymentResponse send(@RequestBody PaymentRequest request);
}
