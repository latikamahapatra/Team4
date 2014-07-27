/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
/**
 *
 * @author names
 */
public class Authorization extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
                
    } 
    int f=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
		PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
           String uid1=request.getParameter("uid");
           String pwd1=request.getParameter("pass");
                
	
           Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","prasanna");
	
	
         Statement stmt=con.createStatement();
          ResultSet rs=stmt.executeQuery("select * from login");
          while(rs.next())
          {
              if(rs.getString(1).equals(uid1)&&rs.getString(2).equals(pwd1))
			  {
              f=1;
			  break;
			  }
          }
          if(f==1)
		  {	
			/*RequestDispatcher rd=request.getRequestDispatcher("LogicMain");
            rd.forward(request,response);*/
			
			out.println("<html><body><form action=\"/apka/ac.html\"><input type=submit value=NEXT></form>");
			//response.sendRedirect("/apka/ac");
			}
              else{                                 
                  out.println("invalid");
                  }
          //ps.setString(1, uname1);
          //ps.setString(2, pwd1);
       } catch (SQLException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }         }
    }