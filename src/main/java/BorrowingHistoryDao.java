import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowingHistoryDao {
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
	
	public String getNameByMemberId(int memberId) {
		loadDriver(dbdriver);
		Connection con = getConnection();
		String query = "SELECT Name FROM LibTrack.Members WHERE MemberID = ?";
		String res = null;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {  // Check if there is a result
	            res = rs.getString("Name");
	        } else {
	            System.out.println("No member found with ID: " + memberId);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	public List<BorrowingHistoryItem> getBorrowingHistoryByMemberId(int memberId) {
		loadDriver(dbdriver);
		Connection con = getConnection();
		String query = "SELECT bh.BorrowID, b.Title, bh.BorrowDate, bh.DueDate, bh.ReturnDate, bh.Status " +
                "FROM LibTrack.Borrowing_History bh " +
                "JOIN Books b ON bh.BookID = b.BookID " +
                "WHERE bh.MemberID = ?";
        List<BorrowingHistoryItem> borrowingHistory = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, memberId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int borrowId = rs.getInt("BorrowID");
					String title = rs.getString("Title");
					String borrowDate = rs.getDate("BorrowDate").toString();
					String dueDate = rs.getDate("DueDate").toString();
					Object returnDateObj = rs.getDate("ReturnDate");
					String returnDate;
					if (returnDateObj != null) {
						returnDate = returnDateObj.toString();
					} else {
						returnDate = "";
					}
					String status = rs.getString("Status");
					
					BorrowingHistoryItem item = new BorrowingHistoryItem(borrowId, title, borrowDate, dueDate, returnDate, status);
					borrowingHistory.add(item);
				}
			}
//			for (BorrowingHistoryItem b : borrowingHistory) {
//				System.out.println(b.getTitle());
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowingHistory;
	}
}
