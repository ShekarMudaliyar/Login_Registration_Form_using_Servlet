import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/new";
		String uname = "root";
		String pass = "shekar";
		String passw = (String) request.getParameter("password");
		String username = (String) request.getParameter("uname");
		try{
		Class.forName("com.mysql.jdbc.Driver");		
		Connection con = DriverManager.getConnection(url,uname,pass);
		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery("select password,username from buyers where username='"+username+"';");
		rs.next();

		PrintWriter out = response.getWriter();
		String p = rs.getString(1);
		String u = rs.getString(2);
		if(username.equals(u)&&passw.equals(p)){
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			//response.sendRedirect("index.html");
			out.print("You are logged in "+username);
		}
		else{
			//response.sendRedirect("login.html");
			out.print("Wrong username or password");
		}
	st.close();
		}
	catch(Exception e){
		System.out.print("error");
	}
	}

}
	