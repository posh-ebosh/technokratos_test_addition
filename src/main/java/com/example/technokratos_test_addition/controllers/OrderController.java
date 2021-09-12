package com.example.technokratos_test_addition.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class OrderController {
    private static final String orderPath = "/orders";
    RestTemplate restTemplate = new RestTemplate();
    @Value("${service.host}")
    private String serviceHost;
    UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

    @GetMapping("/orders")
    public ResponseEntity<String> getOrders () {
        String uri = uriComponentsBuilder
                .scheme("http")
                .path(serviceHost)
                .path(orderPath)
                .build()
                .toUriString();
        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class);
        return  response;
    }

    @GetMapping(value = "/orders/find_by_email", params = "email")
    public ResponseEntity<String> getOrdersByEmail(@RequestParam("email") String email){
        String uri = uriComponentsBuilder
                .scheme("http")
                .host(serviceHost)
                .path(orderPath)
                .path(OrderControllerEnum.FIND_BY_EMAIL.getRequestUrl())
                .queryParam("email", email)
                .build()
                .toUriString();
        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class, email);
        return  response;
    }

    @GetMapping(value = "/orders/find_by_code", params = "code")
    public ResponseEntity<String> getOrdersByCode(@RequestParam("code") String code){
        String uri = uriComponentsBuilder
                .scheme("http")
                .path(serviceHost)
                .path(orderPath)
                .path(OrderControllerEnum.FIND_BY_CODE.getRequestUrl())
                .queryParam("code", code)
                .build()
                .toUriString();
        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class, code);
        return  response;
    }

    @GetMapping(value = {"/orders/between_dates"}, params = {"start", "end"})
    public ResponseEntity<String> getOrdersBetweenDate(@Param("start")String start, @Param("end")String end){
        String uri = uriComponentsBuilder
                .scheme("http")
                .path(serviceHost)
                .path(orderPath)
                .path(OrderControllerEnum.FIND_BY_CODE.getRequestUrl())
                .queryParam("start", start)
                .queryParam("end", end)
                .build()
                .toUriString();
        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class, start, end);
        return  response;
    }

    @PostMapping("/orders")
    public String addOrder(@RequestBody String request){
        String uri = uriComponentsBuilder
                .scheme("http")
                .path(serviceHost)
                .path(orderPath)
                .build()
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        String response  = restTemplate.postForObject(uri, entity, String.class);
        return response;
    }


}
