package com.project.dblock.service;

import com.project.dblock.dto.UpdateBalanceDTO;
import com.project.dblock.model.Customer;
import com.project.dblock.repository.CustomerRepository;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final int MAX_ATTEMPT = 3;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer updateBalance(UpdateBalanceDTO updateBalanceDTO) throws InterruptedException {
        Double balance = updateBalanceDTO.getBalance();
        Long customerId = updateBalanceDTO.getCustomerId();
        Integer timeout = updateBalanceDTO.getTimeout();

        for (int i = 0; i < MAX_ATTEMPT; i++){
            try {
                return updateBalance(balance, customerId, timeout);
            }catch (StaleStateException e){
                if (i == MAX_ATTEMPT) throw new StaleStateException(e.getMessage());
            }
        }

        return null;
    }

    private Customer updateBalance(Double balance, Long customerId, Integer timeout) throws InterruptedException {
        Customer customer
                = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("There's no such customer"));

        System.out.println(customer);

        if (Objects.nonNull(timeout)){
            TimeUnit.SECONDS.sleep(timeout);
        }
        customer.setBalance(customer.getBalance() + balance);
        return customerRepository.save(customer);
    }


}
