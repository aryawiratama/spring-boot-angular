/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.controller;

import com.arya.latihan.entity.SalesOrder;
import com.arya.latihan.repository.SalesOrderRepository;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Artha
 */
@RestController
@RequestMapping(value = "/api/sales-order")
@Transactional(readOnly = true)
public class SalesOrderController {
    
    @Autowired
    private SalesOrderRepository salesOrderRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<SalesOrder> saveSalesOrder(@Valid SalesOrder salesOrder){
        salesOrder = salesOrderRepository.save(salesOrder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(salesOrder.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<SalesOrder> (headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Transactional(readOnly = false)
    public ResponseEntity<SalesOrder> editSalesOrder(@PathVariable("id")String id, @Valid SalesOrder salesOrder){
        salesOrder.setId(id);
        salesOrder = salesOrderRepository.save(salesOrder);
        return new ResponseEntity<SalesOrder>(salesOrder, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(readOnly = false)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalesOrder(@PathVariable("/{id}")String id){
        salesOrderRepository.delete(id);
    }
    
}
