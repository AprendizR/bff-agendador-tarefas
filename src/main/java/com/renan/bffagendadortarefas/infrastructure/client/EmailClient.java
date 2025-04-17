package com.renan.bffagendadortarefas.infrastructure.client;

import com.renan.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.renan.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.renan.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "notificacao", url = "${notificacao.url}")

public interface EmailClient {
    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}