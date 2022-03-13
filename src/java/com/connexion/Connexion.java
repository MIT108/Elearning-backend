
package com.connexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    private static Connection conex;

    public static Object seConnecter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String url="jdbc:mysql://localhost:3306/elearningapp";
    private String user = "root";
    private String password="";
    
    
    private Connexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception e){
            System.out.println("Error of driver: "+e.getMessage());
        }
        
        try{
            conex = DriverManager.getConnection(url,user,password);
        }catch(Exception e){
             System.out.println("Error Connection: "+e.getMessage());
        }
    }
    
    public static Connection seconnecter(){
        if(conex==null){
            Connexion connexion = new Connexion();
        }
        return conex;
    }
    
}


