import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffRegisterDao {
	private String dburl="jdbc:mysql://localhost:3306/LibTrack";
	private String dbuname="root";
	private String dbpassword="CS157A@SJSU";
	private String dbdriver="com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbdriver) {
		try {
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl, dbuname, dbpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	public String insert(Staff staff) {
		loadDriver(dbdriver);
		Connection con = getConnection();
		String result = "data entered successfully";

		// Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

		String sql = "insert into LibTrack.Staff (Name, Email, PhoneNumber, Address, JoinDate) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, staff.getName());
			ps.setString(2, staff.getEmail());
			ps.setString(3, staff.getPhone());
			ps.setString(4, staff.getAddress());
			ps.setString(5, formattedDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			result = "Data not entered";
			e.printStackTrace();
		}
		return result;
	}
}
