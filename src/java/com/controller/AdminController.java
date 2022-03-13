
package com.controller;

import com.model.AdminModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.connexion.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.json.JSONObject;

@Path("admin")
public class AdminController {
    //function to get list of all admins
    @GET
    @Path("/listadmin")
    @Produces("application/json")
    
    public ArrayList<AdminModel> listadmin() throws Exception{
        ArrayList<AdminModel> admins = new ArrayList<>();
        try{
        
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery("select * from admin");
                
            while(rs.next()){
                System.out.println("Entered Controller");
                AdminModel oneadmin = new AdminModel();
                
                oneadmin.setAdminid(rs.getInt("adminid"));
                oneadmin.setName(rs.getString("name"));
                oneadmin.setEmail(rs.getString("email"));
                oneadmin.setPassword(rs.getString("password"));
                
                admins.add(oneadmin);
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return admins;
    }
    
     //function to get list one admin
    @GET
    @Path("/listadmin/{id}")
    @Produces("application/json")
    
    public ArrayList<AdminModel> listoneAdmin(@PathParam("id") int id) throws Exception{
        ArrayList<AdminModel> admins = new ArrayList<>();
        try{
        
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery("select * from admin where adminid="+id);
                
            while(rs.next()){
                System.out.println("Entered Controller");
                AdminModel oneadmin = new AdminModel();
                
                oneadmin.setAdminid(rs.getInt("adminid"));
                oneadmin.setName(rs.getString("name"));
                oneadmin.setEmail(rs.getString("email"));
                oneadmin.setPassword(rs.getString("password"));
                
                admins.add(oneadmin);
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return admins;
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    
    public String deleteuser(@PathParam("id") int id){
        String result="failed";
        try{
            PreparedStatement prep = Connexion.seconnecter().prepareStatement("delete from admin where adminid="+id);
            if(prep.executeUpdate()==1){
                result = "success";
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return result;
    }
    
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response login(AdminModel user) throws Exception {
        AdminModel model = new AdminModel();
        Boolean one = false;
        JsonObject resBody;
        try {
            String sqlStatment = "Select * from admin where password = ? and (name = ? or email = ?)";
            PreparedStatement st = Connexion.seconnecter().prepareStatement(sqlStatment);
            st.setString(1, user.getPassword());            
            st.setString(2, user.getName());
            st.setString(3, user.getEmail());
            
            
            ResultSet rs = st.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                resBody = (JsonObject) Json.createObjectBuilder()
                        .add("admin", rs.getString("adminid"))
                        .add("name", rs.getString("name"))
                        .add("email", rs.getString("email"))
                        .build();
                return Response.status(Response.Status.OK).entity(resBody.toString()).build();
            }else{
                return Response.status(Response.Status.UNAUTHORIZED).entity("incorrect inforamtions").build();
            }

        } catch (Exception e) {
            System.out.println("Error in querry " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error in querry " + e.getMessage()).build();
        }
    }

    
    
    
    @POST
    @Path("/addAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    public AdminModel registerUser(AdminModel user) { 
        try {
            String sqlStatment = "Insert into admin (name,email,password) " + "values (?,?,?)";
            PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error in querry" + e.getMessage());
        }
        return user;
    }
    
}
