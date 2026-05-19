/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.EditarDTO;
import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.repository.EditarDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class EditarService {
    @Autowired
    private EditarDAO repository;
    @Autowired
    private TokenService tokenService;
    
    public void criarEdital(EditarDTO novo, String token){
        UserDTO usuarioLogado= tokenService.extrairClaims(token);
        if(usuarioLogado.getRole().equals("COMPRADOR")){
        String mensagem = "";
        if(novo.getTitulo().equals("")){
            mensagem+= "Titulo não preenchido!\n";
        }
        if(novo.getDescricao().equals("")){
            mensagem+="Descrição não preenchido!\n";
        }
        if(novo.getData_fechamento()==null){
            mensagem+= "Data não preenchida!\n";
        }
        if(!mensagem.equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }
        novo.setStatus("ABERTO");
        int linhas=repository.criar(novo);
            if(linhas== 0){
             throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Erro ao cadastrar");   
            }
        else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Acesso não autorizado");
        }
    }
    }
    public List<EditarDTO> listarTodos(String token){    
    if(tokenService.validarToken(token)){
        return repository.listarTodos();
    }else{
        throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Necessario entrar com conta válida");
    }
}
}
