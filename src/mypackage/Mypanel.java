package mypackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import java.util.*;

public class Mypanel extends JPanel
{
	ImageIcon img;
	public Mypanel(ImageIcon img)
	{
		this.img = img;
	}
	public void paintComponent(Graphics g)              // 自动调用
	{
		super.paintComponent(g);
		g.drawImage(img.getImage(),0,0,1600,900,null);
	}
}
