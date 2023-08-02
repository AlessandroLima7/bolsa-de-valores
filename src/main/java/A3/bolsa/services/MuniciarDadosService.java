package A3.bolsa.services;

import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;
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
        var papel1 = new Papeis("Itaú Unibanco", "ITUB4", 28.27, "O Itaú é um banco muito antigo e sólido no mercado. Oferecendo os mais diversos serviços financeiros.", 1000);
        var papel2 = new Papeis("Banco Bradesco", "BBDC4", 16.57, "O Bradesco é um banco.", 1000);
        var papelEntity1 = papeisMapper.modelToEntity(papel1);
        var papelEntity2 = papeisMapper.modelToEntity(papel2);
        papelRepository.save(papelEntity1);
        papelRepository.save(papelEntity2);


        var investidor = new Investidor("Alessandro", "Lima", "alessandro@hotmail.com", "ale");

        var investidorEntity1 = investidorMapper.modelToEntity(investidor);

        investidorRepository.save(investidorEntity1);



    }
}
