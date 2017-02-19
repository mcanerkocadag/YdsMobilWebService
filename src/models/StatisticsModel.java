package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statistics")
public class StatisticsModel {
	
	@Id
	@GeneratedValue
	@Column(name = "idstatistics", length=0, unique=true, nullable=false)
	private int idstatistics;
	
	@Column(name = "username", length=10, unique=false, nullable=false)
	private String username;
	
	@Column(name = "examname", length=30, unique=false, nullable=false)
	private String examname;
	
	@Column(name = "dogru", length=0, unique=false, nullable=false)
	private int dogru;
	
	@Column(name = "status", length=30, unique=false, nullable=true)
	private String status;

	public int getIdstatistics() {
		return idstatistics;
	}

	public void setIdstatistics(int idstatistics) {
		this.idstatistics = idstatistics;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getExamname() {
		return examname;
	}

	public void setExamname(String examname) {
		this.examname = examname;
	}

	public int getDogru() {
		return dogru;
	}

	public void setDogru(int dogru) {
		this.dogru = dogru;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getJsonString(){
		String json = null;
		json ="{\"idstatistics\":\"" + this.idstatistics + "\","
				+"\"username\":\"" + this.username + "\","
				+"\"examname\":\"" + this.examname + "\","
				+"\"dogru\":\"" + this.dogru + "\","
				+"\"status\":\"" + this.status + "\"}";
		
		return json;
	}
}
