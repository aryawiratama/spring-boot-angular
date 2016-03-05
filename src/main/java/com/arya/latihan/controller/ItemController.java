/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.controller;

import com.arya.latihan.entity.Item;
import com.arya.latihan.repository.ItemRepository;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/items")
@Transactional(readOnly = true)
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> saveItem(@RequestBody @Valid Item item){
        item = itemRepository.save(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Transactional(readOnly = false)
    public ResponseEntity<Item> updateItem(@PathVariable("id")String id, @RequestBody @Valid Item item){
        item.setId(id);
        item = itemRepository.save(item);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Item>> findItems(){
        List<Item> items = itemRepository.findAll();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> findItemById(@PathVariable("id")String id){
        Item item = itemRepository.findOne(id);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional(readOnly = false)
    public void deleteItem(@PathVariable("id")String id){
        itemRepository.delete(id);
    }
}
