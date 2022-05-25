package mypackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;

public class InformationComplete
{
	static int cnt = 0;
	JFrame frame = new JFrame("��Ϣ��д");
	JPanel panel = new JPanel();
	JLabel idlabel = new JLabel("���֤��:");
	JLabel namelabel = new JLabel("����:");
	JTextField idtxt = new JTextField();
	JTextField nametxt = new JTextField();
	JButton confirm,cancel;
	JLabel counterlabel = new JLabel("first");
	Result result;
	Vector<String> name,id;
	String startp,endp;
	Vector<Integer> seat = new Vector<Integer>();
	static int number;
	public void init()
	{
		cnt = 0;
		panel.setLayout(null);
		confirm = new JButton("ȷ��");
		cancel = new JButton("����");
		
		panel.add(idlabel);
		panel.add(namelabel);
		panel.add(idtxt);
		panel.add(nametxt);
		panel.add(confirm);
		panel.add(cancel);
		//panel.add(counterlabel);
		String s = "������˿͵���Ϣ";
		
		counterlabel = new JLabel(s);
		counterlabel.setBounds(150, 50, 180, 40);
		counterlabel.setFont( new Font("����",Font.BOLD,18));
		panel.add(counterlabel);
		
		idlabel.setBounds(120, 100, 90, 30);
		idlabel.setFont( new Font("����",Font.BOLD,16) );
		idtxt.setBounds(220, 105, 150, 22);
		
		namelabel.setBounds(130, 150,60, 30);
		namelabel.setFont( new Font("����",Font.BOLD,16) );
		nametxt.setBounds(220, 155,150,22);
		
		confirm.setBounds(150, 250, 80, 30);
		cancel.setBounds(250, 250, 80, 30);
		
		frame.add(panel);
		frame.setSize(500, 350);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public InformationComplete( int number,Result result,Vector<Integer> seat)
	{
												//���������������֮ǰѡ����λ
		for(int i:seat)
			this.seat.add(i);
		
		id = new Vector<String>();
		name = new Vector<String>();
		init();
		addevent();
		this.number = number;
		this.result = result;
			
		
	}
	
	public void addevent()
	{
		confirm.addActionListener( new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				
				String a = idtxt.getText();
				String b = nametxt.getText();
				id.add(a);
				name.add(b);
				cnt++;
				String s = "�������"+String.valueOf(cnt+1)+"λ�˿͵���Ϣ";
				idtxt.setText("");
				nametxt.setText("");
				if( cnt == number )
				{
					save();
					frame.dispose();
					new Tip("��ӳɹ�");
				}
				else new Tip(s);
				
			}
		});
		cancel.addActionListener( new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();
			}
		});
	}
	public void save()	//���ȷ����ȥ���ݿ�洢
	{
		Statement st;
		
		try
		{
			int flight_id = result.id;
			String query;
			ResultSet rs;
			for( int i = 0 ; i < id.size() ; i++ )	//����һ�ζ�����Ʊ
			{
				 st = new Connect().st;
				 String ID,Name;
				 ID = id.get(i);
				 Name = name.get(i);
				 query = "select * from u where uid = '"+id.get(i)+"'";
				 rs = st.executeQuery(query);	//�Ȳ�ѯһ�Σ������Ƿ��������
				 
				 if( !rs.next() )		//û�������
				 {
					 st = new Connect().st;
					 query = "insert into u values('"+ID+"','"+Name+"')";
					 st.execute(query);
				 }
				 st = new Connect().st;
				 query = "insert into user_flight values('"+ID+"',"+String.valueOf(result.id)+","+String.valueOf(seat.get(i) )+")";
				 st.execute(query);	//����user_flight ��
				 
			}
		}
		catch( Exception ee )
		{
			ee.printStackTrace();
			new Tip("���ʧ��");
		}
	}

}
