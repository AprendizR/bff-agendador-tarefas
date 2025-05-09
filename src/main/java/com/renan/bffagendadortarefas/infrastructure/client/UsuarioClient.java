package com.renan.bffagendadortarefas.infrastructure.client;

import com.renan.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.renan.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.renan.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.renan.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.renan.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.renan.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.renan.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.renan.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping ("/usuario")
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest usuarioDTO);


    @DeleteMapping("/{email}")
    Void deletaUsuarioPorEmail(@PathVariable String email,
                              @RequestHeader ("authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader ("Authorization") String token);


    @PutMapping ("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam ("id") Long id,
                                         @RequestHeader ("authorization") String token);


    @PutMapping ("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam ("id") Long id,
                                         @RequestHeader ("authorization") String token);


    @PostMapping ("/endereco")
    EnderecoDTOResponse cadastroEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader ("authorization") String token);


    @PostMapping ("/telefone")
    TelefoneDTOResponse cadastroTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader ("authorization") String token);

    @GetMapping ("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable("cep") String cep);

}
