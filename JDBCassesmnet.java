package com.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class str implements ActionListener {
    JFrame frame;
    JLabel l1, l2, l3, l4, l5, msg;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2, b3, b4;

    public str() {
        frame = new JFrame("Grid Demo");
        frame.setLayout(new GridLayout(8, 2));
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("ID:");
        l2 = new JLabel("First Name:");
        l3 = new JLabel("Last Name:");
        l4 = new JLabel("Email:");
        l5 = new JLabel("Mobile:");
        msg = new JLabel();

        t1 = new JTextField(15);
        t2 = new JTextField(15);
        t3 = new JTextField(15);
        t4 = new JTextField(15);
        t5 = new JTextField(15);

        b1 = new JButton("Insert");
        b1.addActionListener(this);
        b2 = new JButton("Search By ID");
        b2.addActionListener(this);
        b3 = new JButton("Update");
        b3.addActionListener(this);
        b4 = new JButton("Delete");
        b4.addActionListener(this);

        frame.add(l1);
        frame.add(t1);
        frame.add(l2);
        frame.add(t2);
        frame.add(l3);
        frame.add(t3);
        frame.add(l4);
        frame.add(t4);
        frame.add(l5);
        frame.add(t5);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(msg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "");
                System.out.println("Connection established");

                Statement st = cn.createStatement();

                int x = st.executeUpdate(
                    "INSERT INTO student1 VALUES (" + Integer.parseInt(t1.getText())+ ",'" + t2.getText() + "','" 
                    + t3.getText() + "','" + t4.getText() + "'," + Integer.parseInt(t5.getText()) + ")");

                if (x > 0) {
                    msg.setText("Record inserted");
                } else {
                    msg.setText("Record not inserted");
                }
               
                frame.revalidate();
                frame.repaint();
                cn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "");
                System.out.println("Connection established for Search");

                Statement st = cn.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM student1 WHERE id=" + Integer.parseInt(t1.getText()));
                if (rs.next()) {
                    String student = "Student: " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) 
                        + " " + rs.getString(4) + " " + rs.getInt(5);
                    msg.setText("Record Found: " + student);
                } else {
                    msg.setText("No Record Found");
                }
                
                frame.revalidate();
                frame.repaint();
                cn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource()==b3)
		{
			try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
			System.out.println("Connection established");
			
			Statement st=cn.createStatement();

		
			int x=st.executeUpdate("update student1 set id="+Integer.parseInt(t1.getText())+",firstname='"+t2.getText()+"',lastname='"+t3.getText()+"',mobile="+Integer.parseInt(t5.getText())+" where email='"+t4.getText()+"'");
			
			
			if(x>0)
			{
				msg.setText("record update");
			}
			else
			{
				msg.setText("record not update");
			}
			cn.close();
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
        else if(e.getSource()==b4)
		{
			try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
			System.out.println("Connection established");
			
			Statement st=cn.createStatement();

		
			int x=st.executeUpdate("delete from student1 where email='"+t4.getText()+"'");
		
			
			if(x>0)
			{
				msg.setText("record delete");
			}
			else
			{
				msg.setText("record not delete");
			}
			cn.close();
			
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
        
    }
}

public class JDBCassesmnet {
    public static void main(String[] args) {
        new str();
    }
}