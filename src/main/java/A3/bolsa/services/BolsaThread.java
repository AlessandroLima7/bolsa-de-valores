package A3.bolsa.services;

import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.entities.PapeisEntity;
import A3.bolsa.mappers.PapeisMapper;
import A3.bolsa.repositories.PapelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BolsaThread extends Thread{

  private final PapelRepository papelRepository;
  private final PapeisMapper papeisMapper;

    public BolsaThread(PapelRepository papelRepository, PapeisMapper papeisMapper) {
        this.papelRepository = papelRepository;
        this.papeisMapper = papeisMapper;
    }

    public void run(){

            while (true) {


                var papeis = papelRepository.findAll();
                List<Papeis> papeisModel = papeisMapper.entityToModel(papeis);
                papeisModel.forEach(atual -> {
                    Random rand = new Random();
                    Integer n = rand.nextInt(-5,6);
                    var percent = 0.001 * n;
                    atual.updateValor((atual.getValor() * percent) + atual.getValor());
                });

                List<PapeisEntity> papeisToSave = papeisMapper.listModelToEntity(papeisModel);
                papelRepository.saveAll(papeisToSave);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

    }
}
