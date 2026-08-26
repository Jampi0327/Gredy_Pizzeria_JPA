package com.Practica.JPA.web.controler;

import com.Practica.JPA.persistence.entity.CustomerEntity;
import com.Practica.JPA.persistence.repository.customerRepository;
import com.Practica.JPA.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class customerControler {

    private final customerService customerService;

    @Autowired
    public customerControler(customerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(this.customerService.findByPhone(phone));
    }
}
