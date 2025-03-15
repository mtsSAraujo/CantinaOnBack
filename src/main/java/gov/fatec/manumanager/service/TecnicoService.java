package gov.fatec.manumanager.service;

import gov.fatec.manumanager.dto.converter.TecnicoConverter;
import gov.fatec.manumanager.dto.response.TecnicoResponseDto;
import gov.fatec.manumanager.entity.Tecnico;
import gov.fatec.manumanager.entity.Usuario;
import gov.fatec.manumanager.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoResponseDto createTecnico(Usuario usuario) {
        Tecnico tecnico = Tecnico.builder()
                .usuario(usuario)
                .especializacao("DEFAULT")
                .disponibilidade("DEFAULT")
                .atribuicoes(new ArrayList<>())
                .build();

        tecnicoRepository.save(tecnico);
        return TecnicoConverter.fromEntity(tecnico);
    }
}
