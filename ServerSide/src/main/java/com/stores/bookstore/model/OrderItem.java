package com.stores.bookstore.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn (name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;

    private int quantity;
    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(int id, Order order, Book book, int quantity, BigDecimal price) {
        this.id = id;
        this.order = order;
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
