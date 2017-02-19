package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserModel {
	
	@Id
	@GeneratedValue
	@Column(name = "iduser", length=0, unique=true, nullable=false)
	private int iduser;
	
	@Column(name = "username", length=10, unique=true, nullable=false)
	private String username;
	
	@Column(name = "password", length=64, unique=false, nullable=false)
	private String password;
	
	@Column(name = "email", length=30, unique=true, nullable=false)
	private String email;
	
	@Column(name = "status", length=30, unique=false, nullable=true)
	private String status;

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getJsonString(){
		String json = null;
		json ="{\"iduser\":\"" + this.iduser + "\","
				+"\"username\":\"" + this.username + "\","
				+"\"password\":\"" + this.password + "\","
				+"\"email\":\"" + this.email + "\","
				+"\"status\":\"" + this.status + "\"}";
		
		return json;
	}
	
}
