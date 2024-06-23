package com.example.DA_JAVA.model;

public class CartItem {
    private SamPham sanpham;
    private int quantity;

    public CartItem(SamPham sanpham, int quantity) {
        this.sanpham = sanpham;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSanpham(SamPham sanpham) {
        this.sanpham = sanpham;
    }

    public CartItem() {
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "sanpham=" + sanpham +
                ", quantity=" + quantity +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public SamPham getSanpham() {
        return sanpham;
    }
// Constructors
}
