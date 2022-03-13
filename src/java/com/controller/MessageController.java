/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.MessageModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.connexion.*;
import com.model.AdminModel;
import com.sun.mail.imap.protocol.MessageSet;
import java.sql.PreparedStatement;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author miend
 */
@Path("message")
public class MessageController {

    @POST
    @Path("/addMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})    
    public MessageModel registerMessage(MessageModel msg) {
        try {
            String sqlStatment = "Insert into message (userid,courseid,msg) " + "values (?,?,?)";
            PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment);
            pstmt.setInt(1, msg.getUserid());            
            pstmt.setInt(2, msg.getCourseid());
            pstmt.setString(3, msg.getMsg());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error in query:" + e.getMessage());
        }
        return msg;
    }
    
    @GET
    @Path("/listMessage/{sub}")
    @Produces("application/json")
    
    public ArrayList<MessageModel> listMessage(@PathParam("sub") int sub) throws Exception{
        ArrayList<MessageModel> messages = new ArrayList<>();
        try{
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery("select * from message where courseid ="+sub);
            
            while(rs.next()){
                MessageModel oneMessage = new MessageModel();
                
                oneMessage.setCourseid(rs.getInt("courseid"));
                oneMessage.setMessageid(rs.getInt("messageid"));
                oneMessage.setUserid(rs.getInt("userid"));
                oneMessage.setMsg(rs.getString("msg"));
                oneMessage.setSend(rs.getString("send"));
                
                messages.add(oneMessage);
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        return messages;
    }
    

}
