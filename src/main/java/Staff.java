
public class Staff {
	private int staffID;
	private String name, email, password, phone;
	private String hireDate;
	private String role;

	public Staff() {
		super();
	}
	
	public Staff(String name, String password, String role, String email, String date) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
		this.email = email;
		this.hireDate = date;
	}
	
	public Staff(int id, String name, String password, String role, String email, String date, String phone) {
		super();
		this.staffID = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.email = email;
		this.hireDate = date;
		this.phone = phone;
	}
	
	public Staff(int id, String name, String password, String role, String email, String date) {
		super();
		this.staffID = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.email = email;
		this.hireDate = date;
	}
	
	public int getID() {
		return staffID;
	}

	public void setID(int id) {
		this.staffID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String date) {
		this.hireDate = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
