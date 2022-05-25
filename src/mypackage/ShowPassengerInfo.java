package mypackage;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ShowPassengerInfo
{
	String id,name;
	JFrame frame = new JFrame("ɾ������");
	JPanel panel = new JPanel();
	ResultSet rs;                         //���ݿ���ն���
	JTable table = new JTable();          //����һ��������  ���õı����
	JScrollPane scroll;                   //�������
	Vector rowdata = new Vector();        //������
	Vector columnnames = new Vector();    //����
	JButton delete = new JButton("ɾ��");   //��ť
	JButton cancel = new JButton("����");
	Vector row;
	Vector<Result> rr = new Vector<Result>();
	
	public void init()
	{
		panel.setLayout(null);              //������������
		panel.add(scroll);
		panel.add(delete);
		panel.add(cancel);
		delete.setBounds(150, 400, 80, 30);
		cancel.setBounds(240, 400, 80, 30);
		scroll.setBounds(0, 50, 500, 300);
		table.setRowHeight(100);           //���õı��߶�
		delete.setFont( new Font("����",Font.BOLD,18) );
		cancel.setFont( new Font("����",Font.BOLD,18) );
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(500, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	public ShowPassengerInfo( String id,String name )
	{
		this.id = id;
		this.name = name;
		Statement st = new Connect().st;
		String query = "select * from flight where flight_id in ( select flight_id from user_flight where uid = '"+id+"' )";
		try
		{
			rs = st.executeQuery(query);
			addevent();
			gettable();
			init();
		}
		catch( Exception ee )
		{
			new Tip("���ݿ��쳣");
			ee.printStackTrace();
		}
	}
	public void addevent()
	{
		delete.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) //�������ɾ���¼�
			{
				int r = table.getSelectedRow();
				try
				{
					Statement st = new Connect().st;
					ResultSet rs;
					int seat;	//��ʾ��λ��
					int flight_id = ( (Result)rr.get(r) ).id;
					String query;
					//�Ȳ�ѯ���û�����һ����λ
					query = "select seat from user_flight where flight_id = "+String.valueOf(flight_id)
							+" and uid = '"+id+"'";
					rs = st.executeQuery(query);
					rs.next();
					seat = rs.getInt(1);	//�õ���λ��
					
					//ɾ��user_flight �����������
					st = new Connect().st;
					query = "delete from user_flight where seat = "+String.valueOf(seat);
					st.executeUpdate(query);	

					st = new Connect().st;
					query = "select status from flight where flight_id = "+String.valueOf(flight_id);
					rs = st.executeQuery(query);
					rs.next();
					String status = rs.getString(1).trim();
					String newstring="";
					for( int i = 0 ; i < 30 ; i++ )
					{
						if( i+1 == seat )	//����ǽ�Ҫɾ������λ
							newstring +="0";
						else if( status.charAt(i) == '0' || status.charAt(i) == ' ' )
							newstring +="0";
						else newstring +="1";
					}
					status = newstring;		//�ɴ˵õ����µ�status
					//��һ�������µ����ݿ�
					st = new Connect().st;
					query = "update flight set status = '"+status+"' where flight_id = "+String.valueOf(flight_id);
					st.executeUpdate(query);
					frame.dispose();
					new ShowPassengerInfo(id, name);
					new Tip("���³ɹ�");
				}
				catch( Exception  ee )
				{
					ee.printStackTrace();
					new Tip("ɾ��ʧ��");
				}
			}
		});
		cancel.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
	public void gettable()
	{
		try 
		{
			columnnames.add("���Ļ�Ʊ");
			String total;
			String first,time;
			String companyname,starttime,endtime,startplace,endplace;
			String startairport,endairport;
			
			float price;
			while( rs.next() )
			{
				row = new Vector();
				int flight_id = rs.getInt(1);
				int companyid = rs.getInt(2);
				companyname = getcompany(companyid);
				starttime = rs.getString(3);
				endtime = rs.getString(4);
				price = rs.getFloat(5);
				startplace = rs.getString(6);
				endplace = rs.getString(7);
				startairport = getStartairport(flight_id);
				endairport = getEndairport(flight_id);
				
				starttime = starttime.substring(11, 16)+"  ����";
				endtime = endtime.substring(11, 16)+"  ����";
				Result r = new Result(companyname,startplace,endplace,startairport,endairport,starttime,endtime,price,flight_id);
				rr.add(r);			//ά��һ�����ƣ��Ա�ɾ����Ʊʹ��
				row.add(concat(r));
				rowdata.add(row);
			}
			table = new JTable(rowdata,columnnames);
			table.setRowHeight(80);
			scroll = new JScrollPane(table);
			
			DefaultTableCellRenderer cr = new DefaultTableCellRenderer();	//����������
			cr.setHorizontalAlignment(JLabel.CENTER);
			table.setDefaultRenderer(Object.class, cr);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public String getcompany(int companyid)          //��ȡ��˾
	{
		Statement st = new Connect().st;
		try
		{
			ResultSet rs = st.executeQuery("select company_name from airline_company where company_id ="+String.valueOf(companyid) );
			rs.next();
			return rs.getString(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getStartairport( int flight_id )    //��ȡ��ʼ����
	{
		Statement st = new Connect().st;
		try
		{
			String query = 
			"select airport_name from airport where airport_id in ("
			+ "select airport_id from start_airport where flight_id = "+String.valueOf(flight_id)
			+")";
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getEndairport( int flight_id )     //��ȡ�������
	{
		Statement st = new Connect().st;
		try
		{
			String query = 
			"select airport_name from airport where airport_id in "
			+ "("
			+ "select airport_id from arrival_airport where flight_id = "+String.valueOf(flight_id)
			+")";
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String concat(Result result)            //ƴ������
	{
		String total,place,time,airport,last;
		String space = "&nbsp;";	//HTML�еĿո�
		String tab = "&#9;";		//HTML�е�tab
		String sspace = space+space+space;
		place = Generate_HTML_String("����", "150%", "red", result.startplace+"---------"+result.endplace);			
		time = Generate_HTML_String("����", "120%", "black", result.starttime+tab+result.endtime);
		airport = Generate_HTML_String("����", "125%", "black", result.startairport+sspace+sspace+result.endairport);
		last = Generate_HTML_String("����", "125%", "black", result.companyname+sspace+"���:"+String.valueOf(result.price) );
		total = "<html>"+place+time+airport+last+"</html>";
		return total;
	}
	public String Generate_HTML_String(String font,String size_percentage,String color,String content )
	{
		String q = "\"";
		String total = "<p style = "+q+"font-family:"+font+";"
				+"font-size:"+size_percentage+";"
				+"color:"+color+q+">"
				+content
				+"</p>";
		return total;
	}
}
