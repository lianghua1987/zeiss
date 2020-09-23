package com.hua.interview.zeiss.api.boundary;

import com.hua.interview.zeiss.api.entity.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class Resource {

    @Autowired
    Map<String, Payload> machines;

    @GetMapping("api")
    public Map<String, Payload> getAll() {
        return machines;
    }
}
