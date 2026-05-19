/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.repository;

import com.bidding.system.bidding.model.EditarDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class EditarDAO {
    public int criar(EditarDTO novo){
        try{
            Connection conn= Conexao.conectar();
            PreparedStatement stmt= null;
            stmt= conn.prepareStatement("INSERT INTO editais (titulo,descricao,data_fechamento,status) VALUES (?,?,?,?)");
            stmt.setString(1, novo.getTitulo());
            stmt.setString(2, novo.getDescricao());
            stmt.setDate(3, (Date) novo.getData_fechamento());
            stmt.setString(4, novo.getStatus());
            
            return stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
public List<EditarDTO> listarTodos(){
    List<EditarDTO> dados = new ArrayList();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("SELECT * FROM editais");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                EditarDTO editar = new EditarDTO();
                editar.setId(rs.getLong("id"));
                editar.setTitulo(rs.getString("titulo"));
                editar.setDescricao(rs.getString("descricao"));
                editar.setData_fechamento(rs.getDate("data_fechamento"));
                editar.setStatus(rs.getString("status"));
                dados.add(editar);
            }
        } catch(SQLException e ) {
            e.printStackTrace();
        }
        return dados;
    }
    public EditarDTO getbyid(Long id){
        EditarDTO editar= new EditarDTO();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("SELECT data_fechamento, status FROM editais where id=?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                editar.setData_fechamento(rs.getDate("data_fechamento"));
                editar.setStatus(rs.getString("status"));
            }
        } catch(SQLException e ) {
            e.printStackTrace();
        }
        return editar;        
    }
}