package controllers;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import models.QuestionModel;

@Path("/questions")
public class Question {
	
	@POST
	@Path("/updatequestion")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateQuestion(@FormParam("idquestion") int idquestion,
			@FormParam("question") String question,
			@FormParam("content") String content,
			@FormParam("a") String a,
			@FormParam("b") String b,
			@FormParam("c") String c,
			@FormParam("d") String d,
			@FormParam("e") String e,
			@FormParam("answer") String answer,
			@FormParam("questionType") int questionType,
			@FormParam("status") String status) {

		try {
			
			Session session = new Configuration().configure().buildSessionFactory().openSession();
			
			session.beginTransaction();
			QuestionModel model = session.get(QuestionModel.class, idquestion);
			session.getTransaction().commit();
			
			if(!question.equals("")){
				model.setQuestion(question);
			}
			if(!content.equals("")){
				model.setContent(content);
			}
			if(!a.equals("")){
				model.setA(a);
			}
			if(!b.equals("")){
				model.setB(b);
			}
			if(!c.equals("")){
				model.setC(c);
			}
			if(!d.equals("")){
				model.setD(d);
			}
			if(!e.equals("")){
				model.setE(e);
			}
			if(!answer.equals("")){
				model.setAnswer(answer);
			}
			if(questionType!=0){
				model.setQuestionType(questionType);
			}
			if(!status.equals("")){
				model.setStatus(status);
			}
			
			session.beginTransaction();
			session.save(model);
			session.getTransaction().commit();
			
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e1) {
			// TODO: handle exception
			return "{\"return\":\"" + e1.getMessage() + "\"}";
		}
	}
	
	@POST
	@Path("/delquestion")
	@Produces(MediaType.APPLICATION_JSON)
	public String delQuestion(@FormParam("idquestion") int idquestion) {

		try {
			
			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			
			QuestionModel model = session.get(QuestionModel.class, idquestion);
			session.delete(model);
			
			session.getTransaction().commit();
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e1) {
			// TODO: handle exception
			return "{\"return\":\"" + e1.getMessage() + "\"}";
		}
	}
	
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON)
	public String addQuestion(@FormParam("question") String question,
			@FormParam("content") String content,
			@FormParam("a") String a,
			@FormParam("b") String b,
			@FormParam("c") String c,
			@FormParam("d") String d,
			@FormParam("e") String e,
			@FormParam("answer") String answer,
			@FormParam("questionType") int questionType,
			@FormParam("status") String status) {

		try {
			
			QuestionModel model = new QuestionModel();
			
			model.setQuestion(question);
			model.setContent(content);
			model.setA(a);
			model.setB(b);
			model.setC(c);
			model.setD(d);
			model.setE(e);
			model.setAnswer(answer);
			model.setQuestionType(questionType);
			model.setStatus(status);
			
			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(model);
			
			session.getTransaction().commit();
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e1) {
			// TODO: handle exception
			return "{\"return\":\"" + e1.getMessage() + "\"}";
		}
	}
	
	@POST
	@Path("/getallquestions")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllQuestions() {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings({ "deprecation", "unchecked" })
			List<QuestionModel> modelList = session.createCriteria(QuestionModel.class).addOrder(Order.desc("idquestion")).list();
			session.getTransaction().commit();
			session.close();
			
			//json array formatýna çevriliyor. 
			String response = "[";
			for (int x = 0; x < modelList.size(); x++) {
				response += modelList.get(x).getJsonString();
				if (x != modelList.size() - 1) {
					response += ",";
				}
			}
			response += "]";

			return response;

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}

	@POST
	@Path("/getq")
	@Produces(MediaType.APPLICATION_JSON)
	public String getquestions(@FormParam("counts") String counts) {
		try {
			
			//counts deðerleri string olarak alýnýp parçalanýp int a çevriliyor.
			//gelen counts string formatý -> 5,2,3...
			String typecount[] = counts.split(",");
			String questions = "";
			int count;
			for (int x = 0; x < 11; x++) {
				count = Integer.parseInt(typecount[x]);
				if (count != 0) {
					if (!questions.isEmpty()) {
						questions += ",";
					}
					//int questionType ý x+1 olan sorulardan count adet seç.
					//seçilen soru idleri questions dizisine string olarak atýlýr
					//questions dizisinin içeriði --> 1,3,5,4,2 gibi soru idlerinden oluþur.
					questions += getquestionids(x + 1, count);
				}
			}
			
			//questions dizisi parçalanýr, databaseden idlerine göre sorular çekilir
			//ve her soru response stringine json array formatýnda eklenir.
			String dummy[] = questions.split(",");

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			String response = "[";

			for (int x = 0; x < dummy.length; x++) {
				session.beginTransaction();
				QuestionModel model = session.get(QuestionModel.class, Integer.parseInt(dummy[x]));
				session.getTransaction().commit();
				response += model.getJsonString();
				if (x != dummy.length - 1) {
					response += ",";
				}
			}
			response += "]";

			session.close();

			return response;

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
	//questionType ý questionType olan sorulardan count tanesininin idlerini
	//string olarak geri döndürür.
	//Geri dönen örn deðer --> 1,3,5,4
	private String getquestionids(int questionType, int count) {

		Session session = new Configuration().configure().buildSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<QuestionModel> modelList = session.createCriteria(QuestionModel.class)
				.add(Restrictions.eq("questionType", questionType)).list();
		session.getTransaction().commit();
		session.close();

		int size = modelList.size();
		int rand1 = (int) (Math.random()*size);
		int rand2 = (int) (Math.random()*3+1);

		String response = "";

		for (int x = 0; x < count; x++) {
			response += modelList.get((rand1 + rand2 * x) % size).getIdquestion();
			if (x != count - 1) {
				response += ",";
			}
		}

		return response;
	}
}
