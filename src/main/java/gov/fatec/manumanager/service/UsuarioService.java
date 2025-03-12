package gov.fatec.manumanager.service;

import gov.fatec.manumanager.dto.UsuarioRequestDto;
import gov.fatec.manumanager.dto.UsuarioResponseDto;
import gov.fatec.manumanager.dto.converter.UsuarioConverter;
import gov.fatec.manumanager.entity.Usuario;
import gov.fatec.manumanager.exception.UserNotFoundException;
import gov.fatec.manumanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UsuarioConverter.fromEntity(usuario);
    }

    public List<UsuarioResponseDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()) {
            throw new UserNotFoundException();
        }
        return usuarios.stream().map(UsuarioConverter::fromEntity).collect(Collectors.toList());
    }

    public UsuarioResponseDto createUser(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuarioCriado = UsuarioConverter.fromDto(usuarioRequestDto);
        usuarioCriado.setSenha(passwordEncoder.encode(usuarioRequestDto.senha()));
        // Verificar se ja existe usuário com esse email e retornar exceção.
        return UsuarioConverter.fromEntity(usuarioRepository.save(usuarioCriado));
    }
}
