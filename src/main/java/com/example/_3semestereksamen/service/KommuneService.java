package com.example._3semestereksamen.service;

import com.example._3semestereksamen.model.Kommune;
import com.example._3semestereksamen.model.Party;
import com.example._3semestereksamen.repositories.KommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

@Service
public class KommuneService {

    private KommuneRepository kommuneRepository;

    @Autowired
    public KommuneService(KommuneRepository kommuneRepository){
        this.kommuneRepository = kommuneRepository;
    }

    public Kommune findById(Long id) {
        return kommuneRepository.findById(id).orElseThrow(() -> new NoResultException("Kommune with id: " + id + " does not exist!"));
    }

    public List<Kommune> findAllKommuner(){
        return kommuneRepository.findAll();
    }

    public Kommune saveKommune(Kommune kommune){
        return kommuneRepository.save(kommune);
    }

    public Kommune updateKommune(Kommune kommune, Long id) {
        Kommune tmpKommune = kommuneRepository.findById(id).orElseThrow(() -> new NoResultException("kommune with id: " + id + " does not exist!"));
        tmpKommune.setKommuneID(kommune.getKommuneID());
        tmpKommune.setKommuneName(kommune.getKommuneName());
        return kommuneRepository.save(tmpKommune);
    }

    public void deleteKommune(Long id){
        kommuneRepository.deleteById(id);
    }

    public Set<Party> findAllPartierOnKommune(Long id){
        Kommune tmpKommune = kommuneRepository.findById(id).orElseThrow(()-> new NoResultException("Kommune with id: " + id + " was not found"));
        Set<Party> partier = tmpKommune.getKommunePartySet();
        return partier;
    }

}
