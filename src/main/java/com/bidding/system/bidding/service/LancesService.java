/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.EditarDTO;
import com.bidding.system.bidding.model.LancesDTO;
import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.repository.EditarDAO;
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
    private EditarDAO repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LancesDAO lanceReposiotry;
    
    public void criarLance(Long id, LancesDTO lance, String token){
        if(tokenService.validarToken(token)){
          UserDTO user= tokenService.extrairClaims(token);
          if(!user.getRole().equals("FORNCEDOR")){
             throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Você precisa ser fornecedor para cadastrar um lance");             
          }
          EditarDTO editar = repository.getbyid(id);
          if(!editar.getStatus().equals("ABERTO")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Você não pode criar lances para editar fechado!");
          }
          if(editar.getData_fechamento().before(lance.getData_lance())){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Data do lance ponsterior ao fechamento!");  
          }
          int linhas = lanceReposiotry.criarLance(lance);
          if(linhas== 0){
              throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Erro no bancos de dados!");
          }
        }else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token invalido");
        }
        
    }
}
