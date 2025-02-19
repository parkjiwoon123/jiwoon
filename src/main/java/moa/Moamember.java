package moa;

public class Moamember {

	String id;
	String name;
	String password;
	
	public Moamember() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Moamember [id=" + id + ", password=" + password + ", name=" + name + "]";
	}
	public Moamember(String id, String name, String password) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	
}
