package com.contractlens.service.integration.message;

import com.contractlens.common.dto.GatewayTransactionEvent;
import com.contractlens.common.dto.PublishEvent;

import java.util.List;
import java.util.UUID;

public interface PublishEventService {

    void upsert(GatewayTransactionEvent event);

    void markSuccess(UUID transactionId);

    List<PublishEvent> findPending();
}
