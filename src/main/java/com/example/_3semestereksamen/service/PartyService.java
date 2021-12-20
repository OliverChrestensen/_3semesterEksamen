package com.example._3semestereksamen.service;

import com.example._3semestereksamen.model.Candidate;
import com.example._3semestereksamen.model.Party;
import com.example._3semestereksamen.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

@Service
public class PartyService {

    private PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository){
        this.partyRepository = partyRepository;
    }

    public Party findById(Long id) {
        return partyRepository.findById(id).orElseThrow(() -> new NoResultException("Party with id: " + id + " does not exist!"));
    }

    public List<Party> findAllParties(){
        return partyRepository.findAll();
    }

    public Party saveParty(Party party){
        return partyRepository.save(party);
    }

    public Party updateParty(Party party, Long id) {
        Party tmpParty = partyRepository.findById(id).orElseThrow(() -> new NoResultException("Party with id: " + id + " does not exist!"));
        tmpParty.setPartyID(party.getPartyID());
        tmpParty.setPartyName(party.getPartyName());
        tmpParty.setPartyVotes(party.getPartyVotes());
        return partyRepository.save(tmpParty);
    }

    public void deleteParty(Long id){
        partyRepository.deleteById(id);
    }

    public Set<Candidate> findAllKandidaterOnParti(Long id){
        Party tmpParty = partyRepository.findById(id).orElseThrow(()-> new NoResultException("Party with id: " + id + " was not found"));
        Set<Candidate> candidates = tmpParty.getCandidateSet();
        return candidates;
    }


}
