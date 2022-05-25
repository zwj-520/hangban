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
	JLabel label = new JLabel("��ѡ����ߵ���ӷ��ࣨ��Ҫ�ϳ�����");
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
		confirm = new JButton("ȷ��");
		cancel = new JButton("�˳�");
		panel = new JPanel();
		leftpanel = new JPanel();
		rightpanel = new JPanel[6];
		splitpane = new JSplitPane();
		bt[1] = new JButton("��Ӻ��չ�˾");
		bt[2] = new JButton("��ӷɻ���");
		bt[3] = new JButton("��ӷɻ�");
		bt[4] = new JButton("��Ӻ���");
		
		bt[1].addActionListener(this);	//ע�����
		bt[2].addActionListener(this);
		bt[3].addActionListener(this);
		bt[4].addActionListener(this);
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		
		for( int i = 1 ; i <= 9 ; i++ )	//��ʼ��
		{
			txt[i] = new JTextField();
			lb[i] = new JLabel();
			lb[i].setFont( new Font("����",Font.PLAIN,15) );
		}
		panel.setLayout(null);	//�ұ߳�ʼ��panel
		//handlabel.setIcon(hand);
		panel.add(label);
		panel.add(handlabel);
		panel.add(confirm);
		panel.add(cancel);
		
		label.setBounds(180, 50, 350, 70);
		label.setFont(new Font("����",Font.BOLD,15) );
		handlabel.setBounds(20,50,150,70);
		confirm.setBounds(150,470,80,30);	//������ťλ��
		cancel.setBounds(250,470,80,30);
		confirm.setFont( new Font("����",Font.BOLD,15) );
		cancel.setFont( new Font("����",Font.BOLD,15) );
		
		splitpane.setDividerLocation(JSplitPane.HORIZONTAL_SPLIT);
		splitpane.setOneTouchExpandable(false);
		leftpanel.setLayout( new GridLayout(8,1) );
		for( int i = 1 ; i <= 4 ; i++ )
		{
			leftpanel.add(bt[i]);
			bt[i].setFont( new Font("����",Font.BOLD,16) );
			bt[i].setForeground(Color.white);
			bt[i].setBackground( new Color(49,133,255) );	//ǳ��ɫ�ȽϺÿ�
		}
			//panel.setLayout(null);
		splitpane.setLeftComponent(leftpanel);
		splitpane.setRightComponent(panel);
	
		setRightPanel();	//�ұ�panel����
		
		
		this.setBackground(Color.WHITE);
		this.add(splitpane);
		this.setSize(700, 550);	//���ڴ�С
		this.setVisible(true);
		this.setTitle("�������");
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
		if( e.getSource().equals(bt[1]) )	//��Ӻ��չ�˾
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
			lb[1].setText("���չ�˾���");
			lb[2].setText("���չ�˾����");
			
			lastbutton = 1;
		}
		else if( e.getSource().equals(bt[2]) )	//��ӷɻ���
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
			
			lb[1].setText("�ɻ������");
			lb[2].setText("�ɻ������ڵأ����磺�人��");
			lb[3].setText("�ɻ�������");
			
			lastbutton = 2;
		}
		else if( e.getSource().equals(bt[3]) )	//��ӷɻ�
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
			
			lb[1].setText("�ɻ����");
			lb[2].setText("�ɻ����ƣ����磺����747��");
			
			lastbutton = 3;
		}
		else if( e.getSource().equals(bt[4]) )	//��Ӻ���
		{
			cleartxt();
			handlabel.setVisible(false);
			label.setVisible(false);
			
			lb[1].setText("�����");
			lb[2].setText("���չ�˾��ţ���������ݿ���һ��Ҫ�У�");
			lb[3].setText("����ʱ�䣺���磨2020-06-17 09:30:00��");
			lb[4].setText("����ʱ��:(��ʽͬ��)");
			lb[5].setText("�۸�");
			lb[6].setText("������");
			lb[7].setText("Ŀ�ĵ�");
			lb[8].setText("������ʹ�õĻ������");
			lb[9].setText("Ŀ�ĵػ������");
			for( int i = 1 ; i <= 9 ; i++ )
			{
				lb[i].setVisible(true);
				txt[i].setVisible(true);
			}
			
			lastbutton = 4;
		}
		else if( e.getSource().equals(confirm) )	//���ȷ��
		{
			if( check() == false )
				new Tip("��д����Ϊ��!");
			else
			{
				writeIntoDataBase(); 		//д�����ݿ�
			}
		}
		else if( e.getSource().equals(cancel) )		//����˳�
		{
			this.dispose();
		}
	}
	public void writeIntoDataBase()
	{
		boolean step1 = false,step2 = false,step3 = false;	//���������Ƿ�ִ��
		Statement st = new Connect().st;
		String query;
		String flight_id = null,company_id,startplace,endplace,starttime,endtime,price;
		String start_id = null,end_id = null;
		try
		{
			query = null;
			String id,name,place;
			if( lastbutton == 1 ) 	//���չ�˾
			{
				id = txt[1].getText();
				name = txt[2].getText();
				query = "insert into airline_company values("+id+",'"+name+"',"+"NULL)";
				st.executeUpdate(query);
				new Tip("����ɹ�");
				cleartxt();
			}
			else if( lastbutton == 2 ) 	//��ӷɻ���
			{
				id = txt[1].getText();
				place = txt[2].getText();
				name = txt[3].getText();
				query = "insert into airport values("+id+",'"+place+"','"+name+"')";
				st.executeUpdate(query);
				new Tip("����ɹ�");
				cleartxt();
			}
			else if( lastbutton == 3 )	//��ӷɻ�
			{
				id = txt[1].getText();
				name = txt[2].getText();
				query = "insert into plane values("+id+",'"+name+"')";
				st.executeUpdate(query);
				new Tip("����ɹ�");
				cleartxt();
			}
			else if( lastbutton == 4 )	//��Ӻ���
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
				
				ResultSet rs1,rs2,rs3;	//��ѯ�Ƿ��������������,�Լ����չ�˾
				query = "select * from airport where airport_id = "+start_id;
				rs1 = st.executeQuery(query);
				
				st = new Connect().st;
				query = "select * from airport where airport_id = "+end_id;
				rs2 = st.executeQuery(query);
				
				st = new Connect().st;
				query = "select * from airline_company where company_id = "+company_id;
				rs3 = st.executeQuery(query);
				
				if( !rs1.next() || !rs2.next() || !rs3.next() )
					new Tip("��ȷ�ϻ��������չ�˾������");
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
					new Tip("��ӳɹ�");
					cleartxt();
				}
			}
		}
		catch( Exception e )
		{
			new Tip("���󣬿�����������������ݿ��в�����");
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
