/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

/**
 *
 * @author Artha
 */
@Entity
@Table(name = "t_sales_order")
public class SalesOrder {
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "sales_no", nullable = false, unique = true)
    @NotNull
    private String salesNo;
    
    @Column(name = "sales_date", nullable = false)
    @NotNull
    private DateTime salesDate;
    
    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "salesOrder")
    private Set<SalesOrderDetail> orderDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo;
    }

    public DateTime getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(DateTime salesDate) {
        this.salesDate = salesDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<SalesOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<SalesOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
}
