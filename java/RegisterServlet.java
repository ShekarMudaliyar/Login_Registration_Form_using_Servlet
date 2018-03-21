import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/new";
		String uname = "root";
		String pass = "shekar";
		String name = (String) request.getParameter("name");
		String email = (String) request.getParameter("email");
		String passw = (String) request.getParameter("password");
		String username = (String) request.getParameter("uname");
		PrintWriter out = response.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");		
			Connection con = DriverManager.getConnection(url,uname,pass);
			Statement st = con.createStatement();
			st.execute("insert into hellodemo(name,email,password,uname)values('"+name+"','"+email+"','"+passw+"','"+username+"');");
			st.execute("commit;");
			st.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("error");
			
		}
	
		out.print("Successfully Registered");
	}

}
