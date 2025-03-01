package pt.ipcb.ad.aid_pl2.service;

import org.springframework.data.repository.CrudRepository;
import pt.ipcb.ad.aid_pl2.model.Utilizador;

public interface UtilizadorRepository extends
        CrudRepository<Utilizador, Long>{
}
