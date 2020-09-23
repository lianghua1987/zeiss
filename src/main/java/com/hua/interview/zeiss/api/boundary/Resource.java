package com.hua.interview.zeiss.api.boundary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Resource {

    @GetMapping
    public String getAll() {
        return "Hello World";
    }
}
