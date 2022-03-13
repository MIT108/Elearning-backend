/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.connexion.Connexion;
import com.model.ClassModel;
import com.model.CourseModel;
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
@Path("course")
public class CourseController {
    
    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    public CourseModel addCourse(CourseModel course) { 
        try {
            String sqlStatment = "Insert into course (courseid,classid,name,description) " + "values (?,?,?,?)";
            PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment);
            pstmt.setInt(1, course.getCourseid());            
            pstmt.setInt(2, course.getClassid());
            pstmt.setString(3, course.getName());
            pstmt.setString(4, course.getDescription());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error in querry" + e.getMessage());
        }
        return course;
    }
    
    
    //function to get list of all admins
    @GET
    @Path("/listCourse")
    @Produces("application/json")
    
    public ArrayList<CourseModel> listCourse() throws Exception{
        ArrayList<CourseModel> Course = new ArrayList<>();
        try{
        
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery("select * from course");
                
            while(rs.next()){
                CourseModel oneCourse = new CourseModel();
                
                oneCourse.setCourseid(rs.getInt("courseid"));
                oneCourse.setClassid(rs.getInt("classid"));
                oneCourse.setName(rs.getString("name"));
                oneCourse.setDescription(rs.getString("description"));
                
                Course.add(oneCourse);
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return Course;
    }
    
     //function to get list one admin
    @GET
    @Path("/listCourse/{id}")
    @Produces("application/json")
    
    public ArrayList<CourseModel> listOneCourse(@PathParam("id") int id) throws Exception{
        ArrayList<CourseModel> Course = new ArrayList<>();
        try{
        
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery("select * from course where courseid="+id);
                
            while(rs.next()){
                CourseModel oneCourse = new CourseModel();
                
                oneCourse.setCourseid(rs.getInt("courseid"));
                oneCourse.setClassid(rs.getInt("classid"));
                oneCourse.setName(rs.getString("name"));
                oneCourse.setDescription(rs.getString("description"));
                
                Course.add(oneCourse);
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return Course;
    }
    
    
     //function to get list one admin
    @GET
    @Path("/listCourseClass/{id}")
    @Produces("application/json")
    
    public ArrayList<CourseModel> listCourseClass(@PathParam("id") int id) throws Exception{
        ArrayList<CourseModel> Course = new ArrayList<>();
        try{
        
            Statement st = Connexion.seconnecter().createStatement();
            ResultSet rs = st.executeQuery("select * from course where classid="+id);
                
            while(rs.next()){
                CourseModel oneCourse = new CourseModel();
                
                oneCourse.setCourseid(rs.getInt("courseid"));
                oneCourse.setClassid(rs.getInt("classid"));
                oneCourse.setName(rs.getString("name"));
                oneCourse.setDescription(rs.getString("description"));
                
                Course.add(oneCourse);
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return Course;
    }
    
    
    @DELETE
    @Path("/deleteCourse/{id}")
    @Produces("application/json")
    
    public String deleteuser(@PathParam("id") int id){
        String result="false";
        try{
            PreparedStatement prep = Connexion.seconnecter().prepareStatement("delete from course where courseid="+id);
            if(prep.executeUpdate()==1){
                result = "true";
            }
        }catch(Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }
        
        return result;
    }
}
