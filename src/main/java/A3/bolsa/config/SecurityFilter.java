package A3.bolsa.config;

import A3.bolsa.mappers.AdminMapper;
import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.repositories.AdminRepository;
import A3.bolsa.repositories.InvestidorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private InvestidorRepository investidorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private InvestidorMapper investidorMapper;

    @Autowired
    private AdminMapper adminMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);

            var investidorEntity = investidorRepository.findByEmail(subject);
            var adminEntity = adminRepository.findByEmail(subject);
            if(investidorEntity.isPresent()) {
                var usuario = investidorMapper.entityToModelUsuario(investidorEntity.get());
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (adminEntity.isPresent()){
                var usuario = adminMapper.entityToModelUsuario(adminEntity.get());
                var authetication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authetication);
            }

        }

        filterChain.doFilter(request, response);
    }
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

    }

