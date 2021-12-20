package com.example._3semestereksamen.controller;

import com.example._3semestereksamen.model.Kommune;
import com.example._3semestereksamen.service.KommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
public class KommuneController {

    private KommuneService kommuneService;

    @Autowired
    public KommuneController(KommuneService kommuneService){
        this.kommuneService = kommuneService;
    }

    @GetMapping("/kommune/{id}")
    public ResponseEntity<Kommune> getKommune(@PathVariable Long id){
        Kommune kommune = kommuneService.findById(id);
        return new ResponseEntity<>(kommune, HttpStatus.OK);
    }

    @PostMapping("/kommune")
    public ResponseEntity<Kommune> newkommune(@RequestBody Kommune kommune)throws URISyntaxException {
        Kommune result = null;
        result = kommuneService.saveKommune(kommune);
        return ResponseEntity.created(new URI("/Kommune/" + result.getKommuneID())).body(result);
    }

    @PutMapping("/kommune/{id}")
    public ResponseEntity<Kommune> updateKommune(@PathVariable Long id, @RequestBody Kommune kommune){
        Kommune tmpKommune = kommuneService.updateKommune(kommune, id);
        return ResponseEntity.ok().body(tmpKommune);
    }

    @DeleteMapping("/kommune/{id}")
    public ResponseEntity<?> deleteKommune(@PathVariable Long id) {
        kommuneService.deleteKommune(id);
        return ResponseEntity.ok().build();
    }



}
