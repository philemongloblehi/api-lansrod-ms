package com.lansrod.api.helpers.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
public class PageResponseFactory {
    private PageResponseFactory() {

    }

    public static <T> ResponseEntity<JSONObject> createResponseEntity(HttpStatus status, HttpHeaders headers, Page<T> page, HashMap<String, Object> filters, List<String> options) throws JSONException, JsonProcessingException {
        return createResponseEntity(status, headers, page, filters, options);
    }

    public static <T> ResponseEntity<JSONObject> createResponseEntity(HttpStatus status, HttpHeaders headers, Page<T> page, HashMap<String, Object> filters, List<String> options, ObjectWriter itemObjectWriter) throws JSONException, JsonProcessingException {
        JSONObject jsonResponseObject = createResponseJsonBody(page, filters, options, itemObjectWriter);
        return ResponseEntity.status(status).headers(headers).contentType(MediaType.APPLICATION_JSON).body(jsonResponseObject);
    }

    public static <T> JSONObject createResponseJsonBody(Page<T> page) throws JSONException, JsonProcessingException {
        return createResponseJsonBody(page, null, null, null);
    }

    public static <T> JSONObject createResponseJsonBody(Page<T> page, HashMap<String, Object> filters, List<String> options) throws JSONException, JsonProcessingException {
        return createResponseJsonBody(page, filters, options, null);
    }

    public static <T> JSONObject createResponseJsonBody(Page<T> page, HashMap<String, Object> filters, List<String> options, ObjectWriter objectWriter) throws JSONException, JsonProcessingException {
        if (filters == null) {
            filters = new HashMap<>();
        }
        if (options == null) {
            options = new ArrayList<>();
        }
        if (objectWriter == null) {
            objectWriter = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00")).writer();
        }
        JSONObject jsonResponseObject = new JSONObject();
        jsonResponseObject.put("page", page.getTotalPages());
        jsonResponseObject.put("itemPerPage", page.getSize());
        jsonResponseObject.put("itemOffset", 0);
        jsonResponseObject.put("fullyItems", page.getTotalElements());
        jsonResponseObject.put("pageNumber", page.getNumber() + 1);
        List<T> items = page.getContent();
        String jsonString = objectWriter.writeValueAsString(items);
        jsonResponseObject.put("items", new JSONArray(jsonString));
        return jsonResponseObject;
    }
}
