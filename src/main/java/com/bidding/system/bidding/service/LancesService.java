/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.LancesDTO;
import com.bidding.system.bidding.repository.LancesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class LancesService {
    @Autowired
    private LancesDAO repository;
    @Autowired
    private TokenService tokenService;
    
    public void criarLances(LancesDTO novo){
    String mensagem = "";    
    if(novo.getData_lance()==null){
        mensagem+= "Data não preenchida!\n";
    }
    if(novo.getId_edital()==null){
        mensagem+="Edital não preenchido!\n";
    }
    if(novo.getId_usuario()==null){
        mensagem+="Usuario não preenchido!\n";
    }
    if(!mensagem.equals("")){
    throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }
    }
}
