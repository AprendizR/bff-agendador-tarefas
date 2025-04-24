package com.renan.bffagendadortarefas.business;

import com.renan.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.renan.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.renan.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.renan.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefasServices tarefasServices;
    private final EmailServices emailServices;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = "Bearer " + login(converterParaRequestDTO());
        log.info("Token com o Bearer: {}", token);

        LocalDateTime horaFutura = LocalDateTime.now().plusMinutes(30);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(3).plusMinutes(5);

        List<TarefasDTOResponse> listaTarefas = tarefasServices.buscaTarefasAgendadasPorPeriodo
                (horaFutura, horaFuturaMaisCinco, token);
        log.info("Quantidade de tarefas encontradas: {}", listaTarefas.size());

        listaTarefas.forEach(tarefa -> {emailServices.enviaEmail(tarefa);
            log.info("Enviado email para o usuario: {} ", tarefa.getEmailUsuario());
            tarefasServices.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });
        log.info("Finalizado a busca e notificação");
    }

    public String login(LoginDTORequest dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
