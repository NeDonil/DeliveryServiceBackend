package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.dto.FullProductDTO;
import com.vorstu.DeliveryServiceBackend.db.dto.GroupDTO;
import com.vorstu.DeliveryServiceBackend.db.dto.ShortProductDTO;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.GroupRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/product")
public class UnauthorizedController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity getFullProduct(@RequestParam Long product_id){
        Optional<ProductEntity> productCandid = productRepository.findById(product_id);
        if(productCandid.isPresent()) {
            return ResponseEntity.ok().body(FullProductDTO.fromEntity(productCandid.get()));
        }

        return ResponseEntity.badRequest().body("Could not found product by id");
    }

    @GetMapping("groups")
    public ResponseEntity getGroups(){
        List<GroupDTO> groups = StreamSupport.stream(groupRepository.findAll().spliterator(), false)
                .map(value -> GroupDTO.fromEntity(value))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(groups);
    }

    @GetMapping("group")
    public ResponseEntity getGroups(@RequestParam Long group_id){
        List<ShortProductDTO> groups = StreamSupport.stream(groupRepository.findProductsInGroup(group_id).spliterator(), false)
                .map(value -> ShortProductDTO.fromEntity(value))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(groups);
    }
}
