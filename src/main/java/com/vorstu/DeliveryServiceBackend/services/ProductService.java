package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.GroupRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.FullProductDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.GroupDTO;
import com.vorstu.DeliveryServiceBackend.exception.ProductNotFoundException;
import com.vorstu.DeliveryServiceBackend.mappers.FullProductListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.GroupListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GroupListMapper groupListMapper;

    @Autowired
    FullProductListMapper fullProductListMapper;

    public List<GroupDTO> getGroups(){
        List<GroupEntity> groupEntities = new ArrayList();
        groupRepository.findAll().forEach(groupEntities::add);
        return groupListMapper.toDTOList(groupEntities);
    }

    public List<FullProductDTO> getProductsInGroup(Long groupId){
        List<ProductEntity> productEntities = groupRepository.findProductsInGroup(groupId);
        return fullProductListMapper.toDTOList(productEntities);
    }

    public List<FullProductDTO> findProductsByPattern(String pattern){
        List<ProductEntity> productEntities = productRepository.findProductsByPattern(pattern);
        return fullProductListMapper.toDTOList(productEntities);
    }
}
