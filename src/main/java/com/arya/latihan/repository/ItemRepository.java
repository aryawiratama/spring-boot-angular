/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.repository;

import com.arya.latihan.entity.Item;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Artha
 */
public interface ItemRepository extends JpaRepository<Item, String>{
    
    @Query("SELECT COUNT(i) FROM Item i")
    public Integer findLastCode();
}
