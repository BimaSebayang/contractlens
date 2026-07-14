package com.payflow.paymentgateway.module.service;


import com.payflow.paymentgateway.shared.dto.PaymentGatewayResponse;
import com.payflow.paymentgateway.shared.dto.PaymentRequest;
import com.payflow.paymentgateway.shared.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse confirmPayment(String referenceNo);

    PaymentResponse cancelPayment(String referenceNo);

    PaymentResponse refundPayment(String referenceNo);
}
