package com.contractlens.service.analyzer.module.reader.service;

import com.contractlens.common.dto.ContractSnapshot;

import java.io.InputStream;
import java.util.Map;

public interface JsonReaderSvc {

    ContractSnapshot readContract(String contract);
    ContractSnapshot readContract(byte[] contract);
    ContractSnapshot readContract(InputStream contract);
    ContractSnapshot readContract(Map<String, String> stringMap);
    ContractSnapshot readContractObj(Map<String, Object> stringMap);
}
