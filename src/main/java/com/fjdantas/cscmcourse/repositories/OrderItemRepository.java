package com.fjdantas.cscmcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fjdantas.cscmcourse.domain.OrderItem;

/*
 * OrderItemRepository Interface annotation by JpaRepository
 * interface of operation of data access by object type with id attribute for repository layer
 */

@Repository 
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> { 

}
