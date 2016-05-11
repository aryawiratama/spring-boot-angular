/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.repository;

import com.arya.latihan.entity.SalesOrder;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Artha
 */
public interface SalesOrderRepository extends JpaRepository<SalesOrder, String>{
    
}
