package org.example.data.repository;

import org.example.data.entity.Difficulty;
import org.example.data.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultiesRepository extends JpaRepository<Difficulty, Integer> {
}
