package com.stores.bookstore.controller;

import com.stores.bookstore.model.CartItem;
import com.stores.bookstore.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartItemService cartItemService;


    public CartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCartItemsByUserId(@PathVariable int userId) {
        return cartItemService.getAllCartItems(userId);
    }

    @PostMapping
    public CartItem addItemToCart(@RequestParam int userId, @RequestParam int bookId, @RequestParam int quantity) {
        return cartItemService.addCartItem(userId, bookId, quantity);
    }

    @DeleteMapping ("/{cartItemId}")
    public void deleteCartItem(@PathVariable int cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }

    @DeleteMapping
    public void deleteAllCartItems(@RequestParam List<CartItem> cartItems) {
        cartItemService.deleteAllCartItems(cartItems);
    }




}
