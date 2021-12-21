package com.example._3semestereksamen.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "party")
public class Party {
    @Id
    @Column(name = "party_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyID;

    @Column(name = "party_name")
    private String partyName;

    @Nullable
    @Column(name = "party_votes")
    private Integer partyVotes;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "kommune_id")
    private Kommune kommune;

    @OneToMany(orphanRemoval = true)
    @JoinColumn (name = "party_id")
    @JsonBackReference
    private Set<Candidate> candidateSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return Objects.equals(partyID, party.partyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partyID);
    }
}
