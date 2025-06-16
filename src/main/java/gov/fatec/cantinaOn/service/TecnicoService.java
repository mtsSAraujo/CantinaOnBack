package gov.fatec.cantinaOn.service;

import gov.fatec.cantinaOn.dto.converter.TecnicoConverter;
import gov.fatec.cantinaOn.dto.request.TecnicoRequestDto;
import gov.fatec.cantinaOn.dto.response.TecnicoResponseDto;
import gov.fatec.cantinaOn.entity.Tecnico;
import gov.fatec.cantinaOn.entity.Usuario;
import gov.fatec.cantinaOn.exception.models.MustBeTechnicianException;
import gov.fatec.cantinaOn.exception.models.UserNotFoundException;
import gov.fatec.cantinaOn.repository.TecnicoRepository;
import gov.fatec.cantinaOn.repository.UsuarioRepository;
import gov.fatec.cantinaOn.utils.TiposDeUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final UsuarioRepository usuarioRepository;

    public void createTechnician(Usuario usuario) {
        Tecnico tecnico = Tecnico.builder()
                .usuario(usuario)
                .especializacao("DEFAULT")
                .disponibilidade("DEFAULT")
                .atribuicoes(new ArrayList<>())
                .build();

        tecnicoRepository.save(tecnico);
        TecnicoConverter.fromEntity(tecnico);
    }

    public TecnicoResponseDto updateTechnician(Long id, TecnicoRequestDto tecnicoRequestDto) {
        Optional<Usuario> userFound = usuarioRepository.findById(id);
        if(userFound.isPresent()) {
            if(userFound.get().getTipoUsuario().equals(TiposDeUsuario.TECNICO)) {
                Tecnico tecnico = tecnicoRepository.findByUsuarioId(id).orElseThrow(UserNotFoundException::new);

                tecnico.setEspecializacao(tecnicoRequestDto.especialidade());
                tecnico.setDisponibilidade(tecnicoRequestDto.disponibilidade());
                tecnico.setAtribuicoes(tecnicoRequestDto.atribuicoes());

                tecnicoRepository.save(tecnico);

                return TecnicoConverter.fromEntity(tecnico);
            } else {
                throw new MustBeTechnicianException("Usuário precisa ser do tipo 'TECNICO' para atualizar seus dados!");
            }
        } else {
            throw new UserNotFoundException("ID: " + id + " não cadastrado");
        }
    }
}
