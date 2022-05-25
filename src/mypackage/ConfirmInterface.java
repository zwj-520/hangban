package mypackage;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ConfirmInterface
{
	JFrame frame = new JFrame("������Ϣ");         
	JPanel panel = new JPanel();
	JPanel downpanel = new JPanel();
	ResultSet rs;	//����  ����ʱ�� ����ʱ��  ���չ�˾   ���մ����ݿ������
	Vector rowdata,columnnames;	//�������ɱ���Vector    ����   ��̬����
	JScrollPane scroll;     //������
	JTable table;            //��ά��Ԫ��
	String u,v,startairport,endairport;	    //�����أ�Ŀ�ĵأ�����������Ŀ�Ļ���
	Statement st[] = new Statement[4];      //ִ�����ݿ����Ҫ�ӿ�
	Vector<Result> temp;		        //�����ѯ�Ľ��	��������ʽ    һ������
	CardLayout card = new CardLayout();     //��Ƭ���ֹ�����   ѡ�
	String getyear(String date)
	{
		int ans = 0;
		for( int i = 0 ; i < 4 ; i++ )
			ans = ans*10+(date.charAt(i)-'0' );
		return String.valueOf(ans);
	}
	String getmonth( String date )
	{
		return String.valueOf( (date.charAt(5)-'0')*10+(date.charAt(6)-'0') );
	}
	String getday( String date )
	{
		return String.valueOf( (date.charAt(8)-'0')*10+(date.charAt(9)-'0') );
	}
	public void showtable( )
	{
		try
		{
			columnnames = new Vector();
			rowdata = new Vector();
			columnnames.add("������Ϣ");	//������Ϸ���
			panel.setLayout(card);
			String companyname;
			String time1,time2;
			String total;
			float price;
			temp = new Vector<Result>();
			int id;
			while( rs.next() )	//��֮ǰSQL��ѯ������Ū�� Vector��
			{
				time1 = rs.getTime(1).toString().trim()+" ����";
				time2 = rs.getTime(2).toString().trim()+" ����";
				companyname = rs.getString(3);
				price = rs.getFloat(4);
				id = rs.getInt(5);
				Result tt = new Result(companyname,u,v,startairport,endairport,time1,time2,price,id);
				temp.add(tt);
			}
			Vector row;
			for( int i = 0 ; i < temp.size() ; i++ )
			{
				row = new Vector();
				Result rr = (Result)temp.get(i);
				String s = concat(rr);
				row.add(s);
				rowdata.add(row);
			}
			table = new JTable(rowdata,columnnames);
			settable();
			scroll = new JScrollPane(table);
			panel.add(scroll);
			frame.add(panel);
			frame.add(downpanel,BorderLayout.SOUTH);
			frame.setVisible(true);
			frame.setSize(500, 600);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLocationRelativeTo(null);
		}
		catch( Exception ee )
		{
			ee.printStackTrace();
			new Tip("��ʾ�������");
		}		
	}
	//����ѯ�������ʽ��ʾ
	public String concat(Result result)
	{
		String total,place,time,airport,last;
		String space = "&nbsp;";	//HTML�еĿո�
		String sspace = space+space+space;
		place = Generate_HTML_String("����", "150%", "red", result.startplace+"---------"+result.endplace);		
		time = Generate_HTML_String("����", "115%", "black", result.starttime+sspace+sspace+result.endtime);		
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
	class MyMouseAdapter extends MouseAdapter		//������������λѡ�����
	{
		public void mouseClicked( MouseEvent e )
		{
			int t = table.getSelectedRow();	//getselectedRow �Ǵ�0��ʼ��
			new ChooseSeat( temp.get(t),1 );
			frame.dispose();
		}
	}
	public void settable()
	{
		table.setRowHeight(130);			//���ø߶�
		table.setBackground(Color.WHITE);		//�޸ı����ɫ
		table.addMouseListener(new MyMouseAdapter());				
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();	//����������
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);
	}
	public ConfirmInterface(String u,String v,String date)		//������ ��Ҫ�����ǲ�ѯ��������洢��rs�У����ҵ���������
	{																		
		try
		{
			this.u = u;
			this.v = v;
			panel.setLayout(null);
			String cc = "Select departure_time,arrival_time,company_name,price,flight_id "
					+ "from airline_company,flight "
					+ " where airline_company.company_id = flight.company_id "
					+"and flight.flight_id in "
					+"("
						+"select flight_id from flight where "
						+ "departure_place = '"+u+"' and destination ='"+v+"'"
						+" and year(departure_time) ="+getyear(date)
						+" and month(departure_time)="+getmonth(date)
						+" and day(departure_time)="+getday(date)
					+")";			
			st[1] = new Connect().st;
			rs = st[1].executeQuery(cc);			
			st[2] = new Connect().st;
			ResultSet tt = st[2].executeQuery("select airport_name from airport " + "where airport_place = '"+u+"'");			
			tt.next();
			startairport = tt.getString(1);	//�õ���ʼ����			
			st[3] = new Connect().st;
			String ss = "select airport_name from airport " + "where airport_place = '"+v+"'";
			tt = st[3].executeQuery(ss);
			tt.next();
			endairport = tt.getString(1);
			showtable();
		}
		catch( Exception ee )
		{
			new Tip("��ѯʧ�ܣ�������û����");
			ee.printStackTrace();
		}
	}
}