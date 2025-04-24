package com.renan.bffagendadortarefas.infrastructure.client.config;

import com.renan.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.renan.bffagendadortarefas.infrastructure.exceptions.ConflictExceptions;
import com.renan.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import com.renan.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.renan.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String mensagemErro = mensagemErro(response);

        switch (response.status()) {
            case 409:
                return new ConflictExceptions("Erro: " + mensagemErro);
            case 403:
                return new ResourceNotFoundException("Erro: " + mensagemErro);
            case 401:
                return new UnauthorizedException("Erro: " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Erro: " + mensagemErro);
            default:
                return new BusinessException("Erro: " + mensagemErro);
        }


    }

    private String mensagemErro(Response response) {
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
