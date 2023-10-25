
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
        

public class Checkout extends JFrame implements ActionListener{
  
    Choice ccustomer;
    JLabel lblroomnumber, lblcheckintime, lblcheckouttime;
    JButton checkout, back;
    Checkout(){
      
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        //checkout heading
        
        JLabel text = new JLabel("Checkout");
        text.setBounds(100,20,100,30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);
        
        
           //label id...
        
        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30,80,100,30);
        add(lblid);
        
         //choice customer..label
        
        ccustomer = new Choice();
        ccustomer.setBounds(150,80,150,25);
        add(ccustomer);
        
     
        
         //imageicon tick mark right side
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel tick = new JLabel(i3);
        tick.setBounds(310,80,20,20);
        add(tick);
        
          //label room number...
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30,130,100,30);
        add(lblroom);
        
        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(150,130,100,30);
        add(lblroomnumber);
        
        
        
          //label checkin time...
        
        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30,180,100,30);
        add(lblcheckin);
        
        lblcheckintime = new JLabel();
        lblcheckintime.setBounds(150,180,100,30);
        add(lblcheckintime);
        
        
         //label checkout time...
        
        JLabel lblcheckout = new JLabel("Checkin Time");
        lblcheckout.setBounds(30,230,100,30);
        add(lblcheckout);
        
        
      //  Date date = new Date();
       // lblcheckouttime = new JLabel("" + date);
       // lblcheckouttime.setBounds(200,230,100,30);
       // add(lblcheckouttime);
        java.util.Date date = new java.util.Date(); // Specify the full package path for java.util.Date
        lblcheckouttime = new JLabel("" + date);
        lblcheckouttime.setBounds(150, 230, 150, 30);
        add(lblcheckouttime);
        
        
        //checkout button...
        checkout = new JButton("Checkout");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBounds(30,280,120,30);
        checkout.addActionListener(this);
        add(checkout);
        
         //back button...
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(170,280,120,30);
        back.addActionListener(this);
        add(back);
        
        
          try{
            
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                ccustomer.add(rs.getString("number"));
                lblroomnumber.setText(rs.getString("room"));
                lblcheckintime.setText(rs.getString("checkintime"));
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
          
         //imageicon right side
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400,250,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(350,50,400,250);
        add(image); 
        
        setBounds(300,200,800,400);
        setVisible(true);
    }
        public void actionPerformed(ActionEvent ae){
            if(ae.getSource() == checkout){
                String query1 = "delete from customer where number = '"+ccustomer.getSelectedItem()+"'";
                String query2 = "update room set availability = 'Available' where roomnumber = '"+lblroomnumber.getText()+"'";
           try{
             Conn c = new Conn();
             c.s.executeUpdate(query1);
             c.s.executeUpdate(query2);
             
             JOptionPane.showMessageDialog(null, "Checkout Done");
             setVisible(false);
                new Reception();
             
           }catch (Exception e){
               e.printStackTrace();
           }
            
            }else {
                setVisible(false);
                new Reception();
            }
        }
    
    public static void main(String [] args){
        new Checkout();
    }
}
