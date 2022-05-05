package com.example.test1zaddom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int weight;

    Cargo(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return weight == cargo.weight &&
                Objects.equals(name, cargo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    String getName() {
        return this.name;
    }

    int getWeight() {
        return this.weight;
    }

    public Cargo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}


