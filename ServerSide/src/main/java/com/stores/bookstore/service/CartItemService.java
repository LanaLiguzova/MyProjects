package com.stores.bookstore.service;

import com.stores.bookstore.model.Book;
import com.stores.bookstore.model.CartItem;
import com.stores.bookstore.model.User;
import com.stores.bookstore.repository.BookRepository;
import com.stores.bookstore.repository.CartItemRepository;
import com.stores.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public CartItemService(CartItemRepository cartItemRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    public List<CartItem> getAllCartItems(int userId) {
        return cartItemRepository.findByUserId(userId);
    }

//    public Optional<CartItem> getCartItem(int id) {
//        return cartItemRepository.findById(id);
//    }

    // check it later!!!!!!
    public CartItem addCartItem(int userId, int bookId, int quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));



        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void deleteAllCartItems(List<CartItem> cartItems) {
        cartItemRepository.deleteAll(cartItems);
    }
}
