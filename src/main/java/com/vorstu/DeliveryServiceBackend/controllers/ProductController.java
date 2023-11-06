package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.GroupRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.FullProductDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.GroupDTO;
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

    @GetMapping("/{product_id}")
    public ResponseEntity getFullProduct(@PathVariable Long product_id){
        Optional<ProductEntity> productCandid = productRepository.findById(product_id);
        if(productCandid.isPresent()) {
            return ResponseEntity.ok().body(FullProductDTO.fromEntity(productCandid.get()));
        }

        return ResponseEntity.badRequest().body("Could not found product by id");
    }

    @GetMapping("group")
    public ResponseEntity getGroups(){
        List<GroupDTO> groups = StreamSupport.stream(groupRepository.findAll().spliterator(), false)
                .map(GroupDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(groups);
    }

    @GetMapping("group/{group_id}")
    public ResponseEntity getProductsInGroup(@PathVariable Long group_id){
        List<FullProductDTO> groups = StreamSupport.stream(groupRepository.findProductsInGroup(group_id).spliterator(), false)
                .map(FullProductDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(groups);
    }
}
