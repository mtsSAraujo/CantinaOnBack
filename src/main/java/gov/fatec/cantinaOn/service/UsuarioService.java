package gov.fatec.cantinaOn.service;

import gov.fatec.cantinaOn.dto.request.UsuarioRequestDto;
import gov.fatec.cantinaOn.dto.response.UsuarioResponseDto;
import gov.fatec.cantinaOn.dto.converter.UsuarioConverter;
import gov.fatec.cantinaOn.entity.Usuario;
import gov.fatec.cantinaOn.exception.models.InactiveUserException;
import gov.fatec.cantinaOn.exception.models.UserAlreadyExistsException;
import gov.fatec.cantinaOn.exception.models.UserNotFoundException;
import gov.fatec.cantinaOn.repository.UsuarioRepository;
import gov.fatec.cantinaOn.utils.TiposDeUsuario;
import gov.fatec.cantinaOn.utils.enumStatus.StatusUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final TecnicoService tecnicoService;

    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("ID: " + id + " não corresponde a nenhum usuário")
        );
        return UsuarioConverter.fromEntity(usuario);
    }

    public List<UsuarioResponseDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioConverter::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponseDto createUser(UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(usuarioRequestDto.email());

        if (usuarioEncontrado.isPresent()) {
            throw new UserAlreadyExistsException("Email já cadastrado no sistema");
        }

        Usuario usuarioCriado = UsuarioConverter.fromDto(usuarioRequestDto);
        usuarioCriado.setSenha(passwordEncoder.encode(usuarioRequestDto.senha()));
        usuarioRepository.save(usuarioCriado);

        if(usuarioRequestDto.tipoUsuario().equals(TiposDeUsuario.TECNICO)) {
            tecnicoService.createTechnician(usuarioCriado);
        }

        return UsuarioConverter.fromEntity(usuarioCriado);
    }

    @Transactional
    public UsuarioResponseDto updateUser(Long id, UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> optionalUsuarioEncontrado = usuarioRepository.findById(id);

        if(optionalUsuarioEncontrado.isPresent()) {
            Usuario usuarioEncontrado = optionalUsuarioEncontrado.get();

            usuarioEncontrado.setNome(usuarioRequestDto.nome());
            usuarioEncontrado.setEmail(usuarioRequestDto.email());
            usuarioEncontrado.setSenha(passwordEncoder.encode(usuarioRequestDto.senha()));
            usuarioEncontrado.setTipoUsuario(usuarioRequestDto.tipoUsuario());
            usuarioEncontrado.setStatus(usuarioRequestDto.status());

            return UsuarioConverter.fromEntity(usuarioRepository.save(usuarioEncontrado));
        }
        throw new UserNotFoundException("ID: " + id + " não cadastrado");
    }

    @Transactional
    public void deleteUser(Long id) {
        Optional<Usuario> optionalUsuarioEncontrado = usuarioRepository.findById(id);

        if (optionalUsuarioEncontrado.isPresent()) {
            Usuario usuarioEncontrado = optionalUsuarioEncontrado.get();

            if(usuarioEncontrado.getStatus() == StatusUsuario.INATIVO) {
                throw new InactiveUserException("Usuário já está inativado no sistema");
            }

            usuarioEncontrado.setStatus(StatusUsuario.INATIVO);
            usuarioRepository.save(usuarioEncontrado);
        } else {
            throw new UserNotFoundException("ID: " + id + " não cadastrado");
        }
    }
}
