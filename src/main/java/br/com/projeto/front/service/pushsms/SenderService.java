package br.com.projeto.front.sevice.pushsms;

import br.com.projeto.front.models.Sender;
import br.com.projeto.front.models.to.SenderTO;

import java.util.List;

public interface SenderService {

    Sender novoAgendamento(SenderTO to);

    List<Sender> findAll();

    List<Sender> findByAgendado();

    List<Sender> findByTarget(Integer target);

    List<Sender> findbyType(String type);

    Sender findById(Integer idSender);

}
