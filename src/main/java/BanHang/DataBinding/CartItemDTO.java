package BanHang.DataBinding;

public class CartItemDTO {
    private int quantity;

    private float price;

    private float subTotal;

    private int vegetableId;

    private String image;

    private String name;

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public int getVegetableId() {
        return vegetableId;
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

    public void setVegetableId(int vegetableId) {
        this.vegetableId = vegetableId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
