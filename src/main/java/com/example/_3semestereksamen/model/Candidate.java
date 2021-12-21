package com.example._3semestereksamen.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @Column(name = "candidate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateID;

    private String candidateName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "party_id")
    @JsonBackReference
    private Party Party;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(candidateID, candidate.candidateID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateID);
    }
}
