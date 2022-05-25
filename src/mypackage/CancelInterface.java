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
	JFrame frame = new JFrame("ɾ����Ʊ");
	ImageIcon icon = new ImageIcon("pictures//��Ʊ�ɻ�.jpg");
	Mypanel panel = new Mypanel(icon);               
	   
	JLabel idlabel = new JLabel("���֤��:");           //��ǩ
	JLabel namelabel = new JLabel("����");            //��ǩ
	JTextField idtxt = new JTextField();             //JTextField�ı���   �������֤��
	JTextField nametxt = new JTextField();           //JTextField�ı���   ��������
	JButton confirm,cancel;                          //ȷ��,ȡ����ť   ���ȶ���
	JLabel uplabel = new JLabel("����д��Ϣ��ѯ���Ļ�Ʊ"); //��ǩ
	public void init()
	{
		panel.setLayout(null);                       //���ò��ֹ�����Ϊ��,ͨ���ֶ�����
		confirm = new JButton("ȷ��");                 //������ť����
		cancel = new JButton("����");
		
		panel.add(idlabel);                        //���������
		panel.add(namelabel);
		panel.add(idtxt);
		panel.add(nametxt);
		panel.add(confirm);
		panel.add(cancel);
		panel.add(uplabel);
		
		uplabel.setBounds(140, 40, 240, 40);         //��岼��
		uplabel.setFont( new Font("����",Font.BOLD,19));
		idlabel.setBounds(120, 100, 90, 30);
		idlabel.setFont( new Font("����",Font.BOLD,16) );
		idtxt.setBounds(220, 105, 150, 22);
		namelabel.setBounds(130, 150,60, 30);
		namelabel.setFont( new Font("����",Font.BOLD,16) );
		nametxt.setBounds(220, 155,150,22);
		confirm.setBounds(150, 250, 80, 30);
		cancel.setBounds(250, 250, 80, 30);
		
		frame.add(panel);                           //����������壬�����ÿ��ӻ�
		frame.setSize(500, 350);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public CancelInterface()           //���캯��,�Ƚ��Գ�ʼ��,�ڼ����¼�����
	{
		this.init();
		addevent();
	}
	public void addevent()             //�¼�����
	{
		confirm.addActionListener( new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String ID = idtxt.getText();
				Statement st = new Connect().st;    //�������ݿ� ,������st����Ϊ�����ݿⷢ��SQL���Ķ���
				try {
					
					ResultSet rs = st.executeQuery("select * from u where uid = '"+ID+"'");  //��ѯ���صĽ���������ظò�ѯ���ɵ� ResultSet ���󣬲��᷵��null
					if(rs.next())	//����������          //����Ҳ�����rs.next()Ϊfalse
					{
						new ShowPassengerInfo(idtxt.getText(),nametxt.getText() );  //�����ó˿͵ĳ˿���Ϣ����
						frame.dispose();                           //�رս���
					}
					else
					{
						new Tip("���޴��ˣ�������");             
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
				frame.dispose();                           //�رս���
			}
		});
	}
}
