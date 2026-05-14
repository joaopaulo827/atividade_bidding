/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.repository;

import com.bidding.system.bidding.model.LancesDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class LancesDAO {
    public int criar(LancesDTO novo){
        try{
            Connection conn= Conexao.conectar();
            PreparedStatement stmt= null;
            stmt= conn.prepareStatement("INSERT INTO lances (valor,data_lance,id_editar,id_usuario) VALUES (?,?,?,?)");
            stmt.setDouble(1, novo.getValor());
            stmt.setDate(2, (Date) novo.getData_lance());
            stmt.setLong(3, novo.getId_edital());
            stmt.setLong(4, novo.getId_usuario());
            return stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }    
}
