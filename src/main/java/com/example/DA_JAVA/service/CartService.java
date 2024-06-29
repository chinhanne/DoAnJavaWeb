package com.example.DA_JAVA.service;

import com.example.DA_JAVA.model.CartItem;
import com.example.DA_JAVA.model.SamPham;
import com.example.DA_JAVA.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private SanPhamRepository sanphamRepository;
//    public void addToCart(Long sanphamId, int quantity) {
//        SamPham sanpham = sanphamRepository.findById(sanphamId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + sanphamId));
//        cartItems.add(new CartItem(sanpham, quantity));
//    }

    public void addToCart(Long sanphamId, int quantity) {
        SamPham sanpham = sanphamRepository.findById(sanphamId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + sanphamId));

        Optional<CartItem> existingCartItem = cartItems.stream()
                .filter(item -> item.getSanpham().getId().equals(sanphamId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItems.add(new CartItem(sanpham, quantity));
        }
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void removeFromCart(Long sanphamId) {
        cartItems.removeIf(item -> item.getSanpham().getId().equals(sanphamId));
    }
    public void clearCart() {
        cartItems.clear();
    }

}
