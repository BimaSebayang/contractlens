package com.contractlens.service.analyzer.module.reader.service.impl;

import com.contractlens.common.dto.ContractSnapshot;
import com.contractlens.service.analyzer.module.reader.service.JsonParser;
import com.contractlens.service.analyzer.module.reader.service.JsonReaderSvc;
import com.contractlens.service.analyzer.module.reader.service.NodeSniper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

@Service
@AllArgsConstructor
public class JsonReaderSvcImpl implements JsonReaderSvc {

    private final NodeSniper nodeSniper;
    private final JsonParser jsonParser;

    @Override
    public ContractSnapshot readContract(String contract) {
        JsonNode jsonNode = jsonParser.parse(contract);
        return nodeSniper.normalizer(jsonNode);
    }

    @Override
    public ContractSnapshot readContract(byte[] contract) {
        JsonNode jsonNode = jsonParser.parse(contract);
        return nodeSniper.normalizer(jsonNode);
    }

    @Override
    public ContractSnapshot readContract(InputStream contract) {
        JsonNode jsonNode = jsonParser.parse(contract);
        return nodeSniper.normalizer(jsonNode);
    }

    @Override
    public ContractSnapshot readContract(Map<String, String> stringMap) {
        JsonNode jsonNode = jsonParser.parse(stringMap);
        return nodeSniper.normalizer(jsonNode);
    }

    @Override
    public ContractSnapshot readContractObj(Map<String, Object> stringMap) {
        JsonNode jsonNode = jsonParser.parseObj(stringMap);
        return nodeSniper.normalizer(jsonNode);
    }

}
