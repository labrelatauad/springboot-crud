package com.labrelata.modulworkshop.util;

import com.labrelata.modulworkshop.constant.ResponseCode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ResponseUtilTest {

    @Test
    void buildSuccess() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "ok");
        ResponseEntity<Object> responseEntity = ResponseUtil.build(ResponseCode.SUCCESS, "Success",
                (Serializable) data, HttpStatus.OK);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
    }

}