package com.contractlens.service.analyzer.module.reader.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;
import java.util.Map;

public interface JsonParser {

    JsonNode parse(String content);

    JsonNode parse(byte[] content);

    JsonNode parse(InputStream inputStream);

    JsonNode parse(Map<String, String> content);

    JsonNode parseObj(Map<String, Object> content);

}
