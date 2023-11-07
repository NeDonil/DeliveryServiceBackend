package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.GroupRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.FullProductDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.GroupDTO;
import com.vorstu.DeliveryServiceBackend.mappers.FullProductListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.GroupListMapper;
import com.vorstu.DeliveryServiceBackend.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/product")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity getFullProduct(@PathVariable Long productId){
        try{
            return ResponseEntity.ok().body(productService.getFullProduct(productId));
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("group")
    public ResponseEntity getGroups(){
        try{
            return ResponseEntity.ok().body(productService.getGroups());
        } catch (Exception ex){
            log.warn(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("group/{groupId}")
    public ResponseEntity getProductsInGroup(@PathVariable Long groupId){
        return ResponseEntity.ok().body(productService.getProductsInGroup(groupId));
    }
}
