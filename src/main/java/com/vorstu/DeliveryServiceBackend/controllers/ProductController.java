package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.dto.response.FullProductDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.GroupDTO;
import com.vorstu.DeliveryServiceBackend.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("group")
    public List<GroupDTO> getGroups(){
        return productService.getGroups();
    }

    @GetMapping("group/{groupId}")
    public List<FullProductDTO> getProductsInGroup(@PathVariable Long groupId){
        return productService.getProductsInGroup(groupId);
    }

    @GetMapping("find")
    public List<FullProductDTO> getProductsByPattern(@RequestParam String pattern){
        return productService.findProductsByPattern(pattern);
    }
}
