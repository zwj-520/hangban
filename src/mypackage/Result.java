package mypackage;
import java.util.*;
public class Result 
{
	public String companyname;                //航空公司名
	public String startplace,endplace;        //起始地 终点站
	public String startairport,endairport;    //起始机场 终点机场
	public String starttime,endtime;          //出发时间 到达时间
	public int id;                            //身份证号
	public float price;                       //价格
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

