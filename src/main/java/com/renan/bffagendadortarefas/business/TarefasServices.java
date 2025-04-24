package com.renan.bffagendadortarefas.business;

import com.renan.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.renan.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.renan.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.renan.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasServices {
    private final TarefasClient client;

    public TarefasDTOResponse gravarTarefas(String token, TarefasDTORequest dto) {
        return client.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                    LocalDateTime dataFinal,
                                                                    String token) {
        return client.buscaListaDeTarefasDTO(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return client.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        client.deletaTaferaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return client.alteraStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
       return client.updateTarefas(dto, id, token);
    }

}

