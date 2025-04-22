package com.stores.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table (name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;

    private int quantity;

}
