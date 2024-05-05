package MyPack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/Test")
public class Test_class extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String name_pattern = "^[a-zA-Z\\s]{2,30}$";
	private static final String email_pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private static final String password_pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

	public Test_class() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (!isValidName(name)) {
			out.println("<script>alert('Name should contain only alphabets and should be between 2 and 30 characters long.'); window.location.href='index.html';</script>");
			return;
		}

		if (!isValidEmail(email)) {
	out.println("<script>alert('Invalid email format.'); window.location.href='index.html';</script>");
			return;
		}

		if (!isValidPassword(password)) {
			out.println("<script>alert('Password should be at least 8 characters long, containing at least one lowercase letter, one uppercase letter, and one digit.'); window.location.href='index.html';</script>");
			return;
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://admin.ctmwmskc62pm.ap-south-1.rds.amazonaws.com/logindb?autoReconnect=true&useSSL=false", "admin", "Parth12345");

			PreparedStatement ps = con.prepareStatement("insert into login_table VALUES (?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);

			boolean rs = ps.execute();
			if (rs == false) {
				out.println("<script>alert('Signup done successfully!'); window.location.href='index.html';</script>");
			} else {
				out.println("<script>alert('Signup failed!'); window.location.href='index.html';</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isValidName(String name) {
		Pattern pattern = Pattern.compile(name_pattern);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	private boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile(email_pattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private boolean isValidPassword(String password) {
		Pattern pattern = Pattern.compile(password_pattern);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}