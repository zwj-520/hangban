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
			   System.out.println("连接数据库成功");
			   st = con.createStatement();
		   }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  System.out.print("连接失败");
			  JFrame frame = new JFrame("提示");
			  JPanel panel = new JPanel();
			  JLabel label = new JLabel("数据库连接失败，请检查3306端口");
			  label.setFont(new Font("楷体",Font.BOLD,13));
			  panel.add(label);
			  frame.add(panel);
			  
			  frame.setVisible(true);
			  frame.setSize(500, 500);
			  frame.setLocationRelativeTo(null);
		  }
	}

}
