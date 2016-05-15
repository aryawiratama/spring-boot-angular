/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.controller;

import com.arya.latihan.SpringBootAngularApplication;
import com.arya.latihan.entity.Item;
import com.jayway.restassured.RestAssured;
import java.math.BigDecimal;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Artha
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootAngularApplication.class)
@WebIntegrationTest(randomPort = true)
@Sql(scripts = {"/mysql/delete-item.sql", "/mysql/data-item.sql"})
public class ItemControllerTest {
    
    private static final String url="/api/items";
    private static final String username = "arya";
    private static final String password = "bandenk";
    
    @Value("${local.server.port}")
    int serverPort;
    
    @Before
    public void setup(){
        RestAssured.port = serverPort;
    }
    
    @Test
    public void testSave(){
        Item item = new Item();
        item.setName("Biskuit Cokelat");
        item.setPrice(new BigDecimal("5000"));
        item.setCost(new BigDecimal("4500"));
        item.setStock(new BigDecimal("25"));
        item.setExpiredDate(new DateTime(2017,02,10,0,0));
        
        RestAssured.given().auth().basic(username, password).contentType("application/json").body(item).log().everything()
                .when()
                .post(url)
                .then()
                .statusCode(201).log().everything()
                .header("Location", Matchers.containsString(url));
    }
    @Test
    public void testUpdate(){
        // baca data yang ada di data-item.sql
        RestAssured.given().auth().basic(username, password)
                .when()
                .get(url+"/test123")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalToIgnoringCase("Kacang"));
        
        Item item = new Item();
        item.setName("Biskuit");
        item.setCode("M-0000000001");
        item.setPrice(new BigDecimal("5000"));
        item.setCost(new BigDecimal("4500"));
        item.setStock(new BigDecimal("25"));
        item.setExpiredDate(new DateTime(2017,02,10,0,0));
        // update id test123 dengan data item diatas
        RestAssured.given().auth().basic(username, password)
                .body(item).contentType("application/json")
                .when()
                .put(url + "/test123")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo("test123"))
                .body("name", Matchers.equalToIgnoringCase("Biskuit"));
    }
    
    @Test
    public void testDelete(){
        RestAssured.given().auth().basic(username, password)
                .when()
                .delete(url + "/test123")
                .then()
                .statusCode(204);
    }
    
    @Test
    public void testFindAll(){
        RestAssured.given().auth().basic(username, password)
                .when()
                .get(url)
                .then()
                .body("items.size", Matchers.equalTo(1));
    }
}
