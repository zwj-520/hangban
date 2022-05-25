package mypackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class ChooseSeat                      //选座位界面
{
	JFrame frame = new JFrame("选择座位");
	JPanel leftpanel = new JPanel();
	JPanel midpanel = new JPanel();
	JPanel rightpanel = new JPanel();
	JPanel uppanel = new JPanel();
	JPanel downpanel = new JPanel();
	JPanel mainpanel = new JPanel();
	JButton bt[] = new JButton[63];
	JButton confirm = new JButton();
	JButton cancel = new JButton();
	ImageIcon whiteicon = new ImageIcon("pictures\\座位矮.jpg");
	ImageIcon redicon = new ImageIcon("pictures\\座位（选中）矮.jpg");
	ImageIcon greenicon = new ImageIcon("pictures\\座位绿.jpg");
	static int a[] = new int[70];
	int number,cnt;				//人数，以及当前选了几个座位
	String u,v;
	Result result;
	Vector<Integer> seat = new Vector<Integer>();
	public ChooseSeat( Result result,int number )
	{
		this.result = result;
		this.number = number;
		cnt = 0;
		readStatus(result.id);		
		
		leftpanel.setLayout( new GridLayout(0, 3) );
		rightpanel.setLayout( new GridLayout(0,3) );
		leftpanel.setPreferredSize( new Dimension(180,150 ) );
		rightpanel.setPreferredSize( new Dimension(180, 150) );
		
		midpanel.setLayout( new GridLayout(0,1) );
		JLabel aisle = new JLabel("过道",JLabel.CENTER);		//中间部分的设置
		
		uppanel.setLayout(new GridLayout(0,8) );		//上面有8个
		uppanel.setPreferredSize( new Dimension(0,30) );
		JLabel uplabel[] = new JLabel[8];		//上面 ABC DEF
		for( int i = 0 ; i < 8 ; i++ )
		{
			if( i == 3 || i == 4 )
			{
				uplabel[i] = new JLabel("     ",JLabel.CENTER);
				uppanel.add(uplabel[i]);
				continue;
			}
			
			String s = String.valueOf( (char)('A'+i ) );
			uplabel[i] = new JLabel(s,JLabel.CENTER);
			uplabel[i].setForeground(Color.BLUE);
			uplabel[i].setFont( new Font("宋体",Font.BOLD,20) );
			uppanel.add(uplabel[i]);
		}
		
		confirm.setText("确定");
		cancel.setText("返回");  			// 下面有两个按钮 确定  和 返回
		confirm.setForeground(Color.black);
		cancel.setForeground(Color.black);
		confirm.setFont( new Font("楷体",Font.BOLD,18) );
		cancel.setFont( new Font("楷体",Font.BOLD,18) );
		
		downpanel.add(confirm);
		downpanel.add(cancel);
		
		aisle.setFont( new Font("楷体",Font.PLAIN,18)  );
		aisle.setForeground( Color.red );
		aisle.setBackground(Color.WHITE);
		midpanel.add(aisle);
		
		addbutton();
		mainpanel.setLayout( new BorderLayout() );
		mainpanel.add(leftpanel,BorderLayout.WEST);
		mainpanel.add(midpanel, BorderLayout.CENTER);
		mainpanel.add(rightpanel,BorderLayout.EAST);
		mainpanel.add(uppanel, BorderLayout.NORTH);
		mainpanel.add(downpanel, BorderLayout.SOUTH);
		
		frame.add(mainpanel);
		frame.setVisible(true);
		frame.setSize(500, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	public void addbutton()
	{
		for( int i = 1 ; i <= 30 ; i++ )
		{
			if( a[i] == 0 )
				bt[i] = new JButton(whiteicon);		//空闲位置是white
			else bt[i] = new JButton(redicon);		//被占位置是red
			
			bt[i].addActionListener( getlistener() );
			leftpanel.add(bt[i]);
		}
		for( int i = 31 ; i <= 60 ; i++ )
		{
			if( a[i] == 0 )
				bt[i] = new JButton(whiteicon);
			else bt[i] = new JButton(redicon);
			
			bt[i].addActionListener( getlistener() );
			rightpanel.add(bt[i]);
		}
		
		confirm.addActionListener( new ActionListener() 	//点击确定 的事件
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0) 		//进入信息填写界面
			{
				for( int i = 1 ; i <= 60 ; i++ )
				{
					if( bt[i].getIcon() == whiteicon )
						a[i] = 0;
					else a[i] = 1;
				}
				if( cnt != number )
					new Tip("所选数量与您需求数量不一致！");
				else
				{
					writeStatus();
					new InformationComplete(number,result,seat);	//进入信息填写界面
					frame.dispose();
				}
			}
		});
		cancel.addActionListener( new ActionListener()		//点击返回按钮
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();
			}
		});
	}
	
	public static void readStatus( int id ) //读取该航班的座位状态
	{
		for( int i = 1 ; i <= 60 ; i++ )
			a[i] = 0;
		Statement st = new Connect().st;
		String query = "select status from flight where flight_id = "+String.valueOf(id);
		ResultSet rs;
		try 
		{
			rs = st.executeQuery(query);
			rs.next();
			String status = rs.getString(1);			
			int cnt = 0;
			for( int i = 0 ; i < 60  ; i++ )
			{
				if( status.charAt(i) == '0' || status.charAt(i) == ' ' )
					a[i+1] = 0;
				else a[i+1] = 1;
				if( i+1 == status.length()  )	//如果是字符串最后一个，跳出
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ActionListener getlistener()			//座位按钮， 点击之后改变颜色
	{
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 		
			{
				for( int i = 1 ; i <= 60 ; i++ )
					if( e.getSource() == bt[i] )
					{
						//System.out.println(i);
						
						if( bt[i].getIcon() == whiteicon )	//之前是白的
						{
							bt[i].setIcon(greenicon);
							seat.add(i);
							cnt++;
						}
						else if(bt[i].getIcon() == greenicon)	//之前是绿的（取消所选）
						{
							bt[i].setIcon(whiteicon);
							seat.remove(i);
							cnt--;
						}	
						break;
					}
			}
		};
	}
	public void writeStatus()
	{
        try 
        {
        	Statement st = new Connect().st;
        	String newStatus = "";
        	for( int i = 1 ; i <= 60 ; i++ )
        		if( a[i] == 0 )
        			newStatus += "0";
        		else newStatus += "1";
        	String query = "update flight set status = "+"'"+newStatus+"'"+" where flight_id = "+String.valueOf(result.id);
        	st.executeUpdate(query);
        } 
        catch( Exception e )
        {
        	e.printStackTrace();
        }
    }
}
