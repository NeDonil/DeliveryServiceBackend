package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.GroupRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.FullProductDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.GroupDTO;
import com.vorstu.DeliveryServiceBackend.mappers.FullProductMapper;
import com.vorstu.DeliveryServiceBackend.mappers.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GroupMapper groupListMapper;

    @Autowired
    FullProductMapper fullProductListMapper;

    @Transactional
    public List<GroupDTO> getGroups(){
        List<GroupEntity> groupEntities = new ArrayList();
        groupRepository.findAll().forEach(groupEntities::add);
        return groupListMapper.toDTOList(groupEntities);
    }

    @Transactional
    public List<FullProductDTO> getProductsInGroup(Long groupId){
        List<ProductEntity> productEntities = groupRepository.findProductsInGroup(groupId);
        return fullProductListMapper.toDTOList(productEntities);
    }

    @Transactional
    public List<FullProductDTO> findProductsByPattern(String pattern){
        List<ProductEntity> productEntities = productRepository.findProductsByPattern(pattern);
        return fullProductListMapper.toDTOList(productEntities);
    }
}
