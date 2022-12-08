package com.example.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileOperation {
    public static Map<String, Map<String, Object>> readJsonFile(String path) throws StreamReadException, DatabindException, IOException {
        File jsonFile = new File(path);
        Map<String, Map<String, Object>> result = new ObjectMapper().readValue(jsonFile, HashMap.class);
        return result;
    }
    
}
