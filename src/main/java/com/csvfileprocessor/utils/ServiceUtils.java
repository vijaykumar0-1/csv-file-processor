package com.csvfileprocessor.utils;

import com.csvfileprocessor.entity.CustomData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


@UtilityClass
public class ServiceUtils {
    private static final Logger logger = LoggerFactory.getLogger(ServiceUtils.class);

    public static String processCSVFile(MultipartFile file, String conditionColumnName, String conditionValue) {
        CustomData customData = new CustomData();

        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(file.getInputStream())))) {
            // Read the header to get column names
            String[] header = reader.readNext();

            // Map to store column indices
            Map<String, Integer> columnIndexMap = new HashMap<>();
            for (int i = 0; i < header.length; i++) {
                columnIndexMap.put(header[i].toLowerCase(), i);
            }

            // Variables for statistics
            int totalRecords = 0;
            int searchCount = 0;

            // Perform statistics and search
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                totalRecords++;

                // Example: Search for a record with a specific condition
                Integer conditionColumnIndex = columnIndexMap.get(conditionColumnName.toLowerCase());

                if (conditionColumnIndex != null) {
                    String valueInConditionColumn = nextLine[conditionColumnIndex];

                    logger.debug("Condition column index: {}", conditionColumnIndex);
                    logger.debug("Value in condition column: {}", valueInConditionColumn);

                    if (valueInConditionColumn != null && valueInConditionColumn.equalsIgnoreCase(conditionValue)) {
                        // Found a record that matches the condition
                        searchCount++;
                        // Perform further processing or print the record
                        System.out.println("Found matching record: " + String.join(", ", nextLine));
                    }
                } else {
                    // Handle the case where the condition column is not found in the header
                    System.out.println("Condition column not found in the header");
                }

                // You can add more logic for other statistics
            }

            // Print overall statistics

            customData.setSearchCount(searchCount);
            customData.setTotalRecords(totalRecords);

        } catch (IOException | CsvValidationException e) {
            logger.error("Error processing CSV file", e);
        }

        return customData.toString();
    }
}
