package br.com.projeto.front.frm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScope
public class AgendadorFrm implements Serializable {

    @Getter @Setter
    private boolean agendador;

    @PostConstruct
    public void init() {
        this.agendador = true;
    }

}
