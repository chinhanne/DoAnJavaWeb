package com.example.DA_JAVA.Controller;

import com.example.DA_JAVA.model.Order;
import com.example.DA_JAVA.service.CartService;
import com.example.DA_JAVA.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderAdminController {
    private final OrderService orderService;
    @Autowired
    private CartService cartService;


    public OrderAdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrder();
        model.addAttribute("orders", orders);
        return "/ordersAdmin/orders";
    }
    @GetMapping("/orderDetails/{orderId}")
    public String getOrderDetails(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        double orderTotal = orderService.calculateOrderTotal(order);
        model.addAttribute("order", order);
        model.addAttribute("orderTotal", orderTotal);
        return "/ordersAdmin/order-details";
    }
}
