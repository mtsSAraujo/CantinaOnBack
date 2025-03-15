package gov.fatec.manumanager.service;

import gov.fatec.manumanager.dto.request.UsuarioRequestDto;
import gov.fatec.manumanager.dto.response.UsuarioResponseDto;
import gov.fatec.manumanager.dto.converter.UsuarioConverter;
import gov.fatec.manumanager.entity.Usuario;
import gov.fatec.manumanager.exception.models.UserAlreadyExistsException;
import gov.fatec.manumanager.exception.models.UserNotFoundException;
import gov.fatec.manumanager.repository.UsuarioRepository;
import gov.fatec.manumanager.utils.TiposDeUsuario;
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

    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("ID: " + id + " não corresponde a nenhum usuário")
        );
        return UsuarioConverter.fromEntity(usuario);
    }

    public List<UsuarioResponseDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()) {
            throw new UserNotFoundException("Não existem usuários cadastrados");
        }
        return usuarios.stream().map(UsuarioConverter::fromEntity).collect(Collectors.toList());
    }

    public UsuarioResponseDto createUser(UsuarioRequestDto usuarioRequestDto) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(usuarioRequestDto.email());

        if (usuarioEncontrado.isPresent()) {
            throw new UserAlreadyExistsException("Email já cadastrado no sistema");
        }

        Usuario usuarioCriado = UsuarioConverter.fromDto(usuarioRequestDto);
        usuarioCriado.setSenha(passwordEncoder.encode(usuarioRequestDto.senha()));

        // Criar tecnico associado a esse usuário com valores default para especialização, disponibilidade e atribuições.
        if(usuarioRequestDto.tipoUsuario().equals(TiposDeUsuario.TECNICO)) {
            throw new RuntimeException();
        }

        return UsuarioConverter.fromEntity(usuarioRepository.save(usuarioCriado));
    }

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

    public Void deleteUser(Long id) {
        Optional<Usuario> optionalUsuarioEncontrado = usuarioRepository.findById(id);

        if(optionalUsuarioEncontrado.isPresent()) {
            Usuario usuarioEncontrado = optionalUsuarioEncontrado.get();
            usuarioRepository.delete(usuarioEncontrado);
        }
        throw new UserNotFoundException("ID: " + id + " não cadastrado");
    }
}
