package com.example._3semestereksamen.repositories;

import com.example._3semestereksamen.model.Party;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier
@Repository
public interface PartyRepository extends JpaRepository<Party,Long> {
}
