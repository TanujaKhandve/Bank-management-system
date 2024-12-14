package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Ministatement extends JFrame{

    
    String pinnumber;
    
    
    Ministatement(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);
        
       // SetTitle("MINI STATEMENT");
        
  JLabel mini=new JLabel();
  //mini.setBounds(20,140,400,200);
  add(mini);
       
  JLabel bank=new JLabel("Indian Bank");
  bank.setBounds(150,20,100,20);
  add(bank);   
  
  
  JLabel card=new JLabel();
  card.setBounds(20,80,300,20);
  add(card);   
    
  
  JLabel balance=new JLabel();
  balance.setBounds(20,400,300,20);
  add(balance);
  
  try{
      Conn conn=new Conn();
    //  int bal=0;
      ResultSet rs=conn.s.executeQuery("select * from login where pin='"+pinnumber+"'");
      while(rs.next()){
          card.setText("Card Number: "+rs.getString("cardnumber").substring(0, 4)+"XXXXXXXX"+rs.getString("cardnumber").substring(12));
      }
    }catch(Exception e){
      System.out.println(e);
      
  }
  //To fetch the data 
  try{
      Conn conn=new Conn();
      int bal=0;
      
      ResultSet rs=conn.s.executeQuery("select * from bank where pin= '1844'");
      while(rs.next()){
          mini.setText(mini.getText()+"<html>"+rs.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("amount") +"<br><br><html>");                //override
          if(rs.getString("type").equalsIgnoreCase("deposit")){
             bal+=Integer.parseInt(rs.getString("amount"));      //To convert int to String
          }else{
              bal-=Integer.parseInt(rs.getString("amount"));      

               }
        }
      
      balance.setText("Your Current Account Balance is RS " +bal);
  }catch(Exception e){
      
           System.out.println(e);
 
  }
      
     mini.setBounds(20,140,400,200);
  
  
  setSize(400,600);
  setLocation(20,20);
  getContentPane().setBackground(Color.white);
  setVisible(true);
        
        
        
        
    }
    
    public static void main(String args[]) {

        new Ministatement(""); 

    }
}
