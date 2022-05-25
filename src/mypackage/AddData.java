package mypackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class AddData extends JFrame implements ActionListener
{

	JPanel panel,leftpanel,rightpanel[];
	JButton bt[] = new JButton[6];
	JSplitPane splitpane;
	JLabel label = new JLabel("请选择左边的添加分类（需要拖出来）");
	ImageIcon hand = new ImageIcon("pictures\\hand2.jpg");
	JLabel handlabel = new JLabel(hand);
	JLabel lb[] = new JLabel[10];
	JTextField txt[] = new JTextField[10];
	JButton confirm,cancel;
	int lastbutton = 0;
	int array[] = {2,3,2,9};
	public AddData()
	{
		super();
		confirm = new JButton("确定");
		cancel = new JButton("退出");
		panel = new JPanel();
		leftpanel = new JPanel();
		rightpanel = new JPanel[6];
		splitpane = new JSplitPane();
		bt[1] = new JButton("添加航空公司");
		bt[2] = new JButton("添加飞机场");
		bt[3] = new JButton("添加飞机");
		bt[4] = new JButton("添加航班");
		
		bt[1].addActionListener(this);	//注册监听
		bt[2].addActionListener(this);
		bt[3].addActionListener(this);
		bt[4].addActionListener(this);
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		
		for( int i = 1 ; i <= 9 ; i++ )	//初始化
		{
			txt[i] = new JTextField();
			lb[i] = new JLabel();
			lb[i].setFont( new Font("楷体",Font.PLAIN,15) );
		}
		panel.setLayout(null);	//右边初始的panel
		//handlabel.setIcon(hand);
		panel.add(label);
		panel.add(handlabel);
		panel.add(confirm);
		panel.add(cancel);
		
		label.setBounds(180, 50, 350, 70);
		label.setFont(new Font("楷体",Font.BOLD,15) );
		handlabel.setBounds(20,50,150,70);
		confirm.setBounds(150,470,80,30);	//两个按钮位置
		cancel.setBounds(250,470,80,30);
		confirm.setFont( new Font("楷体",Font.BOLD,15) );
		cancel.setFont( new Font("楷体",Font.BOLD,15) );
		
		splitpane.setDividerLocation(JSplitPane.HORIZONTAL_SPLIT);
		splitpane.setOneTouchExpandable(false);
		leftpanel.setLayout( new GridLayout(8,1) );
		for( int i = 1 ; i <= 4 ; i++ )
		{
			leftpanel.add(bt[i]);
			bt[i].setFont( new Font("楷体",Font.BOLD,16) );
			bt[i].setForeground(Color.white);
			bt[i].setBackground( new Color(49,133,255) );	//浅蓝色比较好看
		}
			//panel.setLayout(null);
		splitpane.setLeftComponent(leftpanel);
		splitpane.setRightComponent(panel);
	
		setRightPanel();	//右边panel布置
		
		
		this.setBackground(Color.WHITE);
		this.add(splitpane);
		this.setSize(700, 550);	//窗口大小
		this.setVisible(true);
		this.setTitle("添加数据");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	/*
	public static void main( String args[] )
	{
		AddData a = new AddData();
	}*/
	
	
	public void setRightPanel()
	{
		int y = 10,gap = 25;
		for( int i = 1 ; i <= 9 ; i++ )
		{
			lb[i].setBounds(50, y, 350, 20);
			y += 25;
			txt[i].setBounds(50, y, 200, 20);
			y += gap;
			panel.add(lb[i]);
			panel.add(txt[i]);
			lb[i].setVisible(false);
			txt[i].setVisible(false);
		}
	}
	
	private boolean check()
	{
		if( lastbutton == 0 )
			return false;
		int goal = array[lastbutton-1];
		for( int i = 1 ; i <= goal ; i++ )
			if( txt[i].getText().isEmpty() )
				return false;
		return true;
	}
	private void cleartxt()
	{
		for( int i = 1 ; i <= 9 ; i++ )
			txt[i].setText("");
	}
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource().equals(bt[1]) )	//添加航空公司
		{
			cleartxt();
			handlabel.setVisible(false);
			label.setVisible(false);
			
			lb[1].setVisible(true);
			txt[1].setVisible(true);
			lb[2].setVisible(true);
			txt[2].setVisible(true);
			
			for( int i = 3 ; i <= 9 ; i++ )
			{
				lb[i].setVisible(false);
				txt[i].setVisible(false);
			}
			lb[1].setText("航空公司编号");
			lb[2].setText("航空公司名称");
			
			lastbutton = 1;
		}
		else if( e.getSource().equals(bt[2]) )	//添加飞机场
		{
			cleartxt();
			handlabel.setVisible(false);
			label.setVisible(false);
			
			for( int i = 1 ; i <= 3 ; i++ )
			{
				lb[i].setVisible(true);
				txt[i].setVisible(true);
			}
			for( int i = 4 ; i <= 9 ; i++ )
			{
				lb[i].setVisible(false);
				txt[i].setVisible(false);
			}
			
			lb[1].setText("飞机场编号");
			lb[2].setText("飞机场所在地（例如：武汉）");
			lb[3].setText("飞机场名称");
			
			lastbutton = 2;
		}
		else if( e.getSource().equals(bt[3]) )	//添加飞机
		{
			cleartxt();
			handlabel.setVisible(false);
			label.setVisible(false);
			
			for( int i = 1 ; i <= 2 ; i++ )
			{
				lb[i].setVisible(true);
				txt[i].setVisible(true);
			}
			for( int i = 3 ; i <= 9 ; i++ )
			{
				lb[i].setVisible(false);
				txt[i].setVisible(false);
			}
			
			lb[1].setText("飞机编号");
			lb[2].setText("飞机名称（例如：波音747）");
			
			lastbutton = 3;
		}
		else if( e.getSource().equals(bt[4]) )	//添加航班
		{
			cleartxt();
			handlabel.setVisible(false);
			label.setVisible(false);
			
			lb[1].setText("航班号");
			lb[2].setText("航空公司编号（编号在数据库中一定要有）");
			lb[3].setText("出发时间：例如（2020-06-17 09:30:00）");
			lb[4].setText("到达时间:(格式同上)");
			lb[5].setText("价格");
			lb[6].setText("出发地");
			lb[7].setText("目的地");
			lb[8].setText("出发地使用的机场编号");
			lb[9].setText("目的地机场编号");
			for( int i = 1 ; i <= 9 ; i++ )
			{
				lb[i].setVisible(true);
				txt[i].setVisible(true);
			}
			
			lastbutton = 4;
		}
		else if( e.getSource().equals(confirm) )	//点击确定
		{
			if( check() == false )
				new Tip("填写不能为空!");
			else
			{
				writeIntoDataBase(); 		//写进数据库
			}
		}
		else if( e.getSource().equals(cancel) )		//点击退出
		{
			this.dispose();
		}
	}
	public void writeIntoDataBase()
	{
		boolean step1 = false,step2 = false,step3 = false;	//三个步骤是否执行
		Statement st = new Connect().st;
		String query;
		String flight_id = null,company_id,startplace,endplace,starttime,endtime,price;
		String start_id = null,end_id = null;
		try
		{
			query = null;
			String id,name,place;
			if( lastbutton == 1 ) 	//航空公司
			{
				id = txt[1].getText();
				name = txt[2].getText();
				query = "insert into airline_company values("+id+",'"+name+"',"+"NULL)";
				st.executeUpdate(query);
				new Tip("插入成功");
				cleartxt();
			}
			else if( lastbutton == 2 ) 	//添加飞机场
			{
				id = txt[1].getText();
				place = txt[2].getText();
				name = txt[3].getText();
				query = "insert into airport values("+id+",'"+place+"','"+name+"')";
				st.executeUpdate(query);
				new Tip("插入成功");
				cleartxt();
			}
			else if( lastbutton == 3 )	//添加飞机
			{
				id = txt[1].getText();
				name = txt[2].getText();
				query = "insert into plane values("+id+",'"+name+"')";
				st.executeUpdate(query);
				new Tip("插入成功");
				cleartxt();
			}
			else if( lastbutton == 4 )	//添加航班
			{
				flight_id = txt[1].getText();
				company_id = txt[2].getText();
				starttime = txt[3].getText();
				endtime = txt[4].getText();
				price = txt[5].getText();
				startplace = txt[6].getText();
				endplace = txt[7].getText();
				start_id = txt[8].getText();
				end_id = txt[9].getText();
				
				ResultSet rs1,rs2,rs3;	//查询是否存在这两个机场,以及航空公司
				query = "select * from airport where airport_id = "+start_id;
				rs1 = st.executeQuery(query);
				
				st = new Connect().st;
				query = "select * from airport where airport_id = "+end_id;
				rs2 = st.executeQuery(query);
				
				st = new Connect().st;
				query = "select * from airline_company where company_id = "+company_id;
				rs3 = st.executeQuery(query);
				
				if( !rs1.next() || !rs2.next() || !rs3.next() )
					new Tip("请确认机场（航空公司）存在");
				else
				{
					String status = "";
					for( int i = 1 ; i < 30 ; i++ )
						status+="0";
					st = new Connect().st;
					query = "insert into start_airport values("+flight_id+","+start_id+")";
					st.executeUpdate(query);
					step1 = true;
					
					st = new Connect().st;
					query = "insert into arrival_airport values("+flight_id+","+end_id+")";
					st.executeUpdate(query);
					step2 = true;
					
					st = new Connect().st;
					query = "insert into flight values"
							+"("+flight_id+","+company_id+","
							+"'"+starttime+"','"+endtime+"',"+price+","
							+"'"+startplace+"',"+"'"+endplace+"','"+start_id+"','"+end_id+"','"+status+"')";
					st.executeUpdate(query);
					step3 = true;
					new Tip("添加成功");
					cleartxt();
				}
			}
		}
		catch( Exception e )
		{
			new Tip("错误，可能是相关数据在数据库中不存在");
			e.printStackTrace();
			if( step1 )
			{
				st = new Connect().st;
				query = "delete from start_airport where flight_id = "+flight_id+" and airport_id = "+start_id;
				try {
					st.execute(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if( step2 )
			{
				st = new Connect().st;
				query = "delete from arrival_airport where flight_id = "+flight_id+" and airport_id = "+end_id;
				try {
					st.execute(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if( step3 )
			{
				st = new Connect().st;
				query = "delete from flight where flight_id = "+flight_id;
				try {
					st.execute(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
