/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.repository;

import com.arya.latihan.SpringBootAngularApplication;
import com.arya.latihan.entity.Item;
import java.math.BigDecimal;
import javax.transaction.Transactional;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Artha
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootAngularApplication.class)
@Sql(scripts = {"/mysql/delete-item.sql", "/mysql/data-item.sql"})
public class ItemRepositoryTest {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Test
    @Transactional
    public void testSave(){
        Item item = new Item();
        item.setCode("M-0000000002");
        item.setName("Susu");
        item.setPrice(new BigDecimal("5000"));
        item.setCost(new BigDecimal("4500"));
        item.setStock(new BigDecimal("25"));
        item.setExpiredDate(new DateTime(2017,02,10,0,0));
        Assert.assertNull(item.getId());
        item = itemRepository.save(item);
        Assert.assertNotNull(item.getId());
    }
    
    @Test
    @Transactional
    public void testUpdate(){
        Item item = itemRepository.findOne("test123");
        Assert.assertNotNull(item);
        item.setCode("M-000000003");
        item = itemRepository.save(item);
        item = itemRepository.findOne("test123");
        Assert.assertEquals("M-000000003", item.getCode());
    }
    
    @Test
    public void testDelete(){
        itemRepository.delete("test123");
        Item item = itemRepository.findOne("test123");
        Assert.assertNull(item);
    }
    
    @Test
    public void testGetAllItem(){
        Assert.assertEquals(1, itemRepository.findAll().size());
    }
}
