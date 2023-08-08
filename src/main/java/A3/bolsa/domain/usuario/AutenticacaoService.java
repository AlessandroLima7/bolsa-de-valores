package A3.bolsa.domain.usuario;

import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.repositories.InvestidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private InvestidorRepository investidorRepository;

    @Autowired
    private InvestidorMapper investidorMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var investidorEntity = investidorRepository.findByEmail(email);
        Usuario usuario = investidorMapper.entityToModelUsuario(investidorEntity.get());
        return usuario;
    }
}
