package com.project.dblock.service;


import com.project.dblock.dto.UpdateBalanceDTO;
import com.project.dblock.model.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer updateBalance(UpdateBalanceDTO updateBalanceDTO) throws InterruptedException;

}
