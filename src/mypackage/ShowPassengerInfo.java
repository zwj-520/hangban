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
	JFrame frame = new JFrame("删除界面");
	JPanel panel = new JPanel();
	ResultSet rs;                         //数据库接收对象
	JTable table = new JTable();          //创建一个表格对象  内置的表格类
	JScrollPane scroll;                   //滚动面板
	Vector rowdata = new Vector();        //行数据
	Vector columnnames = new Vector();    //列名
	JButton delete = new JButton("删除");   //按钮
	JButton cancel = new JButton("返回");
	Vector row;
	Vector<Result> rr = new Vector<Result>();
	
	public void init()
	{
		panel.setLayout(null);              //添加组件及布局
		panel.add(scroll);
		panel.add(delete);
		panel.add(cancel);
		delete.setBounds(150, 400, 80, 30);
		cancel.setBounds(240, 400, 80, 30);
		scroll.setBounds(0, 50, 500, 300);
		table.setRowHeight(100);           //设置的表格高度
		delete.setFont( new Font("楷体",Font.BOLD,18) );
		cancel.setFont( new Font("楷体",Font.BOLD,18) );
		
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
			new Tip("数据库异常");
			ee.printStackTrace();
		}
	}
	public void addevent()
	{
		delete.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) //点击触发删除事件
			{
				int r = table.getSelectedRow();
				try
				{
					Statement st = new Connect().st;
					ResultSet rs;
					int seat;	//表示座位号
					int flight_id = ( (Result)rr.get(r) ).id;
					String query;
					//先查询是用户是哪一个座位
					query = "select seat from user_flight where flight_id = "+String.valueOf(flight_id)
							+" and uid = '"+id+"'";
					rs = st.executeQuery(query);
					rs.next();
					seat = rs.getInt(1);	//得到座位号
					
					//删除user_flight 表里面的数据
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
						if( i+1 == seat )	//如果是将要删除的座位
							newstring +="0";
						else if( status.charAt(i) == '0' || status.charAt(i) == ' ' )
							newstring +="0";
						else newstring +="1";
					}
					status = newstring;		//由此得到了新的status
					//下一步，更新到数据库
					st = new Connect().st;
					query = "update flight set status = '"+status+"' where flight_id = "+String.valueOf(flight_id);
					st.executeUpdate(query);
					frame.dispose();
					new ShowPassengerInfo(id, name);
					new Tip("更新成功");
				}
				catch( Exception  ee )
				{
					ee.printStackTrace();
					new Tip("删除失败");
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
			columnnames.add("您的机票");
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
				
				starttime = starttime.substring(11, 16)+"  出发";
				endtime = endtime.substring(11, 16)+"  到达";
				Result r = new Result(companyname,startplace,endplace,startairport,endairport,starttime,endtime,price,flight_id);
				rr.add(r);			//维持一个复制，以便删除机票使用
				row.add(concat(r));
				rowdata.add(row);
			}
			table = new JTable(rowdata,columnnames);
			table.setRowHeight(80);
			scroll = new JScrollPane(table);
			
			DefaultTableCellRenderer cr = new DefaultTableCellRenderer();	//表格字体居中
			cr.setHorizontalAlignment(JLabel.CENTER);
			table.setDefaultRenderer(Object.class, cr);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public String getcompany(int companyid)          //获取公司
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
	public String getStartairport( int flight_id )    //获取启始机场
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
	public String getEndairport( int flight_id )     //获取到达机场
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
	public String concat(Result result)            //拼接数据
	{
		String total,place,time,airport,last;
		String space = "&nbsp;";	//HTML中的空格
		String tab = "&#9;";		//HTML中的tab
		String sspace = space+space+space;
		place = Generate_HTML_String("楷体", "150%", "red", result.startplace+"---------"+result.endplace);			
		time = Generate_HTML_String("楷体", "120%", "black", result.starttime+tab+result.endtime);
		airport = Generate_HTML_String("楷体", "125%", "black", result.startairport+sspace+sspace+result.endairport);
		last = Generate_HTML_String("楷体", "125%", "black", result.companyname+sspace+"金额:"+String.valueOf(result.price) );
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
