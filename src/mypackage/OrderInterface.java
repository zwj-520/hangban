package mypackage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OrderInterface 
{
	JFrame frame = new JFrame("查询界面");     //定义并构建一个框
	JLabel lb[]= new JLabel[10];            //10个标签           
	ImageIcon icon = new ImageIcon("pictures/小飞机.jpg"); //图片
	Mypanel panel = new Mypanel(icon);      //面板
	static String city[] = {"武汉","天津","上海","重庆","新疆","北京","宁夏","广西",
								 "吉林","徐州","襄阳","厦门","宜昌","广东","深圳"};  //使用静态字符串数组存储地点
	JButton confirm,cancel;                 //按钮 确定和取消
	DateChooserJButton datechoose;			//日期选择按钮
	JTextField txt[] = new JTextField[5];   //文本框
	JComboBox combo[] = new JComboBox[5];   //下拉菜单类
	public OrderInterface()
	{
		panel.setLayout(null);              //设置jpane的布局管理器为空，通过手动布局
		lb[1] = new JLabel("机票查询");
		lb[2] = new JLabel("出发城市");
		lb[3] = new JLabel("到达城市");
		lb[4] = new JLabel("出发日期");
		lb[5] = new JLabel("人数");
		combo[1] = new JComboBox<String>();  //出发城市和到达城市所对应的下拉文本框
		combo[2] = new JComboBox<String>();
		combo[3] = new JComboBox<Integer>(); //订票人数的下拉文本框
		for( int i = 0 ; i < city.length ; i++ ) //通过for循环将数组city中的城市添加到下拉文本框中
		{
			combo[1].addItem(city[i]);       
			combo[2].addItem(city[i]);
		}
//		for( int i = 1 ; i <= 4 ; i++ )          //通过for循环将要订票的人数添加到下拉文本框中
//			combo[3].addItem(i);
		
		confirm = new JButton("查询");            
		cancel = new JButton("返回");
		datechoose = new DateChooserJButton("点我选择日期");  //日期选择类
		
		for( int i = 1 ; i <= 5 ; i++ )		//通过for循环添加标签组件
			panel.add(lb[i]);
		
		panel.add(combo[1]);	//添加城市下拉框
		panel.add(combo[2]);
		panel.add(datechoose);  //添加日期选择框
		panel.add(confirm);     //确定按钮
		panel.add(cancel);      //取消按钮
									
		lb[1].setBounds(200, 10, 80, 60);             //5个标签的位置及字体属性设置
		lb[1].setFont( new Font("楷体",Font.BOLD,17));	
		lb[2].setBounds(50,50,80,60);
		lb[2].setFont( new Font("楷体",Font.BOLD,16));
		lb[3].setBounds(50, 90, 80, 60);
		lb[3].setFont( new Font("楷体",Font.BOLD,16) );
		lb[4].setBounds(50, 130, 80, 60);
		lb[4].setFont(new Font("楷体",Font.BOLD,16) );		
		combo[1].setBounds(150, 70, 80, 30);          //2个下列框的位置
		combo[2].setBounds(150, 110, 80, 30);
		
		datechoose.setBounds(150, 147, 130, 30);      //日期选择位置及字体设置
		datechoose.setFont( new Font("楷体",Font.PLAIN,15) );
		
		confirm.setBounds(160, 220, 80, 30);          //确定按钮位置及字体设置
		confirm.setFont( new Font("楷体",Font.BOLD,15));
		
		cancel.setBounds(250, 220, 80, 30);           //取消按钮位置及字体设置
		cancel.setFont( new Font("楷体",Font.BOLD,15) );
		
		
		EventListener();		                      //添加事件监听对于“确定”和“取消”两个按钮
		frame.add(panel);
		frame.setSize(500,300);					      //整个界面 500  乘 300
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void EventListener()		//确定和返回 按钮
	{
		confirm.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String u = (String)combo[1].getSelectedItem();  //u变量代表当前选择的出发城市
				String v = (String)combo[2].getSelectedItem();  //v变量代表当前选择的到达城市
				
				if( u.equals(v) )   //判断“出发城市”和“到达城市”是否相同，正常情况下不可能相同，如果不合理给出提示
				{
					new Tip("选择城市不合法!");
					return;
				}
				
				String date = datechoose.getText();            //获取当前选择的日期
	//			int number = (int)combo[3].getSelectedItem();  //获取要买的票的数量
				new ConfirmInterface(u,v,date);         //进入在此航班的选票界面
				frame.dispose();                               //关闭查询界面框
			}
		});
		cancel.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();                              //关闭查询界面框
			}
		});
	}
	
	
}
