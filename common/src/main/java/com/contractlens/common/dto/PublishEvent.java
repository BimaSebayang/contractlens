package com.contractlens.common.dto;

import com.contractlens.common.enums.PublishStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PublishEvent {

    private UUID id;
    private UUID transactionId;
    private String payload;
    private PublishStatus status;
    private Integer retryCount;
    private LocalDateTime nextRetryTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
