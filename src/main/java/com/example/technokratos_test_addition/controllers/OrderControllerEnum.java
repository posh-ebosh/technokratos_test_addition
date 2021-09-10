package com.example.technokratos_test_addition.controllers;

public enum OrderControllerEnum {
        FIND_BY_EMAIL("/find_by_email"),
        FIND_BY_CODE("/find_by_code"),
        BETWEEN_DATES("/between_dates");

        private String requestUrl;

        OrderControllerEnum(String requestUris){
            this.requestUrl = requestUris;
        }

        public String getRequestUrl() {
            return requestUrl;
        }

}
