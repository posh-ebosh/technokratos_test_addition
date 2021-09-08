package com.example.technokratos_test_addition.controllers;

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

@Controller
public class OrderController {
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/orders")
    public ResponseEntity<String> getOrders () {
        String respUrl = "http://localhost:8080/orders";
        ResponseEntity<String> response
                = restTemplate.getForEntity(respUrl, String.class);
        return  response;
    }

    @GetMapping(value = "/orders/find_by_email", params = "email")
    public ResponseEntity<String> getOrdersByEmail(@RequestParam("email") String email){
        String respUrl = "http://localhost:8080/orders/find_by_email?email={email}";
        ResponseEntity<String> response
                = restTemplate.getForEntity(respUrl, String.class, email);
        return  response;
    }

    @GetMapping(value = "/orders/find_by_code", params = "code")
    public ResponseEntity<String> getOrdersByCode(@RequestParam("code") String email){
        String respUrl = "http://localhost:8080/orders/find_by_code?code={code}";
        ResponseEntity<String> response
                = restTemplate.getForEntity(respUrl, String.class, email);
        return  response;
    }

    @GetMapping(value = {"/orders/between_dates"}, params = {"start", "end"})
    public ResponseEntity<String> getOrdersBetweenDate(@Param("start")String start, @Param("end")String end){
        String respUrl = "http://localhost:8080/orders/between_dates?start={start}&end={end}";
        ResponseEntity<String> response
                = restTemplate.getForEntity(respUrl, String.class, start, end);
        return  response;
    }

    @PostMapping("/orders")
    public String addOrder(@RequestBody String request){
        String respUrl = "http://localhost:8080/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        String response  = restTemplate.postForObject(respUrl, entity, String.class);
        return response;
    }


}
