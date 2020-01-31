package br.com.projeto.front.sevice.pushsms;

import br.com.projeto.front.models.Sender;
import br.com.projeto.front.models.to.SenderTO;
import br.com.projeto.front.repository.SenderRepository;
import br.com.projeto.front.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SenderServiceImp implements SenderService {

    @Autowired
    private final SenderRepository senderDao;

    public SenderServiceImp(SenderRepository senderDao) {
        this.senderDao = senderDao;
    }

    @Override
    public Sender novoAgendamento(SenderTO to) {
        Utils.validate(to);
        Sender sender = new Sender(to.getTipo(), to.isAgendar(), to.getAlvos(), to.getTitulo(), to.getMensagens(),
                to.getDataEnvio(), to.getHoraEnvio());

        return this.senderDao.save(sender);
    }

    @Override
    public List<Sender> findAll() {
        return this.senderDao.findAll();
    }

    @Override
    public List<Sender> findByAgendado() {
        return this.senderDao.findByAgendarFalseOrAgendarTrue();
    }

    @Override
    public Sender findById(Integer idSender) {
        if (Utils.validarParametros(idSender)) {
            Sender sender = senderDao.findById(idSender).orElse(null);
            if (sender != null) {
                return sender;
            } else {
                throw new NullPointerException("Mensagem não encontrada.");
            }
        } else {
            throw new NullPointerException("Mensagem não encontrada.");
        }
    }

    @Override
    public List<Sender> findByTarget(Integer target) {
        if (Utils.validarParametros(target)) {
            return this.senderDao.findByAlvos(target);
        } else {
            throw new NullPointerException("Alvo não encontrado.");
        }
    }

    @Override
    public List<Sender> findbyType(String type) {
        if (Utils.validarParametros(type)) {
            return this.senderDao.findByTipo(type);
        } else {
            throw new NullPointerException("Tipo não encontrado.");
        }
    }

    public List<Sender> findByDate(Date date) {
        if (Utils.validarParametros(date)) {
            return this.senderDao.findByDataEnvio(date);
        } else {
            throw new NullPointerException("Falha ao pesquisar.");
        }
    }
}
