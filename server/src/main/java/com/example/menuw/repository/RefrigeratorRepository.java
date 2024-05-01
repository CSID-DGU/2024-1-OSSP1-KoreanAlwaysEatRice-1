package com.example.menuw.repository;

import com.example.menuw.domain.Refrigerator;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefrigeratorRepository {
    private final EntityManager em;

    public void save(Refrigerator refrigerator) {
        em.persist(refrigerator);
    }

    public Refrigerator findOne(Long id) {
        return em.find(Refrigerator.class, id);
    }
}
