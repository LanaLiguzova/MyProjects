package com.stores.bookstore.service;

import com.stores.bookstore.model.*;
import com.stores.bookstore.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, CartItemRepository cartItemRepository, UserRepository userRepository, OrderStatusRepository orderStatusRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.bookRepository = bookRepository;
    }

    public List<Order> getAllOrderByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order addNewOrder(int userId) {

        List<CartItem> cartItemList = cartItemRepository.findByUserId(userId);
        if (cartItemList.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        Order order = new Order();
        order.setUser(userRepository.findById(userId).get());
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(orderStatusRepository.findById(1).get());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item:cartItemList) {
            Book book = item.getBook();

            if (book.getStock() < item.getQuantity()) {
                throw  new RuntimeException("Not enough books in stock!");
            }
            book.setStock(book.getStock() - item.getQuantity());
            bookRepository.save(book);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setPrice(book.getPrice());
            orderItem.setQuantity(item.getQuantity());

            orderItems.add(orderItem);

        }

        order.setItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        cartItemRepository.deleteAll(cartItemList);

        return savedOrder;
    }
}
