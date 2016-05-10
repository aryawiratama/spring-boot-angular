/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.controller;

import com.arya.latihan.entity.Customer;
import com.arya.latihan.repository.CustomerRepository;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.sql.Delete;
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
@RequestMapping("/api/customer")
@Transactional(readOnly = true)
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Customer> saveCustomer(@Valid Customer customer){
        customer = customerRepository.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Customer>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Transactional(readOnly = false)
    public ResponseEntity<Customer> editCustomer(@PathVariable("id") String id, @Valid Customer customer){
        customer.setId(id);
        customer = customerRepository.save(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(readOnly = false)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id")String id){
        customerRepository.delete(id);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("/{id}")String id){
        Customer customer = customerRepository.findOne(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
