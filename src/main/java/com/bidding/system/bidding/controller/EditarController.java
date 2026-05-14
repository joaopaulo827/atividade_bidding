/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.controller;


import com.bidding.system.bidding.model.EditarDTO;
import com.bidding.system.bidding.model.LancesDTO;
import com.bidding.system.bidding.service.EditarService;
import com.bidding.system.bidding.service.LancesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/api/editais")
public class EditarController {
    @Autowired
    private EditarService service;
    @Autowired
    private LancesService LanceService;
    
    @PostMapping
    public String cadastrarEditar(
        @RequestHeader("Authorization") String auth,
        @RequestBody EditarDTO  editar      
    ){
        String token = auth.replace("Bearer ", "");
        service.criarEdital(editar, token);
        
        return "Edital Cadatrado com sucesso";
}
    @GetMapping
    public List <EditarDTO> ListarTodos(){
        return service.listarTodos();
    }
    @PostMapping("/criar/lances")
    public String criar(@RequestBody LancesDTO user){
        LanceService.criarLances(user);
        return "Novos dados adicionado com sucesso";
    }
}
