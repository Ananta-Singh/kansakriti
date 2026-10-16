package com.kansakriti.user_service.controller;
import com.kansakriti.user_service.dto.SellerRegisterRequest;
import com.kansakriti.user_service.model.Seller;
import com.kansakriti.user_service.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Seller> register(@RequestBody SellerRegisterRequest request) {
        Seller seller = sellerService.register(request);
        return ResponseEntity.ok(seller);
    }
}
