package com.csvfileprocessor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
public class RequestPayload {

        private MultipartFile file;
        private String conditionColumnName;
        private String conditionValue;

}
