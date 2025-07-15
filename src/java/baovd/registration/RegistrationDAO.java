/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baovd.registration;

import baovd.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class RegistrationDAO implements Serializable{
//    public boolean checkLogin(String username, String password) 
//        throws SQLException , ClassNotFoundException {
    public RegistrationDTO checkLogin(String username, String password) 
        throws SQLException , ClassNotFoundException {
//        boolean result = false; 
        RegistrationDTO result = null; 
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
        //1.connection DB
        con = DBHelper.makeConnection();
        if( con != null){
        
        //2.query DB
        //2.1 Write SQL command String
        String sql = "Select lastname, isAdmin " //them 1 khoang trang roi nhan Enter de xuong dong
                + "From Registration "
                + "Where username = ? "
                + "And password = ?";
        
        //2.2 Create Statement Object ( boi den chay tung cau lenh de luu vao bo nho)
        stm = con.prepareStatement(sql); // nhin so dau ? de xet bao nhieu tham so de SET (? tinh tu trai sang phai tinh tu vi tri so 1)
        stm.setString(1, username); 
        stm.setString(2, password);
        //2.3 Execute Query
        rs = stm.executeQuery();
        //3. get data from Result Set 
        //then model set data to properties of Model
        if(rs.next()){
            String fullname = rs.getString("lastname");
            boolean role = rs.getBoolean("isAdmin");
            
            result = new RegistrationDTO(username, null, fullname, role);
        }//user is existed
        } // end connection is an available
        
    } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
        }
            if(con!=null){
                con.close();
            }
        
    
        
        return result;
    }    

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue)
        throws SQLException , ClassNotFoundException { 
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
        //1.connection DB
        con = DBHelper.makeConnection();
        if( con != null){
        
        //2.query DB
        //2.1 Write SQL command String
        String sql ="Select username, password, lastname, isAdmin "
                + "From Registration "
                + "Where lastname LIKE ?";  //them 1 khoang trang roi nhan Enter de xuong dong
        // lasy tat ca cac Registraion con voi dieu kien string nam trong cot thang hang
                
        
        //2.2 Create Statement Object ( boi den chay tung cau lenh de luu vao bo nho)
        stm = con.prepareStatement(sql); // nhin so dau ? de xet bao nhieu tham so de SET (? tinh tu trai sang phai tinh tu vi tri so 1)
        stm.setString(1,"%" + searchValue + "%");
        //2.3 Execute Query
        rs = stm.executeQuery();
        //3. get data from ... then model set data to properties of Model
        while (rs.next()){
            String username = rs.getString("username");
            String password = rs.getString("password");
            String lastname = rs.getString("lastname");
            boolean isAdmin = rs.getBoolean("isAdmin");
            
            RegistrationDTO dto = new RegistrationDTO(username,password,lastname,isAdmin);
            if (this.accounts == null){
                this.accounts = new ArrayList<>();
            }//account is not available
            this.accounts.add(dto);
            
                    
        }//traversal each row in ResultSet
        
        }// end connection is an available
        
    } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
        
            if(con!=null){
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
    throws SQLException , ClassNotFoundException { 
        Connection con = null;
        PreparedStatement stm = null;
//        ResultSet rs = null;
        boolean result = false;
        
        try {
        //1.connection DB
        con = DBHelper.makeConnection();
        if( con != null){
        
        //2.query DB
        //2.1 Write SQL command String
        String sql ="Delete From Registration "
                + "Where username = ?";  //them 1 khoang trang roi nhan Enter de xuong dong
        // lasy tat ca cac Registraion con voi dieu kien string nam trong cot thang hang
                
        
        //2.2 Create Statement Object ( boi den chay tung cau lenh de luu vao bo nho)
        stm = con.prepareStatement(sql); // nhin so dau ? de xet bao nhieu tham so de SET (? tinh tu trai sang phai tinh tu vi tri so 1)
        stm.setString(1, username);
        //2.3 Execute Query
        int effectRow= stm.executeUpdate();
        //3. vheck effectRow
        if (effectRow > 0){
            result = true;
        }
        
        }// end connection is an available
        
    } finally {
//            if(rs != null){
//                rs.close();
//            }
            if(stm != null){
                stm.close();
            }
        
            if(con!=null){
                con.close();
            }
        }
    return result;
    }
    
    public boolean createAccount(RegistrationDTO account)
         throws SQLException , ClassNotFoundException {
 
        boolean result = false; 
//         1. Model connect Database.
//          Khai bao bien va gan null
//          Phai dong tat ca cac doi tuong bang moi cach
//          Xu ly
         
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
        //1.connection DB
        con = DBHelper.makeConnection();
        if( con != null){
        
        //2.query DB
        //2.1 Write SQL command String
//        String sql ="Insert into Registration("
//                + "username, password, lastname, isAdmin"
//                + ") value(" 
//                + "?, ?, ?, ?" 
//                + ")";

        String sql = "INSERT INTO Registration("
           + "username, password, lastname, isAdmin"
           + ") VALUES(?, ?, ?, ?)";
        
        
        //2.2 Create Statement Object ( boi den chay tung cau lenh de luu vao bo nho)
        stm = con.prepareStatement(sql); // nhin so dau ? de xet bao nhieu tham so de SET (? tinh tu trai sang phai tinh tu vi tri so 1)
        stm.setString(1, account.getUsername()); 
        stm.setString(2, account.getPassword()); 
        stm.setString(3, account.getFullName()); 
        stm.setBoolean(4, account.isRole()); 
        
        //2.3 Execute Query
        int effectRows = stm.executeUpdate();
        //3. get data from Result Set 
        //then model set data to properties of Model
        if(effectRows > 0){
            result = true;
        }//user is existed
        } // end connection is an available
        
    } finally {
 
            if(stm != null){
                stm.close();
            }
        
            if(con!=null){
                con.close();
            }
        
        }
        
        return result;

    
    
}
    
    public boolean updateAccount(String username,String password, boolean isAdmin)
        throws SQLException , ClassNotFoundException {
 
        boolean result = false;  
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
        //1.connection DB
        con = DBHelper.makeConnection();
        if( con != null){
        
        //2.query DB
        //2.1 Write SQL command String
        
        String sql = "UPDATE Registration "
                + "SET password = ?, isAdmin = ? "
                + "WHERE username = ?";
        
        
        //2.2 Create Statement Object ( boi den chay tung cau lenh de luu vao bo nho)
        stm = con.prepareStatement(sql); // nhin so dau ? de xet bao nhieu tham so de SET (? tinh tu trai sang phai tinh tu vi tri so 1)
        stm.setString(1, password);
        stm.setBoolean(2, isAdmin);
        stm.setString(3, username);
        
        //2.3 Execute Query
        int effectRows = stm.executeUpdate();
        //3. get data from Result Set 
        //then model set data to properties of Model
        if(effectRows > 0){
            result = true;
        }//user is existed
        } // end connection is an available
        
    } finally {
 
            if(stm != null){
                stm.close();
            }
        
            if(con!=null){
                con.close();
            }
        
        }
        
        return result;

    
    
    }
}