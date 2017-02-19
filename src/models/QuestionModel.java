package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class QuestionModel {
	
	@Id
	@GeneratedValue
	@Column(name = "idquestion", length=0, unique=true, nullable=false)
	private int idquestion;
	
	@Column(name = "question", length=500, unique=false, nullable=false)
	private String question;
	
	@Column(name = "content", length=1500, unique=false, nullable=true)
	private String content;
	
	@Column(name = "a", length=500, unique=false, nullable=false)
	private String a;
	
	@Column(name = "b", length=500, unique=false, nullable=false)
	private String b;
	
	@Column(name = "c", length=500, unique=false, nullable=false)
	private String c;
	
	@Column(name = "d", length=500, unique=false, nullable=false)
	private String d;
	
	@Column(name = "e", length=500, unique=false, nullable=false)
	private String e;
	
	@Column(name = "answer", length=1, unique=false, nullable=false)
	private String answer;
	
	@Column(name = "questionType", length=0, unique=false, nullable=false)
	private int questionType;
	
	@Column(name = "status", length=30, unique=false, nullable=true)
	private String status;

	public int getIdquestion() {
		return idquestion;
	}

	public void setIdquestion(int idquestion) {
		this.idquestion = idquestion;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getJsonString(){
		String json = null;
		json ="{\"idquestion\":\"" + this.idquestion + "\","
				+"\"question\":\"" + this.question + "\","
				+"\"content\":\"" + this.content + "\","
				+"\"a\":\"" + this.a + "\","
				+"\"b\":\"" + this.b + "\","
				+"\"c\":\"" + this.c + "\","
				+"\"d\":\"" + this.d + "\","
				+"\"e\":\"" + this.e + "\","
				+"\"answer\":\"" + this.answer + "\","
				+"\"questionType\":\"" + this.questionType + "\","
				+"\"status\":\"" + this.status + "\"}";
		
		return json;
	}
	
}
