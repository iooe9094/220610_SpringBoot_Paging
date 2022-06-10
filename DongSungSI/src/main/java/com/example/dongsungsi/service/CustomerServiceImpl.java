package com.example.dongsungsi.service;

import com.example.dongsungsi.dao.CustomerDao;
import com.example.dongsungsi.model.Customer;
import com.example.dongsungsi.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// @Service : 스프링에 객체로 생성
@Service
public class CustomerServiceImpl implements CustomerService {
    // @Autowired : 스프링에 생성된 객체를 받아옴
    @Autowired
    private CustomerDao customerDao2; // 객체 정의 {null} => 스프링 객체

    // 향후에 로그 찍고 싶을때 사용하는 객체
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Optional<Customer> findById(long id) {
        return customerDao2.findById(id);
    }

    @Override
    public List<Customer> findByTitleContaining(Criteria criteria) {
        // 빈 값으로 초기화
        List<Customer> customers = Collections.emptyList();

        // Title이 Null 체크
        Optional<String> optionalCriteria = Optional.ofNullable(criteria.getTitle());

        // 테이블의 총 데이터 건수
        int totalCount = customerDao2.selectTotalCount(optionalCriteria.orElse(""));

        // criteria : 페이징 처리 클래스 객체
        criteria.setTotalItems(totalCount);

        // 총 페이지 개수 : 테이블의 총 건수(totalCount) / 페이지 당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());

        if(criteria.getTitle() == null){
            // title(제목)이 없으면(==null) 전채 검색을 함
            customers = customerDao2.findAll(criteria);
        } else {
            // title(제목) 이 있으면(!=null) 제목 검색을 함
            customers = customerDao2.findByTitleContaining(criteria);
        }

        return customers;
    }

    @Override
    public List<Customer> findAll(Criteria criteria) {
        return customerDao2.findAll(criteria);
    }

    @Override
    public boolean save(Customer customer) {
        int queryResult = 0;

        logger.info("tutorial {} : " , customer);

        if(customer.getId() == null)
        {
            // id 값이 없으면 insert 문 실행
            queryResult = customerDao2.insertCustomer(customer);
        }else {
            // id 값이 있으면 update 문 실행
            queryResult = customerDao2.updateCustomer(customer);
        }
        return (queryResult >= 1) ? true : false;
    }

    @Override
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = customerDao2.deleteCustomer(id);
        return (queryResult >= 1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;

        queryResult = customerDao2.deleteAll();
        return (queryResult >= 1) ? true : false;
    }
}











