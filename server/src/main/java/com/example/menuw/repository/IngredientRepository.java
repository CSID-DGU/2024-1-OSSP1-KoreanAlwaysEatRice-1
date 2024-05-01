package com.example.menuw.repository;

import com.example.menuw.domain.Ingredient;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IngredientRepository {
    private final EntityManager em;

    public void save(Ingredient ingredient) {
        if (ingredient.getIngredientId() == null) {
            em.persist(ingredient);
        } else {
            em.merge(ingredient);
        }
    }

    public Ingredient findOne(Long id) {
        return em.find(Ingredient.class, id);
    }

    public List<Ingredient> findAll() {
        return em.createQuery("select i from Ingredient i", Ingredient.class).getResultList();
    }
}
