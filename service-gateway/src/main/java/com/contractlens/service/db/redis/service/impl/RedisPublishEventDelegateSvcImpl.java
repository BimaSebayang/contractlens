package com.contractlens.service.db.redis.service.impl;

import com.contractlens.common.constant.ServiceConstants;
import com.contractlens.common.dto.PublishEvent;
import com.contractlens.service.db.redis.service.PublishEventDelegateSvc;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(ServiceConstants.REDIS_PUBLISH_EVENT_DELEGATE)
@AllArgsConstructor
public class RedisPublishEventDelegateSvcImpl implements PublishEventDelegateSvc {

    @SneakyThrows
    @Override
    public void save(PublishEvent event) {



    }

    @Override
    public List<PublishEvent> findPending() {
        return new ArrayList<>();
    }

    @Override
    public PublishEvent findByTransactionId(UUID transactionId) {
        return new PublishEvent();
    }

    @Override
    public void delete(UUID transactionId) {

    }
}
