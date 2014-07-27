import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class LogicMain extends HttpServlet
{
	Connection c;
	Statement stmt;
	String[] re=new String[50];
	
	String[] rn=new String[4];
	int[] inc=new int[4];
	int[] inn=new int[70];
	int[] rati=new int[70];
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter out=res.getWriter();
		try
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","prasanna");
			stmt=c.createStatement();
			ResultSet rs1=stmt.executeQuery("select * from recipe");
			for(int i=0; rs1.next(); i++)
			{
				re[i]=rs1.getString("rid");
				rn[i]=rs1.getString("rname");
				
			}
			for(int j=0;j<re.length;j++)
			{
					//Statement stmt=c.createStatement();
					ResultSet rs2=stmt.executeQuery("select * from ingredients i1 natural join uses u where u.rid='"+re[j]+"'");
					for(int k=0; rs2.next(); k++)
					{
						inc[k]=rs2.getInt("cost");
						inn[k]=rs2.getInt("nutrival");
						//inq[k]=rs2.getInt("quantity);
					}
			//rs2.close();
			}
			for(int i=0;i<re.length;i++)
			{
			
			rati[i]=sum(inn)-sum(inc);	
			}
			for(int p=0;p<rn.length;p++)
			{
				for(int q=0;q<rn.length-1-p;q++)
				{
					if(rati[q]>rati[q+1])
					{
						int t1=rati[q];
						rati[q]=rati[q+1];
						rati[q+1]=t1;
						String t2=rn[q];
						rn[q]=rn[q+1];
						rn[q+1]=t2;
					}
				}
			}
			for(int p=0;p<re.length;p++)
			out.println(re[p]+"    "+rn[p]+"     "+rati[p]);
			c.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	int sum(int[] a)
	{
		int u=0;
		for(int i=0;i<a.length;i++)
		{
			u+=a[i];
		}
		return u;
	}
}

	




