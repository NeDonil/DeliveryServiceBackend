package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.dto.AssemblerDTO;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.AssemblerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    AssemblerRepository assemblerRepository;

    @Autowired
    AssemblerRepository courierRepository;

    @GetMapping("assembler")
    ResponseEntity getAssemblers(){
        return ResponseEntity.ok().body(StreamSupport.stream(assemblerRepository.findAll().spliterator(), false)
                .map(AssemblerDTO.Response::fromEntity)
                .collect(Collectors.toList())
        );
    }

    @PostMapping("assembler")
    ResponseEntity addAssembler(@RequestBody AssemblerDTO.Request assembler){
        AssemblerEntity assemblerEntity = new AssemblerEntity(assembler.getFio(), assembler.getEmail(), assembler.getPassword());
        assemblerRepository.save(assemblerEntity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("assembler")
    ResponseEntity updateAssembler(@RequestParam Long assemblerId,
                                @RequestBody AssemblerDTO.Request assembler){
        AssemblerEntity assemblerEntity = assemblerRepository.findById(assemblerId).get();
        assemblerEntity.setFio(assembler.getFio());
        assemblerRepository.save(assemblerEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("assembler")
    ResponseEntity deleteAssembler(@RequestParam Long assemblerId){
        assemblerRepository.deleteById(assemblerId);
        return ResponseEntity.ok().build();
    }
}
