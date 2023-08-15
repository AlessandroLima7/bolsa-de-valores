package A3.bolsa.services;

import A3.bolsa.domain.admin.Admin;
import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.papeis.dto.CadastroPapelDto;
import A3.bolsa.domain.usuario.ROLE;
import A3.bolsa.domain.usuario.dto.DadosCadastroUsuario;
import A3.bolsa.entities.AdminEntity;
import A3.bolsa.mappers.AdminMapper;
import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.mappers.PapeisMapper;
import A3.bolsa.repositories.AdminRepository;
import A3.bolsa.repositories.CarteiraRepository;
import A3.bolsa.repositories.InvestidorRepository;
import A3.bolsa.repositories.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
public class MuniciarDadosService implements ApplicationRunner {

    private final PapelRepository papelRepository;
    private final InvestidorRepository investidorRepository;

    private final CarteiraRepository carteiraRepository;

    private final AdminRepository adminRepository;

    @Autowired
    private PapeisMapper papeisMapper;

    @Autowired
    private InvestidorMapper investidorMapper;

    @Autowired
    private AdminMapper adminMapper;

    public MuniciarDadosService(PapelRepository papelRepository, InvestidorRepository investidorRepository, CarteiraRepository carteiraRepository, AdminRepository adminRepository) {
        this.papelRepository = papelRepository;
        this.investidorRepository = investidorRepository;
        this.carteiraRepository = carteiraRepository;
        this.adminRepository = adminRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Subindo as ações no H2
        List<Papeis> papeis = new ArrayList<>();
        papeis.add(new Papeis(new CadastroPapelDto("Itaú Unibanco", "ITUB4", 28.27, "O Itaú é um banco muito antigo e sólido no mercado. Oferecendo os mais diversos serviços financeiros.", 22000)));
        papeis.add(new Papeis(new CadastroPapelDto("Banco Bradesco", "BBDC4", 16.57, "O Bradesco é um banco.", 30000)));
        papeis.add(new Papeis(new CadastroPapelDto("Vivo Telefônica Brasil", "VIVT4", 42.27, "Empresa que atua em telecomunicações.", 10000)));

        papelRepository.saveAll(papeisMapper.listModelToEntity(papeis));


        //Subindo investidores no H2
        List<Investidor> investidores = new ArrayList<>();
        investidores.add(new Investidor(new DadosCadastroUsuario("Alessandro", "Lima", "alessandro@hotmail.com","ale", ROLE.INVESTIDOR)));
        investidores.add(new Investidor(new DadosCadastroUsuario("Marcos", "Medeiros", "marcos@hotmail.com", "marcos", ROLE.INVESTIDOR)));
        investidores.add(new Investidor(new DadosCadastroUsuario("Gustavo", "Guanabara", "gustavo@hotmail.com", "gustavo", ROLE.INVESTIDOR)));

        investidorRepository.saveAll(investidorMapper.listModelToEntity(investidores));


        Admin admin = new Admin(new DadosCadastroUsuario("Alessandro", "Lima", "sandro@hotmail.com", "ale", ROLE.ADMIN));

        AdminEntity adminEntity = adminMapper.modelToEntity(admin);

        adminRepository.save(adminEntity);

        var bolsaEmTempoReal = new BolsaThread(papelRepository, papeisMapper);
        bolsaEmTempoReal.start();

    }
}
