package com.contractlens.service.analyzer.db.mongo.service;

import com.contractlens.service.analyzer.db.mongo.dao.AnalyzeSpecDocument;
import com.contractlens.service.analyzer.db.mongo.repository.AnalyzeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnalyzeSpecDocumentDelegateService {

    private final AnalyzeRepository analyzeRepository;

    public void save(AnalyzeSpecDocument runtime) {
        analyzeRepository.save(runtime);
    }
}
