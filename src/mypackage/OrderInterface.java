package mypackage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OrderInterface 
{
	JFrame frame = new JFrame("��ѯ����");     //���岢����һ����
	JLabel lb[]= new JLabel[10];            //10����ǩ           
	ImageIcon icon = new ImageIcon("pictures/С�ɻ�.jpg"); //ͼƬ
	Mypanel panel = new Mypanel(icon);      //���
	static String city[] = {"�人","���","�Ϻ�","����","�½�","����","����","����",
								 "����","����","����","����","�˲�","�㶫","����"};  //ʹ�þ�̬�ַ�������洢�ص�
	JButton confirm,cancel;                 //��ť ȷ����ȡ��
	DateChooserJButton datechoose;			//����ѡ��ť
	JTextField txt[] = new JTextField[5];   //�ı���
	JComboBox combo[] = new JComboBox[5];   //�����˵���
	public OrderInterface()
	{
		panel.setLayout(null);              //����jpane�Ĳ��ֹ�����Ϊ�գ�ͨ���ֶ�����
		lb[1] = new JLabel("��Ʊ��ѯ");
		lb[2] = new JLabel("��������");
		lb[3] = new JLabel("�������");
		lb[4] = new JLabel("��������");
		lb[5] = new JLabel("����");
		combo[1] = new JComboBox<String>();  //�������к͵����������Ӧ�������ı���
		combo[2] = new JComboBox<String>();
		combo[3] = new JComboBox<Integer>(); //��Ʊ�����������ı���
		for( int i = 0 ; i < city.length ; i++ ) //ͨ��forѭ��������city�еĳ�����ӵ������ı�����
		{
			combo[1].addItem(city[i]);       
			combo[2].addItem(city[i]);
		}
//		for( int i = 1 ; i <= 4 ; i++ )          //ͨ��forѭ����Ҫ��Ʊ��������ӵ������ı�����
//			combo[3].addItem(i);
		
		confirm = new JButton("��ѯ");            
		cancel = new JButton("����");
		datechoose = new DateChooserJButton("����ѡ������");  //����ѡ����
		
		for( int i = 1 ; i <= 5 ; i++ )		//ͨ��forѭ����ӱ�ǩ���
			panel.add(lb[i]);
		
		panel.add(combo[1]);	//��ӳ���������
		panel.add(combo[2]);
		panel.add(datechoose);  //�������ѡ���
		panel.add(confirm);     //ȷ����ť
		panel.add(cancel);      //ȡ����ť
									
		lb[1].setBounds(200, 10, 80, 60);             //5����ǩ��λ�ü�������������
		lb[1].setFont( new Font("����",Font.BOLD,17));	
		lb[2].setBounds(50,50,80,60);
		lb[2].setFont( new Font("����",Font.BOLD,16));
		lb[3].setBounds(50, 90, 80, 60);
		lb[3].setFont( new Font("����",Font.BOLD,16) );
		lb[4].setBounds(50, 130, 80, 60);
		lb[4].setFont(new Font("����",Font.BOLD,16) );		
		combo[1].setBounds(150, 70, 80, 30);          //2�����п��λ��
		combo[2].setBounds(150, 110, 80, 30);
		
		datechoose.setBounds(150, 147, 130, 30);      //����ѡ��λ�ü���������
		datechoose.setFont( new Font("����",Font.PLAIN,15) );
		
		confirm.setBounds(160, 220, 80, 30);          //ȷ����ťλ�ü���������
		confirm.setFont( new Font("����",Font.BOLD,15));
		
		cancel.setBounds(250, 220, 80, 30);           //ȡ����ťλ�ü���������
		cancel.setFont( new Font("����",Font.BOLD,15) );
		
		
		EventListener();		                      //����¼��������ڡ�ȷ�����͡�ȡ����������ť
		frame.add(panel);
		frame.setSize(500,300);					      //�������� 500  �� 300
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void EventListener()		//ȷ���ͷ��� ��ť
	{
		confirm.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String u = (String)combo[1].getSelectedItem();  //u��������ǰѡ��ĳ�������
				String v = (String)combo[2].getSelectedItem();  //v��������ǰѡ��ĵ������
				
				if( u.equals(v) )   //�жϡ��������С��͡�������С��Ƿ���ͬ����������²�������ͬ����������������ʾ
				{
					new Tip("ѡ����в��Ϸ�!");
					return;
				}
				
				String date = datechoose.getText();            //��ȡ��ǰѡ�������
	//			int number = (int)combo[3].getSelectedItem();  //��ȡҪ���Ʊ������
				new ConfirmInterface(u,v,date);         //�����ڴ˺����ѡƱ����
				frame.dispose();                               //�رղ�ѯ�����
			}
		});
		cancel.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();                              //�رղ�ѯ�����
			}
		});
	}
	
	
}
