package gov.fatec.manumanager.controller.auth;

import gov.fatec.manumanager.service.auth.UsuarioAutenticacaoService;
import gov.fatec.manumanager.utils.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioAutenticacaoService customUserDetailsService;

    @PostMapping("/login")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200")
    })
    public String login(@RequestParam String email, @RequestParam String senha) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return jwtUtil.generateToken(userDetails);
    }
}
