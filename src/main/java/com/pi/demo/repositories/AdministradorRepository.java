package com.pi.demo.repositories;

import com.pi.demo.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByEmailAdm(String emailAdm);

    @Query("SELECT DISTINCT a FROM Administrador a JOIN Animal an ON an.administrador.idAdministrador = a.idAdministrador WHERE an.nomeAnimal = :nomeAnimal")
    List<Administrador> findByAnimalNome(@Param("nomeAnimal") String nomeAnimal);

    @Query("SELECT DISTINCT a FROM Administrador a JOIN Animal an ON an.administrador.idAdministrador = a.idAdministrador WHERE an.especie.especie = :especie")
    List<Administrador> findByAnimalEspecie(@Param("especie") String especie);
}