package com.contractlens.service.analyzer.module.enhance.service;

import com.contractlens.common.dto.ContractCompareResult;
import com.contractlens.common.dto.ContractSnapshot;
import com.contractlens.common.dto.GatewayTransactionEvent;
import com.contractlens.service.analyzer.db.mongo.dao.AnalyzeSpecDocument;
import com.contractlens.service.analyzer.db.mongo.service.AnalyzeSpecDocumentDelegateService;
import com.contractlens.service.analyzer.db.mongo.service.AnalyzeSpecDocumentQueryService;
import com.contractlens.service.analyzer.module.reader.service.ContractComparator;
import com.contractlens.service.analyzer.module.reader.service.JsonReaderSvc;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AnalyzeMainService {

    private final AnalyzeSpecDocumentQueryService queryService;
    private final AnalyzeSpecDocumentDelegateService delegateService;
    private final ContractComparator contractComparator;
    private final JsonReaderSvc jsonReaderSvc;

    public void analyze(GatewayTransactionEvent event) {
        AnalyzeSpecDocument baseLine = queryService.getMainBaseLine(event);

        AnalyzeSpecDocument runtime = new AnalyzeSpecDocument();
        BeanUtils.copyProperties(event,runtime);

        ContractSnapshot requestHeaderSnapshot = jsonReaderSvc.readContract(event.getRequestHeaders());
        runtime.setRequestHeaderSnapshot(requestHeaderSnapshot);

        ContractSnapshot requestBodySnapshot = jsonReaderSvc.readContract(event.getRequestBody());
        runtime.setRequestBodySnapshot(requestBodySnapshot);

        ContractSnapshot responseHeadersSnapshot = jsonReaderSvc.readContract(event.getResponseHeaders());
        runtime.setResponseHeadersSnapshot(responseHeadersSnapshot);

        ContractSnapshot responseBodySnapshot = jsonReaderSvc.readContract(event.getResponseBody());
        runtime.setResponseBodySnapshot(responseBodySnapshot);

        if(!Objects.isNull(baseLine)) {

            runtime.setCompareDocId(baseLine.getCompareDocId());
            ContractCompareResult contractCompareRequestHeader = contractComparator.compare(
                    baseLine.getRequestHeaderSnapshot(),
                    requestHeaderSnapshot
            );
            runtime.setRequestHeaderCompareResult(contractCompareRequestHeader);

            ContractCompareResult contractCompareRequestBody = contractComparator.compare(
                    baseLine.getRequestBodySnapshot(),
                    requestBodySnapshot
            );
            runtime.setRequestBodyCompareResult(contractCompareRequestBody);

            ContractCompareResult contractCompareResponseHeader = contractComparator.compare(
                    baseLine.getResponseHeadersSnapshot(),
                    responseHeadersSnapshot
            );
            runtime.setResponseHeadersCompareResult(contractCompareResponseHeader);


            ContractCompareResult contractCompareResponseBody = contractComparator.compare(
                    baseLine.getResponseBodySnapshot(),
                    responseBodySnapshot
            );
            runtime.setResponseBodyCompareResult(contractCompareResponseBody);


        }

        delegateService.save(runtime);


    }

}
