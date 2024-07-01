package com.example.DA_JAVA.service;

import com.example.DA_JAVA.model.CartItem;
import com.example.DA_JAVA.model.SamPham;
import com.example.DA_JAVA.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public void upsertCart(Long sanphamId, int quantity) {
        SamPham sanPham = sanPhamRepository.findById(sanphamId)
                .orElseThrow(() -> new IllegalArgumentException("San Pham not found: " + sanphamId));
        if (cartItems.stream().filter(p -> p.getSanpham().getId() == sanphamId && p.getQuantity() > 0).count() > 0) {
            CartItem item = cartItems.stream().filter(p -> p.getSanpham().getId() == sanphamId).findFirst().get();
            item.setQuantity(item.getQuantity() + quantity);
        } else
            cartItems.add(new CartItem(sanPham, quantity));

    }

    public void updateCart(Long id, int quantity){
        SamPham sanPham = sanPhamRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("San Pham not found: "+id));
        if(cartItems.stream().filter(p->p.getSanpham().getId().equals(id)&&p.getQuantity()>0).count()>0){
            CartItem item = cartItems.stream().filter(p->p.getSanpham().getId()==id).findFirst().get();
            item.setQuantity(quantity);
        }
    }


    public List<CartItem> getCartItems(){return cartItems;}

    public void removeFormCart(Long sanphamId){
        cartItems.removeIf(i->i.getSanpham().getId().equals(sanphamId));
    }


    public void clearCart(){
        cartItems.clear();
    }

    public double getSubtotal(){
        return cartItems.stream().filter(p->p.getAmount()>0).mapToDouble(p->p.getAmount()).sum();
    }

}