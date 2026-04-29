package com.pi.demo.services;

import com.pi.demo.entities.Pelagem;
import com.pi.demo.repositories.PelagemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PelagemServiceTest {

    @Mock
    private PelagemRepository pelagemRepository;

    @InjectMocks
    private PelagemService pelagemService;

    @Test
    void shouldReturnAllPelagens() {
        // Given
        Pelagem pelagem1 = new Pelagem(1L, "Curta");
        Pelagem pelagem2 = new Pelagem(2L, "Longa");
        List<Pelagem> expectedPelagens = Arrays.asList(pelagem1, pelagem2);

        when(pelagemRepository.findAll()).thenReturn(expectedPelagens);

        // When
        List<Pelagem> actualPelagens = pelagemService.findAll();

        // Then
        assertThat(actualPelagens).isEqualTo(expectedPelagens);
    }

    @Test
    void shouldReturnPelagemById() {
        // Given
        Long id = 1L;
        Pelagem expectedPelagem = new Pelagem(id, "Curta");

        when(pelagemRepository.findById(id)).thenReturn(Optional.of(expectedPelagem));

        // When
        Optional<Pelagem> actualPelagem = pelagemService.findById(id);

        // Then
        assertThat(actualPelagem).isPresent();
        assertThat(actualPelagem.get()).isEqualTo(expectedPelagem);
    }
}