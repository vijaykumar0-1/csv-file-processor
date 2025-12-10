package com.csvfileprocessor.service;
import com.csvfileprocessor.utils.ServiceUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    public String getResult(MultipartFile file, String conditionColumnName, String conditionValue) {
        String result = ServiceUtils.processCSVFile(file,conditionColumnName,conditionValue);
        return result;
    }
}




