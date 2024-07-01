package com.example.DA_JAVA.model;



public class CartItem {

    private SamPham sanpham;
    private int quantity;


    public SamPham getSanpham() {
        return sanpham;
    }

    public void setSanpham(SamPham sanpham) {
        this.sanpham = sanpham;
    }

    public CartItem() {
    }

    public CartItem(SamPham sanpham, int quantity) {
        this.sanpham = sanpham;
        this.quantity = quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount(){
        return quantity * sanpham.getGia();
    }


}