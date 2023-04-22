/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.ViewModel;

/**
 *
 * @author hp
 */
public class VegetableVm {
    private int vegetableId;

    private String categoryName;

    private String name;

    private String unit;

    private int amount;

    private String image;

    private float price;

    private int sold;

    public VegetableVm() {

    }

    public int getVegetableId() {
        return vegetableId;
    }

    public String getCategoryName() {
        return categoryName;
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

    public void setVegetableId(int vegetableId) {
        this.vegetableId = vegetableId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public VegetableVm(int vegetableId, String categoryName, String name, String unit, int amount, String image,
            float price, int sold) {
        this.vegetableId = vegetableId;
        this.categoryName = categoryName;
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.image = image;
        this.price = price;
        this.sold = sold;
    }

}
