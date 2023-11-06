package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.GroupRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.FullProductDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.GroupDTO;
import com.vorstu.DeliveryServiceBackend.mappers.FullProductListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.GroupListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    GroupListMapper groupListMapper;

    @Autowired
    FullProductListMapper fullProductListMapper;

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
        List<GroupEntity> groupEntities = new ArrayList();
        groupRepository.findAll().forEach(groupEntities::add);
        return ResponseEntity.ok().body(groupListMapper.toDTOList(groupEntities));
    }

    @GetMapping("group/{group_id}")
    public ResponseEntity getProductsInGroup(@PathVariable Long group_id){
        List<ProductEntity> productEntities = groupRepository.findProductsInGroup(group_id);
        return ResponseEntity.ok().body(fullProductListMapper.toDTOList(productEntities));
    }
}
