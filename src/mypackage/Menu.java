package mypackage;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Menu 
{
	JFrame frame = new JFrame("��̨ѧԺ��ƱԤ��ϵͳ");    //����
	JLabel title = new JLabel("��̨ѧԺ��ƱԤ��ϵͳ");    //������(��ǩ)
	JButton bt[] = new JButton[5];
	Mypanel panel;                                  //һ����������
	ImageIcon main_background;                      //һ��ͼƬ�����
	private void init()
	{
		main_background = new ImageIcon("pictures/1.jpg");  //����һ��ͼƬ����
		panel = new Mypanel(main_background);               //����������

		bt[1] = new JButton("��ƱԤ��");         //�ĸ����ܰ�ť
		bt[2] = new JButton("��Ʊ");
		bt[3] = new JButton("�˳�ϵͳ");
		bt[4] = new JButton("�������");
		
		panel.setLayout(null);                //����jpane�Ĳ��ֹ�����Ϊ�գ�ͨ���ֶ�����
		panel.add(bt[1]);                     //�����������ĸ����ܰ�ť
		panel.add(bt[2]);
		panel.add(bt[3]);
		panel.add(bt[4]);

		panel.add(title);                     //���������ӱ���
		title.setFont(new Font("����",Font.BOLD,38));  //���ñ����������� ��С
		title.setForeground(Color.RED);               //������ɫ ��

		bt[1].setBounds(200,500,120,50);	          //�ĸ����ܰ�ť������λ��
		bt[2].setBounds(500,500,120,50);	
		bt[3].setBounds(800,500,120,50);
		bt[4].setBounds(1100,500,120,50);
		bt[1].setFont(new Font("����",Font.PLAIN,17));  //�ĸ����ܰ�ť��������
		bt[2].setFont(new Font("����",Font.PLAIN,17));
		bt[3].setFont(new Font("����",Font.PLAIN,17));
		bt[4].setFont(new Font("����",Font.PLAIN,17));
		
		title.setBounds(500,20,450,50);				//����λ��
		
		                                                    //�¼�����������������ʽ
		bt[1].addActionListener( new ActionListener()		//Ԥ����ť����ʵ��
		{
			public void actionPerformed(ActionEvent e) 
			{
				new OrderInterface();		//Ԥ����,����Ԥ������
			}
		} ); 
		bt[2].addActionListener( new ActionListener()	//��Ʊ��ť����ʵ��
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				new CancelInterface();      //�˶���,�����˶�����
			}
		});
		bt[3].addActionListener(new ActionListener()		//�˳�ϵͳ��ť 
		{	
			public void actionPerformed(ActionEvent e)      
			{
				System.exit(1);             //�رճ���
			}
		});
		
		bt[4].addActionListener(new ActionListener()	//������ݰ�ť
		{
		    public void actionPerformed(ActionEvent e) 
			{
				new AddData();             //�����,����������ݽ���
			}
		});
		
		frame.add(panel);                      //����м������
		frame.setSize(1500,900);               //��Ĵ�С
		frame.setVisible(true);                //������ʾ��
		
	}
	public Menu()
	{
		this.init();                          //��װ��ʼ������init();
	}
	
	public static void main(String[] args) 
	{
		new Menu();
	}

}

