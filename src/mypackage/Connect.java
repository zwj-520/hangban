package mypackage;

import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.sql.*;

import java.util.*;

import javax.swing.*;

public class Connect {
	public Connection con;
	public Statement st;
	public Connect()
	{
		  String driverName="com.mysql.cj.jdbc.Driver";
		  String dbURL="jdbc:mysql://localhost:3306/airplane?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
		  String userName="root";
		  String userPwd="123abc";
		  try
		  {
			   Class.forName(driverName);
			   con = DriverManager.getConnection(dbURL,userName,userPwd);
			   System.out.println("�������ݿ�ɹ�");
			   st = con.createStatement();
		   }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  System.out.print("����ʧ��");
			  JFrame frame = new JFrame("��ʾ");
			  JPanel panel = new JPanel();
			  JLabel label = new JLabel("���ݿ�����ʧ�ܣ�����3306�˿�");
			  label.setFont(new Font("����",Font.BOLD,13));
			  panel.add(label);
			  frame.add(panel);
			  
			  frame.setVisible(true);
			  frame.setSize(500, 500);
			  frame.setLocationRelativeTo(null);
		  }
	}

}
