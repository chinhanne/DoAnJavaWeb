package com.example.DA_JAVA.service;

import com.example.DA_JAVA.model.CartItem;
import com.example.DA_JAVA.model.Order;
import com.example.DA_JAVA.model.OrderDetail;
import com.example.DA_JAVA.repository.OrderDetailRepository;
import com.example.DA_JAVA.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService; // Assuming you have a CartService
    @Transactional
    public Order createOrder(Order order, List<CartItem> cartItems) {
        order.setCustomerName(order.getCustomerName());
        order.setAddress(order.getAddress());
        order.setNumber(order.getNumber());
        order.setNote(order.getNote());
        order = orderRepository.save(order);
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setSanpham(item.getSanpham());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }// Optionally clear the cart after order placement
        cartService.clearCart();
        return order;
    }
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found with id: " + orderId));
    }
    public double calculateOrderTotal(Order order) {
        double total = 0.0;
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            double productPrice = orderDetail.getSanpham().getGia();
            int quantity = orderDetail.getQuantity();
            double lineTotal = productPrice * quantity;
            total += lineTotal;
        }
        return total;
    }
}