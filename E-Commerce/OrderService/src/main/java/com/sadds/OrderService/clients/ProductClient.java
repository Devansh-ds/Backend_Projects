package com.sadds.OrderService.clients;

import com.sadds.OrderService.dto.ProductPurchaseRequest;
import com.sadds.OrderService.dto.ProductPurchaseResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", url = "${application.config.product-service-url}")
public interface ProductClient {

    @PostMapping("products/purchase")
    public Optional<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody @Valid List<ProductPurchaseRequest> purchaseRequest);

}
