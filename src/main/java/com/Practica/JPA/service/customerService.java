package com.Practica.JPA.service;

import com.Practica.JPA.persistence.entity.CustomerEntity;
import com.Practica.JPA.persistence.repository.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class customerService {
    private final customerRepository customerRepository;

    @Autowired
    public customerService(customerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public CustomerEntity findByPhone(String phone) {
        return this.customerRepository.findByPhone(phone);
    }
}
