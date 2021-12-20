package com.example._3semestereksamen.service;
import com.example._3semestereksamen.model.Candidate;
import com.example._3semestereksamen.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class CandidateService {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElseThrow(() -> new NoResultException("Candidate with id: " + id + " does not exist!"));
    }

    public List<Candidate> findAllCandidates(){
        return candidateRepository.findAll();
    }

    public Candidate saveCandidate( Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Candidate candidate, Long id) {
        Candidate tmpCandidate = candidateRepository.findById(id).orElseThrow(() -> new NoResultException("Candidate with id: " + id + " does not exist!"));
        tmpCandidate.setCandidateID(candidate.getCandidateID());
        tmpCandidate.setCandidateName(candidate.getCandidateName());
        return candidateRepository.save(tmpCandidate);
    }

    public void deleteCandidate(Long id){
        candidateRepository.deleteById(id);
    }


}
