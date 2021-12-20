package com.example._3semestereksamen.controller;

import com.example._3semestereksamen.model.Candidate;
import com.example._3semestereksamen.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable Long id){
        Candidate candidate = candidateService.findById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @GetMapping("/allCandidates")
    public ResponseEntity<List<Candidate>> allCandidates(){
        List<Candidate> candidates = candidateService.findAllCandidates();
        return new ResponseEntity<>(candidates,HttpStatus.OK);
    }

    @PostMapping("/candidate")
    public ResponseEntity<Candidate> newcandidate(@RequestBody Candidate candidate)throws URISyntaxException {
        Candidate result = null;
        result = candidateService.saveCandidate(candidate);
        return ResponseEntity.created(new URI("/customer/" + result.getCandidateID())).body(result);
    }

    @PutMapping("/candidate/{id}")
    public ResponseEntity<Candidate> updatecandidate(@PathVariable Long id, @RequestBody Candidate candidate){
        Candidate tmpCandidate = candidateService.updateCandidate(candidate, id);
        return ResponseEntity.ok().body(tmpCandidate);
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok().build();
    }



}
