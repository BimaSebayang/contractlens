package com.contractlens.service.analyzer.module.service.impl;

import com.contractlens.service.analyzer.integration.merchant.service.MerchantGateway;
import com.contractlens.service.analyzer.module.service.PaymentService;
import com.payflow.paymentgateway.shared.dto.PaymentGatewayResponse;
import com.payflow.paymentgateway.shared.dto.PaymentRequest;
import com.payflow.paymentgateway.shared.dto.PaymentResponse;
import com.payflow.paymentgateway.shared.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final MerchantGatewayFactory merchantGatewayFactory;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {

        //simpan data

        return new PaymentResponse();
    }


    @Override
    public PaymentResponse confirmPayment(String referenceNo) {
        PaymentStatus  paymentStatus = (PaymentStatus) redisTemplate.opsForHash().get("payment:" + referenceNo,
                "status");

        if(paymentStatus!=PaymentStatus.PENDING){
            throw new RuntimeException();
        }

        //Get Datanya
        PaymentRequest request = new PaymentRequest();


        redisTemplate.opsForHash().put(
                "payment:" + referenceNo,
                "status",
                PaymentStatus.ON_PROCESS
        );

        MerchantGateway gateway =
                merchantGatewayFactory.getGateway(request.getMerchantType());

        PaymentGatewayResponse gatewayResponse = gateway.process(request);

        redisTemplate.opsForHash().put(
                "payment:" + referenceNo,
                "status",
                gatewayResponse.getStatus()
        );

        rabbitTemplate.convertAndSend(
                "payment.exchange",
                "payment.notification",
                gatewayResponse
        );


        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus(gatewayResponse.getStatus());

        return paymentResponse;
    }

    @Override
    public PaymentResponse cancelPayment(String referenceNo) {
        PaymentStatus  paymentStatus = (PaymentStatus) redisTemplate.opsForHash().get("payment:" + referenceNo,
                "status");

        if(paymentStatus!=PaymentStatus.PENDING){
            throw new RuntimeException();
        }

        redisTemplate.opsForHash().put(
                "payment:" + referenceNo,
                "status",
                PaymentStatus.CANCELLED
        );

        PaymentGatewayResponse gatewayResponse = new PaymentGatewayResponse();
        gatewayResponse.setStatus(PaymentStatus.CANCELLED);

        rabbitTemplate.convertAndSend(
                "payment.exchange",
                "payment.notification",
                gatewayResponse
        );

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus(gatewayResponse.getStatus());

        return paymentResponse;
    }

    @Override
    public PaymentResponse refundPayment(String referenceNo) {
        PaymentStatus  paymentStatus = (PaymentStatus) redisTemplate.opsForHash().get("payment:" + referenceNo,
                "status");

        if(!Arrays.asList(PaymentStatus.PENDING,PaymentStatus.FAILED,PaymentStatus.ON_PROCESS).contains(paymentStatus)){
            throw new RuntimeException();
        }

        //Get Datanya
        PaymentRequest request = new PaymentRequest();


        redisTemplate.opsForHash().put(
                "payment:" + referenceNo,
                "status",
                PaymentStatus.ON_PROCESS
        );

        MerchantGateway gateway =
                merchantGatewayFactory.getGateway(request.getMerchantType());

        PaymentGatewayResponse gatewayResponse = gateway.process(request);
        gatewayResponse.setStatus(PaymentStatus.REFUNDED);

        redisTemplate.opsForHash().put(
                "payment:" + referenceNo,
                "status",
                gatewayResponse.getStatus()
        );

        rabbitTemplate.convertAndSend(
                "payment.exchange",
                "payment.notification",
                gatewayResponse
        );


        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus(gatewayResponse.getStatus());

        return paymentResponse;
    }
}
