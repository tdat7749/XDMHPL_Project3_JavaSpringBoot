/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private float price;

    @Column(name = "SubTotal")
    private float subTotal;

    @ManyToOne
    @JoinColumn(name = "VegetableID", updatable = false)
    private Vegetable vegetable;

    @ManyToOne
    @JoinColumn(name = "OrderID", updatable = false)
    private Order order;

    public OrderDetail() {

    }

    public OrderDetail(int id, int orderId, int vegetableId, int quantity, float price, float subTotal) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public Order getOrder() {
        return order;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public void setVegetable(Vegetable Vegetable) {
        this.vegetable = Vegetable;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
