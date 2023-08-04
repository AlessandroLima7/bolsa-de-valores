package A3.bolsa.services;

import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.investidor.dtos.CadastroInvestidorDto;
import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.papeis.dto.CadastroPapelDto;
import A3.bolsa.entities.CarteiraEntity;
import A3.bolsa.entities.InvestidorEntity;
import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.mappers.PapeisMapper;
import A3.bolsa.repositories.CarteiraRepository;
import A3.bolsa.repositories.InvestidorRepository;
import A3.bolsa.repositories.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MuniciarDadosService implements ApplicationRunner {

    private final PapelRepository papelRepository;
    private final InvestidorRepository investidorRepository;

    private final CarteiraRepository carteiraRepository;

    @Autowired
    private PapeisMapper papeisMapper;

    @Autowired
    InvestidorMapper investidorMapper;

    public MuniciarDadosService(PapelRepository papelRepository, InvestidorRepository investidorRepository, CarteiraRepository carteiraRepository) {
        this.papelRepository = papelRepository;
        this.investidorRepository = investidorRepository;
        this.carteiraRepository = carteiraRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Subindo as ações no H2
        List<Papeis> papeis = new ArrayList<>();
        papeis.add(new Papeis(new CadastroPapelDto("Itaú Unibanco", "ITUB4", 28.27, "O Itaú é um banco muito antigo e sólido no mercado. Oferecendo os mais diversos serviços financeiros.", 700)));
        papeis.add(new Papeis(new CadastroPapelDto("Banco Bradesco", "BBDC4", 16.57, "O Bradesco é um banco.", 1000)));
        papeis.add(new Papeis(new CadastroPapelDto("Vivo Telefônica Brasil", "VIVT4", 42.27, "Empresa que atua em telecomunicações.", 100)));

        papelRepository.saveAll(papeisMapper.listModelToEntity(papeis));


        //Subindo investidores no H2
        List<Investidor> investidores = new ArrayList<>();
        investidores.add(new Investidor(new CadastroInvestidorDto("Alessandro", "Lima", "alessandro@hotmail.com", "ale")));
        investidores.add(new Investidor(new CadastroInvestidorDto("Marcos", "Medeiros", "marcos@hotmail.com", "marcos")));
        investidores.add(new Investidor(new CadastroInvestidorDto("Gustavo", "Guanabara", "gustavo@hotmail.com", "gustavo")));

        investidorRepository.saveAll(investidorMapper.listModelToEntity(investidores));



    }
}
