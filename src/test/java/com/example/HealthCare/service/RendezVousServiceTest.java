package com.example.HealthCare.service;

import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.mapper.RendezVousMapper;
import com.example.HealthCare.model.RendezVous;
import com.example.HealthCare.repository.RendezVousRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RendezVousServiceTest {
    @Mock RendezVousRepository repository;
    @Mock RendezVousMapper mapper;
    @InjectMocks RendezVousService service;

    @Test
    public void testAjouter(){
        RendezVousDTO dto = new RendezVousDTO();
        when(repository.save(any())).thenReturn(new RendezVous());
        when(mapper.toDTO(any())).thenReturn(dto);

        assertNotNull(service.ajouter(dto));
    }

    @Test
    public void testAnnuler(){
        RendezVous r = new RendezVous();
        when(repository.findById(1L)).thenReturn(Optional.of(r));
        when(repository.save(any())).thenReturn(r);
        when(mapper.toDTO(any())).thenReturn(new RendezVousDTO());

        service.annuler(1L);
        assertEquals("ANNULE" , r.getStatut());
    }

    @Test
    public void testLister(){
        when(repository.findAll()).thenReturn(List.of(new RendezVous()));
        when(mapper.toDTOList(any())).thenReturn(List.of(new RendezVousDTO()));

        assertFalse(service.lister().isEmpty());
    }

    @Test
    public void testModifier(){
        RendezVous r = new RendezVous();
        RendezVousDTO dto = new RendezVousDTO();
        dto.setStatut("CONFIRME");

        when(repository.findById(1L)).thenReturn(Optional.of(r));
        when(repository.save(any())).thenReturn(r);
        when(mapper.toDTO(any())).thenReturn(dto);

        RendezVousDTO result = service.modifier(1L , dto);
        assertEquals("CONFIRME" , result.getStatut());
    }

    @Test
    public void testRechercherParPatient(){
        when(repository.findByPatientId(1L)).thenReturn(new RendezVous());
        when(mapper.toDTO(any())).thenReturn(new RendezVousDTO());

        assertNotNull(service.rechercherParPatientId(1L));
    }

    @Test
     public void testRechercherParMedecin() {
        when(repository.findByMedecinId(1L)).thenReturn(new RendezVous());
        when(mapper.toDTO(any())).thenReturn(new RendezVousDTO());

        assertNotNull(service.rechercherParMedecinId(1L));
    }
}