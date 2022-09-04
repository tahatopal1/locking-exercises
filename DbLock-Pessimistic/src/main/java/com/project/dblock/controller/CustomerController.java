package com.project.dblock.controller;

import com.project.dblock.dto.UpdateBalanceDTO;
import com.project.dblock.model.Customer;
import com.project.dblock.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Value("${server.port}")
    private int serverPort;

    @PutMapping(path = "/balance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer updateBalance(@RequestBody UpdateBalanceDTO updateBalanceDTO){
        System.out.println("Accessing from port: " + serverPort);
        try {
           return customerService.updateBalance(updateBalanceDTO);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer saveCustomer(@RequestBody Customer customer){
        System.out.println("Accessing from port: " + serverPort);
        return customerService.saveCustomer(customer);
    }

}
