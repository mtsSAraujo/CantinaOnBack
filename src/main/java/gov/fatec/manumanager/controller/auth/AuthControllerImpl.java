package gov.fatec.manumanager.controller.auth;

import gov.fatec.manumanager.service.auth.UsuarioAutenticacaoService;
import gov.fatec.manumanager.utils.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioAutenticacaoService customUserDetailsService;

    public String login(@RequestParam String email, @RequestParam String senha) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senha)
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        return jwtService.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
    }
}