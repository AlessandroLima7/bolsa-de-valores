package A3.bolsa.domain.usuario;

import A3.bolsa.mappers.AdminMapper;
import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.repositories.AdminRepository;
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
    private AdminRepository adminRepository;

    @Autowired
    private InvestidorMapper investidorMapper;

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var investidorEntity = investidorRepository.findByEmail(email);
        var adminEntity = adminRepository.findByEmail(email);
        if(investidorEntity.isPresent()){
            Usuario usuario = investidorMapper.entityToModelUsuario(investidorEntity.get());
            return usuario;
        }
        else if(adminEntity.isPresent()) {

            Usuario usuario = adminMapper.entityToModelUsuario(adminEntity.get());
            System.out.println(usuario.getRole());
            return usuario;
        }
        else{
            return null;
        }


    }
}
