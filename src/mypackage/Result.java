package mypackage;
import java.util.*;
public class Result 
{
	public String companyname;                //���չ�˾��
	public String startplace,endplace;        //��ʼ�� �յ�վ
	public String startairport,endairport;    //��ʼ���� �յ����
	public String starttime,endtime;          //����ʱ�� ����ʱ��
	public int id;                            //���֤��
	public float price;                       //�۸�
	public Result(String companyname,String startplace,String endplace,String startairport,String endairport,String starttime,String endtime,float price,int id )
	{
		this.id = id;
		this.companyname = companyname;
		this.starttime = starttime;
		this.startplace = startplace;
		this.startairport = startairport;
		this.endplace = endplace;
		this.endtime = endtime;
		this.endairport = endairport;
		this.price = price;	
	}
}

