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

@Controller
public class OrderController {
    private static final String orderPath = "orders";
    RestTemplate restTemplate = new RestTemplate();
    @Value("${service.host}")
    private String serviceURL;

    @GetMapping("/orders")
    public ResponseEntity<String> getOrders () {
        String respUrl = serviceURL + orderPath; //TODO переделать в билдер
        ResponseEntity<String> response
                = restTemplate.getForEntity(respUrl, String.class);
        return  response;
    }

    @GetMapping(value = "/orders/find_by_email", params = "email")
    public ResponseEntity<String> getOrdersByEmail(@RequestParam("email") String email){
        String respUrl = serviceURL + orderPath + OrderControllerEnum.FIND_BY_EMAIL + "email={email}"; //TODO переделать в билдер
        ResponseEntity<String> response
                = restTemplate.getForEntity(respUrl, String.class, email);
        return  response;
    }

    @GetMapping(value = "/orders/find_by_code", params = "code")
    public ResponseEntity<String> getOrdersByCode(@RequestParam("code") String code){
        String respUrl = serviceURL + orderPath + OrderControllerEnum.FIND_BY_CODE + "code={code}"; //TODO переделать в билдер
        ResponseEntity<String> response
                = restTemplate.getForEntity(respUrl, String.class, code);
        return  response;
    }

    @GetMapping(value = {"/orders/between_dates"}, params = {"start", "end"})
    public ResponseEntity<String> getOrdersBetweenDate(@Param("start")String start, @Param("end")String end){
        String respUrl = serviceURL + orderPath //TODO переделать в билдер
                + OrderControllerEnum.BETWEEN_DATES.getRequestUri()
                + "start={start}&end={end}";
        ResponseEntity<String> response
                = restTemplate.getForEntity(respUrl, String.class, start, end);
        return  response;
    }

    @PostMapping("/orders")
    public String addOrder(@RequestBody String request){
        String respUrl = serviceURL + orderPath; //TODO переделать в билдер
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        String response  = restTemplate.postForObject(respUrl, entity, String.class);
        return response;
    }


}
