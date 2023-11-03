package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.dto.GroupDTO;
import com.vorstu.DeliveryServiceBackend.db.dto.ProductDTO;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.GroupRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity getFullProduct(@RequestParam Long product_id){
        Optional<ProductEntity> productCandid = productRepository.findById(product_id);
        if(productCandid.isPresent()) {
            return ResponseEntity.ok().body(ProductDTO.Full.Response.fromEntity(productCandid.get()));
        }

        return ResponseEntity.badRequest().body("Could not found product by id");
    }

    @GetMapping("group")
    public ResponseEntity getGroups(){
        List<GroupDTO.Response> groups = StreamSupport.stream(groupRepository.findAll().spliterator(), false)
                .map(GroupDTO.Response::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(groups);
    }

    @GetMapping("group/{group_id}")
    public ResponseEntity getProductsInGroup(@PathVariable Long group_id){
        List<ProductDTO.Short.Response> groups = StreamSupport.stream(groupRepository.findProductsInGroup(group_id).spliterator(), false)
                .map(ProductDTO.Short.Response::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(groups);
    }
}
