package com.contractlens.service.analyzer.module.enhance.service;

import com.contractlens.common.dto.ContractCompareResult;
import com.contractlens.common.dto.GatewayTransactionEvent;
import com.contractlens.service.analyzer.db.mongo.dao.AnalyzeSpecDocument;
import com.contractlens.service.analyzer.db.mongo.service.AnalyzeSpecDocumentDelegateService;
import com.contractlens.service.analyzer.db.mongo.service.AnalyzeSpecDocumentQueryService;
import com.contractlens.service.analyzer.module.reader.service.ContractComparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnalyzeMainService {

    private final AnalyzeSpecDocumentQueryService queryService;
    private final AnalyzeSpecDocumentDelegateService delegateService;
    private final ContractComparator contractComparator;

    public void analyze(GatewayTransactionEvent event) {
        AnalyzeSpecDocument document = queryService.getMainBaseLine(event);
    }

}
