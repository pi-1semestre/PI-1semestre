package com.pi.demo.repositories;

import com.pi.demo.entities.Mensagem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    
    List<Mensagem> findByStatusTrue();
    
    List<Mensagem> findByStatusFalse();
    
    List<Mensagem> findByNomeAnimalContaining(String nome);

    @Query("SELECT m FROM Mensagem m WHERE m.nomeAnimal = :nomeAnimal")
    List<Mensagem> findByNomeAnimal(@Param("nomeAnimal") String nomeAnimal);
}