package mypackage;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Menu 
{
	JFrame frame = new JFrame("邢台学院机票预订系统");    //框名
	JLabel title = new JLabel("邢台学院机票预订系统");    //标题名(标签)
	JButton bt[] = new JButton[5];
	Mypanel panel;                                  //一个面板类变量
	ImageIcon main_background;                      //一个图片类变量
	private void init()
	{
		main_background = new ImageIcon("pictures/1.jpg");  //构建一个图片对象
		panel = new Mypanel(main_background);               //构建面板对象

		bt[1] = new JButton("机票预订");         //四个功能按钮
		bt[2] = new JButton("退票");
		bt[3] = new JButton("退出系统");
		bt[4] = new JButton("添加数据");
		
		panel.setLayout(null);                //设置jpane的布局管理器为空，通过手动布局
		panel.add(bt[1]);                     //向面板中添加四个功能按钮
		panel.add(bt[2]);
		panel.add(bt[3]);
		panel.add(bt[4]);

		panel.add(title);                     //向面板中添加标题
		title.setFont(new Font("楷体",Font.BOLD,38));  //设置标题字体类型 大小
		title.setForeground(Color.RED);               //字体颜色 红

		bt[1].setBounds(200,500,120,50);	          //四个功能按钮在面板的位置
		bt[2].setBounds(500,500,120,50);	
		bt[3].setBounds(800,500,120,50);
		bt[4].setBounds(1100,500,120,50);
		bt[1].setFont(new Font("楷体",Font.PLAIN,17));  //四个功能按钮字体设置
		bt[2].setFont(new Font("楷体",Font.PLAIN,17));
		bt[3].setFont(new Font("楷体",Font.PLAIN,17));
		bt[4].setFont(new Font("楷体",Font.PLAIN,17));
		
		title.setBounds(500,20,450,50);				//标题位置
		
		                                                    //事件监听采用匿名类形式
		bt[1].addActionListener( new ActionListener()		//预订按钮功能实现
		{
			public void actionPerformed(ActionEvent e) 
			{
				new OrderInterface();		//预订类,加载预订界面
			}
		} ); 
		bt[2].addActionListener( new ActionListener()	//退票按钮功能实现
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				new CancelInterface();      //退订类,加载退订界面
			}
		});
		bt[3].addActionListener(new ActionListener()		//退出系统按钮 
		{	
			public void actionPerformed(ActionEvent e)      
			{
				System.exit(1);             //关闭程序
			}
		});
		
		bt[4].addActionListener(new ActionListener()	//添加数据按钮
		{
		    public void actionPerformed(ActionEvent e) 
			{
				new AddData();             //添加类,加载添加数据界面
			}
		});
		
		frame.add(panel);                      //向框中加入面板
		frame.setSize(1500,900);               //框的大小
		frame.setVisible(true);                //开启显示框
		
	}
	public Menu()
	{
		this.init();                          //封装初始化函数init();
	}
	
	public static void main(String[] args) 
	{
		new Menu();
	}

}

