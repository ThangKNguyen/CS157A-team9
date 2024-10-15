import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/browseBooks")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials and URL
    private String dburl = "jdbc:mysql://localhost:3306/libtrack";
    private String dbuname = "root";
    private String dbpassword = "Th4ngalang?";
    private String dbdriver = "com.mysql.cj.jdbc.Driver";

    private void loadDriver(String dbdriver) {
        try {
            Class.forName(dbdriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = new ArrayList<>();
        loadDriver(dbdriver);

        try (Connection connection = DriverManager.getConnection(dburl, dbuname, dbpassword)) {
            System.out.println("Database connected successfully!"); // Debug statement

            String sql = "SELECT b.BookID, b.Title, a.Name AS AuthorName " +
                         "FROM books b " + 
                         "JOIN authors a ON b.AuthorID = a.AuthorID " +
                         "WHERE b.Availability = 'Available'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("BookID");
                String title = resultSet.getString("Title");
                String author = resultSet.getString("AuthorName");
                books.add(new Book(id, title, author));
            }

            System.out.println("Books retrieved: " + books.size()); // Debug statement

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("books", books);
        request.getRequestDispatcher("browseBooks.jsp").forward(request, response);
    }

}

// Book Class
class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
}


