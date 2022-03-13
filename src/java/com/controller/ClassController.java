/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.connexion.Connexion;
import com.model.AdminModel;
import com.model.ClassModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author miend
 */

@Path("class")
public class ClassController {
    
    
    @POST
    @Path("/addClass")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    public ClassModel addClass(ClassModel classs) { 
        try {
            String sqlStatment = "Insert into classes (adminid,name) " + "values (?,?)";
            PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment);
            pstmt.setInt(1, classs.getAdminid());
            pstmt.setString(2, classs.getName());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error in querry" + e.getMessage());
        }
        return classs;
    }
    
    
     //function to get list of all admins
    @GET
    @Path("/listClasses")
    @Produces("application/json")
    
    public ArrayList<ClassModel> listClass() throws Exception{
        ArrayList<ClassModel> Classes = new ArrayList<>();
        try{
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery("select * from classes");
                
            while(rs.next()){
                ClassModel oneClass = new ClassModel();
                
                oneClass.setClassid(rs.getInt("classid"));
                oneClass.setAdminid(rs.getInt("adminid"));
                oneClass.setName(rs.getString("name"));
                
                Classes.add(oneClass);
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return Classes;
    }
    
     //function to get list one admin
    @GET
    @Path("/listClass/{id}")
    @Produces("application/json")
    
    public ArrayList<ClassModel> listOneClass(@PathParam("id") int id) throws Exception{
        ArrayList<ClassModel> Classes = new ArrayList<>();
        try{
        
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery("select * from classes where classid="+id);
                
            while(rs.next()){
                ClassModel oneClass = new ClassModel();
                
                oneClass.setClassid(rs.getInt("classid"));
                oneClass.setAdminid(rs.getInt("adminid"));
                oneClass.setName(rs.getString("name"));
                
                Classes.add(oneClass);
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return Classes;
    }
    
    
    @DELETE
    @Path("/deleteClass/{id}")
    @Produces("application/json")
    
    public String deleteuser(@PathParam("id") int id){
        String result="false";
        try{
            PreparedStatement prep = Connexion.seconnecter().prepareStatement("delete from classes where classid="+id);
            if(prep.executeUpdate()==1){
                result = "true";
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return result;
    }
}
