package com.example._3semestereksamen.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "kommune")
public class Kommune {

 @Id
 @Column(name = "kommune_id")
 @GeneratedValue(strategy =  GenerationType.IDENTITY)
 private Long kommuneID;

 @Column(name = "kommune_navn")
 private String kommuneName;

 @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
 @JoinColumn(name = "kommune_id")
 @JsonBackReference
 private Set<Party> kommunePartySet = new HashSet<>();

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Kommune kommune = (Kommune) o;
  return Objects.equals(kommuneID, kommune.kommuneID);
 }

 @Override
 public int hashCode() {
  return Objects.hash(kommuneID);
 }
}
