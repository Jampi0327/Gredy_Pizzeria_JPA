package com.Practica.JPA.service;

import com.Practica.JPA.persistence.entity.OrderEntity;
import com.Practica.JPA.persistence.projection.OrderSumary;
import com.Practica.JPA.persistence.repository.OrderRepository;
import com.Practica.JPA.service.dto.RandonOrderDto;
import jakarta.transaction.Transactional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return orders;
    }

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    }
    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }
    @Secured("ROLE_ADMIN")
    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrder(idCustomer);
    }

    public OrderSumary getSumary(int orderId){
        return this.orderRepository.findSumary(orderId);
    }

    @Transactional
    public boolean saveRandorOrder(RandonOrderDto randonOrderDto){
        return this.orderRepository.saveRandonOrder(randonOrderDto.getIdCustomer(), randonOrderDto.getMethod());

    }
}
