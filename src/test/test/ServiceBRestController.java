//package com.example.SpringSecondAppTest;
//
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestClient;
//
//@RestController
//@RequestMapping("/api/hello")
//public class ServiceBRestController {
//    private final DiscoveryClient discoveryClient;
//    private final RestClient restClient;
//
//    public ServiceBRestController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
//        this.discoveryClient = discoveryClient;
//        this.restClient = restClientBuilder.build();
//    }
//
//    @GetMapping
//    public String hello() {
//        ServiceInstance serviceInstance = discoveryClient.getInstances("SpringFirstAppTest").get(0);
//        return restClient.get()
//                .uri(serviceInstance.getUri() + "/helloA")
//                .retrieve()
//                .body(String.class);
//    }
//
//}
