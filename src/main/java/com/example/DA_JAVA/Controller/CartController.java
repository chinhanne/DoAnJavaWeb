package com.example.DA_JAVA.Controller;

import com.example.DA_JAVA.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String showCart(Model model){
        model.addAttribute("cartItems", cartService.getCartItems());
        return "/cart/cart";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam Long sanphamId,@RequestParam int quantity){
        cartService.addToCart(sanphamId, quantity);
        return "redirect:/cart";
    }
    @GetMapping("/remove/{sanphamId}")
    public String removeFromCart(@PathVariable Long sanphamId){
        cartService.removeFromCart(sanphamId);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart(){
        cartService.clearCart();
        return "redirect:/cart";
    }}