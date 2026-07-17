package com.contractlens.service.module.testercontroller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class RequestBodyPost {

    private String search;
    private String index;

}
