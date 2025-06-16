package gov.fatec.cantinaOn.controller.auth;

import gov.fatec.cantinaOn.dto.request.UserInfo;
import gov.fatec.cantinaOn.service.auth.UsuarioAutenticacaoService;
import gov.fatec.cantinaOn.utils.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioAutenticacaoService customUserDetailsService;

    @Override
    public String login(@RequestBody UserInfo userInfo) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userInfo.email(), userInfo.password())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userInfo.email());

        return jwtService.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
    }
}