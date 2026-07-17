package com.contractlens.service.db.redis.service;

import com.contractlens.common.dto.PublishEvent;

import java.util.List;
import java.util.UUID;

public interface PublishEventDelegateSvc {

    void save(PublishEvent event);
    List<PublishEvent> findPending();
    PublishEvent findByTransactionId(UUID transactionId);
    void delete(UUID transactionId);

}
