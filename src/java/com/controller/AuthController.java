/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.connexion.Connexion;
import com.model.UserModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.jsp.jstl.sql.Result;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author miend
 */
@Path("auth")
public class AuthController {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public UserModel login(UserModel user) throws Exception {
        UserModel model = new UserModel();
        Boolean one = false;
        try {
            String sqlStatment = "Select * from users";
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery(sqlStatment);
            while (rs.next()) {
                if ((rs.getString("email").equals(user.getName())) && (rs.getString("password").equals(user.getPassword())) || ((rs.getString("name").equals(user.getName())) && (rs.getString("password").equals(user.getPassword())))) {
                    model.setUserid(rs.getInt("userid"));
                    model.setAdminid(rs.getInt("adminid"));
                    model.setName(rs.getString("name"));
                    model.setEmail(rs.getString("email"));
                    model.setPassword(rs.getString("password"));
                    model.setRole(rs.getInt("role"));
                    model.setFirebase(rs.getInt("firebase"));
                    model.setVerify(rs.getInt("verify"));
                    model.setClassid(rs.getInt("classid"));
                }
            }

        } catch (Exception e) {
            System.out.println("Error in querry " + e.getMessage());
        }
        return model;
    }

    @GET
    @Path("/firebaseUpdate/{userid}/{value}")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces("application/json")
    public String update(@PathParam("userid") int userid, @PathParam("value") int value, UserModel user) throws Exception {
        String result = "false";

        if (value == 0) {
            value = 1;
        } else {
            value = 0;
        }

        try {
            String sql = "update users set firebase = ? where userid = " + Integer.toString(userid);
            PreparedStatement st = Connexion.seconnecter().prepareStatement(sql);
            st.setString(1, Integer.toString(value));
            st.executeUpdate();
            result = "true";
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());

        }
        return result;
    }

    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public String verify(UserModel user) throws Exception {
        String result = "false";
        String newPassord = user.getPassword();
        int userid = user.getUserid();
        try {
            String sql = "update users set password = ? , verify = ? where userid = " + Integer.toString(userid);
            PreparedStatement st = Connexion.seconnecter().prepareStatement(sql);
            st.setString(1, newPassord);
            st.setInt(2, 1);
            st.executeUpdate();
            result = "true";

        } catch (Exception e) {
            System.out.println("Error in querry " + e.getMessage());
        }

        return result;
    }
}
