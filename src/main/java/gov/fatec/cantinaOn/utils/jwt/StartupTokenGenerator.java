package gov.fatec.cantinaOn.utils.jwt;

import gov.fatec.cantinaOn.entity.Usuario;
import gov.fatec.cantinaOn.repository.UsuarioRepository;
import gov.fatec.cantinaOn.utils.TiposDeUsuario;
import gov.fatec.cantinaOn.utils.enumStatus.StatusUsuario;
import gov.fatec.cantinaOn.utils.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupTokenGenerator {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Bean
    public CommandLineRunner criarUsuarioRoot() {
        return args -> {
            if (usuarioRepository.findByEmail("root@example.com").isEmpty()) {
                Usuario root = Usuario.builder()
                        .nome("Root User")
                        .email("root@example.com")
                        .senha(passwordEncoder.encode("root123"))
                        .tipoUsuario(TiposDeUsuario.ADMINISTRADOR)
                        .status(StatusUsuario.ATIVO)
                        .build();

                usuarioRepository.save(root);
                log.info("Usuário root criado com sucesso.");
            }

            // Gera um token JWT para o usuário root
            Usuario root = usuarioRepository.findByEmail("root@example.com").orElseThrow();
            String token = jwtService.generateToken(root.getUsername(), root.getAuthorities());
            log.info("🔑 Token JWT gerado para o usuário root: {}", token);
        };
    }
}

