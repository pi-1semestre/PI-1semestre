package com.pi.demo.repositories;

import com.pi.demo.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByEspecieIdEspecie(Long especieId);
    List<Animal> findByPorteIdPorte(Long porteId);
    List<Animal> findByPelagemIdPelagem(Long pelagemId);
    List<Animal> findBySexoIdSexo(Long sexoId);
    List<Animal> findByNomeAnimalContaining(String nome);

    @Query("SELECT a FROM Animal a WHERE a.especie.especie = :especieNome")
    List<Animal> findByEspecieNome(@Param("especieNome") String especieNome);
}