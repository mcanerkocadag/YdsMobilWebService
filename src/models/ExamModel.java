package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class ExamModel {
	
	@Id
	@GeneratedValue
	@Column(name = "idexam", length=0, unique=true, nullable=false)
	private int idexam;
	
	@Column(name = "name", length=30, unique=true, nullable=false)
	private String name;
	
	@Column(name = "questions", length=500, unique=false, nullable=false)
	private String questions;
	
	@Column(name = "status", length=30, unique=false, nullable=true)
	private String status;

	public int getIdexam() {
		return idexam;
	}

	public void setIdexam(int idexam) {
		this.idexam = idexam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getJsonString(){
		String json = null;
		json ="{\"idexam\":\"" + this.idexam + "\","
				+"\"name\":\"" + this.name + "\","
				+"\"questions\":\"" + this.questions + "\","
				+"\"status\":\"" + this.status + "\"}";
		
		return json;
	}
	
}
