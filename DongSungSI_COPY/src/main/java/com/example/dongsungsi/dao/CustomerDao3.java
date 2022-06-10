package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.dao
 * fileName : CustomerDao
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// mybatis 사용시 아래 어노테이션을 붙임
//    인터페이스 구현 => .xml에서 함(스프링에 자동 객체 생성)
@Mapper
public interface CustomerDao3 {
//    전체 회원 조회 메소드
    List<Customer> findAll();

//    id로 회원 조회 메소드
    Optional<Customer> findById(Long id);

//    회원 생성 메소드
    long insertCustomer(Customer customer);

//    회원 수정 메소드
    long updateCustomer(Customer customer);

//    id로 회원 삭제 메소드
    int deleteCustomer(Long id);
//    전체 회원 삭제 메소드
    int deleteAll();
}












