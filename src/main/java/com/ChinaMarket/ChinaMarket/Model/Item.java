package com.ChinaMarket.ChinaMarket.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int requiredQuantity;
    String category;
    int price;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Cart cart;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    Product product;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Ordered order;
}