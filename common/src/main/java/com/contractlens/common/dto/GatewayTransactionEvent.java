package com.contractlens.common.dto;

import lombok.*;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayTransactionEvent {

    private UUID transactionId;

    /**
     * Endpoint Identifier
     */
    private UUID tokenId;

    /**
     * HTTP Information
     */
    private String method;
    private String targetUrl;
    private Integer statusCode;
    private Long duration;

    /**
     * Request
     */
    private Map<String, String> requestHeaders;
    private Map<String, String> queryParams;
    private byte[] requestBody;

    /**
     * Response
     */
    private Map<String, String> responseHeaders;
    private byte[] responseBody;

    /**
     * Metadata
     */
    private Instant requestTime;

}
