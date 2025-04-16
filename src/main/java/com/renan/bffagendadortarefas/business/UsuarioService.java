package com.renan.bffagendadortarefas.business;

import com.renan.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.renan.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.renan.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.renan.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.renan.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.renan.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.renan.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.renan.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
        return client.buscarUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest dto){
        return client.atualizaDadoUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token){
        return client.atualizaTelefone(dto, idTelefone, token);
    }

    public EnderecoDTOResponse cadastroEndereco(String token, EnderecoDTORequest dto){
        return client.cadastroEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest dto){
        return client.cadastroTelefone(dto, token);
    }

}
