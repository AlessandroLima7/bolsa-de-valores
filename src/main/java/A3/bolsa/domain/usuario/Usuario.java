package A3.bolsa.domain.usuario;


import A3.bolsa.domain.usuario.dto.DadosCadastroUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String senha;
    private ROLE role;

    public Usuario(DadosCadastroUsuario cadastroDto) {
        this.firstName = cadastroDto.firstName();
        this.lastName = cadastroDto.lastName();
        this.email = cadastroDto.email();
        this.senha = new BCryptPasswordEncoder().encode(cadastroDto.senha());
        this.role = cadastroDto.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(String.format("ROLE_%s", this.role)));
        return list;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
