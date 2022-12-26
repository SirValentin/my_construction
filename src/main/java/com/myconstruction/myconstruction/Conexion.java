package com.myconstruction.myconstruction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Conexion {
    String bd = "my_construction";
    String ip = "localhost";
    String puerto = "3307";
    String driver = "com.mysql.jdbc.Driver";
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    Connection cn = null;
    Statement st=null;
    PreparedStatement ps;
    ResultSet rs;
    
    public Conexion() {
        try {
            cn=DriverManager.getConnection(cadena, "root", "");
            st=cn.createStatement();
            Class.forName(driver);
        }catch (Exception e) {
            System.out.println("Error al conectar.");
        }
    }
    
    public ArrayList<Usuario> login(String correo, String clave){
        ArrayList<Usuario> res=new ArrayList();
        try {
            ps=cn.prepareStatement(" select * from usuario where correo=? and clave=?");
            ps.setString(1, correo);
            ps.setString(2, clave);
            rs=ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setCorreo(rs.getString("correo"));
                user.setClave(rs.getString("clave"));
                res.add(user);
            }
            if (res.isEmpty()) {
                System.out.println("Acceso denegado.");
            } else {
                System.out.println("Login Exitoso.");
            }
        } catch (Exception e) {
        }
        return res;
    }
    
}
