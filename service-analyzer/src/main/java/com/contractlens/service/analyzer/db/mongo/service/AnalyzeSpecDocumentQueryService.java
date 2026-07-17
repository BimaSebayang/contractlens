package com.contractlens.service.analyzer.db.mongo.service;

import com.contractlens.common.dto.GatewayTransactionEvent;
import com.contractlens.service.analyzer.db.mongo.dao.AnalyzeSpecDocument;
import com.contractlens.service.analyzer.db.mongo.repository.AnalyzeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AnalyzeSpecDocumentQueryService {

    private final AnalyzeRepository analyzeRepository;

    public AnalyzeSpecDocument getMainBaseLine(GatewayTransactionEvent event) {
        //Find Existing BaseLine First
        Optional<AnalyzeSpecDocument> analyzeSpecDocument = analyzeRepository.findLatestWithBaseline(
                event.getTokenId(),
                event.getMethod(),
                event.getTargetUrl()
        );

        if(analyzeSpecDocument.isEmpty()){

            //Find The Latest if baseline not exists
            return analyzeRepository.findLatest(
                    event.getTokenId(),
                    event.getMethod(),
                    event.getTargetUrl()
            ).orElse(new AnalyzeSpecDocument());

        }else {
            return analyzeSpecDocument.get();
        }


    }
}
