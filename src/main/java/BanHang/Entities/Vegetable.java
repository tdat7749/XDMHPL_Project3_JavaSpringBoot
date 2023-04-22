/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Entities;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Vegetables")
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VegetableID")
    private int vegetableId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Unit")
    private String unit;

    @Column(name = "Amount")
    private int amount;

    @Column(name = "Image")
    private String image;

    @Column(name = "Price")
    private float price;

    @Column(name = "Sold")
    private int sold;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category Category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vegetable", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Vegetable() {

    }

    public Vegetable(int vegetableId, String name, String unit, int amount, String image, float price, int sold) {
        this.vegetableId = vegetableId;
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.image = image;
        this.price = price;
        this.sold = sold;
    }

    public int getVegetableId() {
        return vegetableId;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getAmount() {
        return amount;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public int getSold() {
        return sold;
    }

    public Category getCategory() {
        return Category;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setVegetableId(int vegetableId) {
        this.vegetableId = vegetableId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void setCategory(Category Category) {
        this.Category = Category;
    }

    public void setOrderDetails(List<OrderDetail> OrderDetails) {
        this.orderDetails = OrderDetails;
    }

}
