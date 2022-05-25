package mypackage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CancelInterface
{
	JFrame frame = new JFrame("删除机票");
	ImageIcon icon = new ImageIcon("pictures//退票飞机.jpg");
	Mypanel panel = new Mypanel(icon);               
	   
	JLabel idlabel = new JLabel("身份证号:");           //标签
	JLabel namelabel = new JLabel("姓名");            //标签
	JTextField idtxt = new JTextField();             //JTextField文本框   输入身份证号
	JTextField nametxt = new JTextField();           //JTextField文本框   输入姓名
	JButton confirm,cancel;                          //确定,取消按钮   仅先定义
	JLabel uplabel = new JLabel("请填写信息查询您的机票"); //标签
	public void init()
	{
		panel.setLayout(null);                       //设置布局管理器为空,通过手动布局
		confirm = new JButton("确定");                 //创建按钮对象
		cancel = new JButton("返回");
		
		panel.add(idlabel);                        //面板添加组件
		panel.add(namelabel);
		panel.add(idtxt);
		panel.add(nametxt);
		panel.add(confirm);
		panel.add(cancel);
		panel.add(uplabel);
		
		uplabel.setBounds(140, 40, 240, 40);         //面板布局
		uplabel.setFont( new Font("楷体",Font.BOLD,19));
		idlabel.setBounds(120, 100, 90, 30);
		idlabel.setFont( new Font("楷体",Font.BOLD,16) );
		idtxt.setBounds(220, 105, 150, 22);
		namelabel.setBounds(130, 150,60, 30);
		namelabel.setFont( new Font("楷体",Font.BOLD,16) );
		nametxt.setBounds(220, 155,150,22);
		confirm.setBounds(150, 250, 80, 30);
		cancel.setBounds(250, 250, 80, 30);
		
		frame.add(panel);                           //向界面加入面板，并设置可视化
		frame.setSize(500, 350);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public CancelInterface()           //构造函数,先进性初始化,在加入事件监听
	{
		this.init();
		addevent();
	}
	public void addevent()             //事件监听
	{
		confirm.addActionListener( new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String ID = idtxt.getText();
				Statement st = new Connect().st;    //连接数据库 ,变量“st”作为向数据库发送SQL语句的对象
				try {
					
					ResultSet rs = st.executeQuery("select * from u where uid = '"+ID+"'");  //查询返回的结果集。返回该查询生成的 ResultSet 对象，不会返回null
					if(rs.next())	//如果有这个人          //如果找不到则rs.next()为false
					{
						new ShowPassengerInfo(idtxt.getText(),nametxt.getText() );  //构建该乘客的乘客信息对象
						frame.dispose();                           //关闭界面
					}
					else
					{
						new Tip("查无此人，请重输");             
					}
				} catch (SQLException e) {
					e.printStackTrace();                       
				  }
			}
		});
		cancel.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();                           //关闭界面
			}
		});
	}
}
