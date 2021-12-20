package com.example._3semestereksamen.controller;

import com.example._3semestereksamen.model.Candidate;
import com.example._3semestereksamen.model.Party;
import com.example._3semestereksamen.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
public class PartiController {


    private PartyService partyService;

    @Autowired
    public PartiController(PartyService partyService){
        this.partyService = partyService;
    }

    @GetMapping("/party/{id}")
    public ResponseEntity<Party> getParti(@PathVariable Long id){
        Party party = partyService.findById(id);
        return new ResponseEntity<>(party, HttpStatus.OK);
    }

    @GetMapping("/partyCandidates/{id}")
    public ResponseEntity<Set<Candidate>> partyCandidates(@PathVariable Long id){
        Set<Candidate> partyCandidates = partyService.findAllKandidaterOnParti(id);
        return new ResponseEntity<>(partyCandidates, HttpStatus.OK);
    }


    @GetMapping("/allParties")
    public ResponseEntity<List<Party>> allParties(){
        List<Party> allParties = partyService.findAllParties();
        return new ResponseEntity<>(allParties, HttpStatus.OK);
    }

    @PostMapping("/party")
    public ResponseEntity<Party> newParty(@RequestBody Party party)throws URISyntaxException {
        Party result = null;
        result = partyService.saveParty(party);
        return ResponseEntity.created(new URI("/Party/" + result.getPartyID())).body(result);
    }

    @PutMapping("/party/{id}")
    public ResponseEntity<Party> updateParty(@PathVariable Long id, @RequestBody Party party){
        Party tmpParty = partyService.updateParty(party, id);
        return ResponseEntity.ok().body(tmpParty);
    }

    @DeleteMapping("/party/{id}")
    public ResponseEntity<?> deleteParty(@PathVariable Long id) {
        partyService.deleteParty(id);
        return ResponseEntity.ok().build();
    }



}
