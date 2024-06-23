package com.example.DA_JAVA.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String Address;
    private String Number;
    private String Note;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
