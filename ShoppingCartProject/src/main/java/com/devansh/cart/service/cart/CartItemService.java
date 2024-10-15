package com.devansh.cart.service.cart;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.devansh.cart.exception.ResourceNotFoundException;
import com.devansh.cart.model.Cart;
import com.devansh.cart.model.CartItem;
import com.devansh.cart.model.Product;
import com.devansh.cart.repository.CartItemRepository;
import com.devansh.cart.repository.CartRepository;
import com.devansh.cart.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{
	
	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final IProductService productService;
	private final ICartService cartService;
	
	@Override
	public void addItemToCart(Long cartId, Long productId, int quantity) {
		Cart cart = cartService.getCart(cartId);
		Product product = productService.getProductById(productId);
		CartItem cartItem = cart.getCartItems().stream()
								.filter(item -> item.getProduct().getId().equals(productId))
								.findFirst()
								.orElse(new CartItem());
		if (cartItem.getId() == null) {
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setUnitPrice(product.getPrice());
		}
		else {
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
		}
		cartItem.setTotalPrice();
		cart.addItem(cartItem);
		cartItemRepository.save(cartItem);
		cartRepository.save(cart);
	}

	@Override
	public void removeItemFromCart(Long cartId, Long productId) {
		Cart cart = cartService.getCart(cartId);
		CartItem itemToRemove = getCartItem(cartId, productId);
		cart.removeItem(itemToRemove);
		cartRepository.save(cart);
		
	}

	@Override
	public void updateItemQuantity(Long cartId, Long productId, int quantity) {
		Cart cart = cartService.getCart(cartId);
		cart.getCartItems()
			.stream()
			.filter(item -> item.getProduct().getId().equals(productId))
			.findFirst()
			.ifPresent(item -> {
				item.setQuantity(quantity);
				item.setUnitPrice(item.getProduct().getPrice());
				item.setTotalPrice();
			});
		BigDecimal totalAmount = cart.getCartItems()
									 .stream()
									 .map(CartItem::getTotalPrice)
									 .reduce(BigDecimal.ZERO, BigDecimal::add);
		cart.setTotalAmount(totalAmount);
		cartRepository.save(cart);	
	}

	@Override
	public CartItem getCartItem(Long cartId, Long productId) {
		Cart cart = cartService.getCart(cartId);
		return cart.getCartItems()
				.stream()
				.filter(item -> item.getProduct().getId().equals(productId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Product Not found"));
	}
}
















