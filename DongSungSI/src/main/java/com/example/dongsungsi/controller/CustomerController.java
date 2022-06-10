package com.example.dongsungsi.controller;

import com.example.dongsungsi.model.Customer;
import com.example.dongsungsi.paging.Criteria;
import com.example.dongsungsi.service.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.controller
 * fileName : CustomerController
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService2; // 객체 정의 ( null ) => 스프링객체 받기

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/customer")
    public ResponseEntity<Customer>
    createTutorial(@RequestBody Customer customer) {

        logger.info("createCustomer : customer {} : ", customer);
        boolean bSuccess = customerService2.save(customer);

        try {
            if(bSuccess == true) {
                return new ResponseEntity<>(customer, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer>
    getCustomerById(@PathVariable("id") long id ) {
        Optional<Customer> customer = customerService2.findById(id);
        if(customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<Map<String, Object>>
    getAllTitlePage(Criteria criteria) {
        logger.info("criteria {}", criteria);
        List<Customer> customer =
                customerService2.findByTitleContaining(criteria);
        logger.info("customer {}", customer);

        try {
            if (customer.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("criteria {}", criteria);

            // TODO: Map에 넣어 객체 + 페이지 정보를 Vue로 전송
            Map<String, Object> response = new HashMap<>();
            response.put("customer", customer);
            // page: 현재 페이지
            response.put("currentPage", criteria.getPage());
            // totalItems: 총 데이터 건수
            response.put("totalItems", criteria.getTotalItems());
            // totalPages: 총 페이지 개수
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer>
    updateTutorial(@PathVariable("id") long id,
                   @RequestBody Customer customer) {
        Optional<Customer> customerData = customerService2.findById(id);

        if(customerData.isPresent()) {
            boolean bSuccess = customerService2.save(customer);
            if(bSuccess == true) {
                return new ResponseEntity<>(customer, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customer/deletion/{id}")
    public ResponseEntity<HttpStatus>
    deleteTutorial(@PathVariable("id") long id) {
        boolean bSuccess = customerService2.deleteById(id);

        try {
            if( bSuccess == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customer")
    public ResponseEntity<HttpStatus> deleteAllCustomer() {
        boolean bSuccess = customerService2.deleteAll();
        try {
            if( bSuccess == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}











