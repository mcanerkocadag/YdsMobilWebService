package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import models.ExamModel;
import models.ExamModel2;
import models.QuestionModel;

@Path("/exams")
public class Exam {
	
	@POST
	@Path("/getallexamsmodel2")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllExamsModel2() {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings({ "deprecation", "unchecked" })
			List<ExamModel> modelList = session.createCriteria(ExamModel.class).addOrder(Order.desc("idexam")).list();
			session.getTransaction().commit();
			
			List<ExamModel2> modelListModel2 = new ArrayList<>();
			ExamModel2 model2;
			String dummy[];
			
			//json array formatýna çevriliyor.
			for (int x = 0; x < modelList.size(); x++) {
				model2=new ExamModel2();
				
				model2.setIdexam(modelList.get(x).getIdexam());
				model2.setName(modelList.get(x).getName());
				
				dummy = modelList.get(x).getQuestions().split(",");
				
				for (int x1 = 0; x1 < dummy.length; x1++) {
					session.beginTransaction();
					QuestionModel qModel = session.get(QuestionModel.class, Integer.parseInt(dummy[x1]));
					session.getTransaction().commit();
					if(qModel.getQuestionType()==1){
						model2.setKelimeBilgisi(model2.getKelimeBilgisi()+1);
					}
					if(qModel.getQuestionType()==2){
						model2.setDilBilgisi(model2.getDilBilgisi()+1);
					}
					if(qModel.getQuestionType()==3){
						model2.setClozeTest(model2.getClozeTest()+1);
					}
					if(qModel.getQuestionType()==4){
						model2.setCumleTam(model2.getCumleTam()+1);
					}
					if(qModel.getQuestionType()==5){
						model2.setIngTur(model2.getIngTur()+1);
					}
					if(qModel.getQuestionType()==6){
						model2.setTurIng(model2.getTurIng()+1);
					}
					if(qModel.getQuestionType()==7){
						model2.setOkumaParca(model2.getOkumaParca()+1);
					}
					if(qModel.getQuestionType()==8){
						model2.setDiyalogTam(model2.getDiyalogTam()+1);
					}
					if(qModel.getQuestionType()==9){
						model2.setEnYakin(model2.getEnYakin()+1);
					}
					if(qModel.getQuestionType()==10){
						model2.setParagrafTam(model2.getParagrafTam()+1);
					}
					if(qModel.getQuestionType()==11){
						model2.setAnlamBozan(model2.getAnlamBozan()+1);
					}
				}
				
				model2.setStatus(modelList.get(x).getStatus());
				
				modelListModel2.add(model2);
				
			}

			session.close();
			
			//json array formatýna çevriliyor. 
			String response = "[";
			for (int x = 0; x < modelListModel2.size(); x++) {
				response += modelListModel2.get(x).getJsonString();
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
	@Path("/updateexam")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateExam(
			@FormParam("idexam") int idexam,
			@FormParam("name") String name,
			@FormParam("counts") String counts,
			@FormParam("status") String status) {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();

			session.beginTransaction();
			ExamModel model = session.get(ExamModel.class, idexam);
			session.getTransaction().commit();

			if (!name.equals("")) {
				model.setName(name);
			}
			if (!counts.equals("")) {
				
				String typecount[] = counts.split(",");
				String questions = "";
				int count;
				for(int x=0; x<typecount.length; x++){
					count = Integer.parseInt(typecount[x]);
					if(count != 0){
						if(!questions.isEmpty()){
							questions += ",";
						}
						questions += getquestionids(x+1, count);
					}
				}
				
				model.setQuestions(questions);
			}
			if (!status.equals("")) {
				model.setStatus(status);
			}
			
			session.beginTransaction();
			session.save(model);
			session.getTransaction().commit();
			
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
	@POST
	@Path("/getallexams")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllExams() {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings({ "deprecation", "unchecked" })
			List<ExamModel> modelList = session.createCriteria(ExamModel.class).addOrder(Order.desc("idexam")).list();
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
	@Path("/delexam")
	@Produces(MediaType.APPLICATION_JSON)
	public String delExam(@FormParam("idexam") int idexam) {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();

			ExamModel model = session.get(ExamModel.class, idexam);
			session.delete(model);

			session.getTransaction().commit();
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public String search(){
		try{
			
			LocalDateTime now = LocalDateTime.now();
			
			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			
			@SuppressWarnings({ "deprecation", "unchecked" })
			List<ExamModel> modelList = session.createCriteria(ExamModel.class).list();
			
			session.getTransaction().commit();
			
			LocalDateTime date;
			long fark = 0;
			long enyakin = 9999999;
			ExamModel model = new ExamModel();
			
			//en yakýn sýnav bulunuyor. 
			for(int x=0; x<modelList.size(); x++){
				date=LocalDateTime.parse(modelList.get(x).getStatus());
				fark=fark(now,date);
				//en yakýn sýnavýn baþlamasýndan sonra 15 dakika geçmiþ mi?
				if(fark>-15 && fark<enyakin){
					enyakin=fark;
					model.setIdexam(modelList.get(x).getIdexam());
					model.setName(modelList.get(x).getName());
					model.setQuestions(modelList.get(x).getQuestions());
					model.setStatus(modelList.get(x).getStatus());
				}
				
			}
			
			String response = "";
			
			//sýnav henüz baþlamadýysa en yakýn sýnavýn tarihi geri döndürülüyor.
			//sýnav baþladýysa sýnavýn sorularý döndürülüyor.
			if(enyakin>0){
				response ="{\"return\":\"" + model.getStatus() + "\"}";
			}else{
				
				String dummy[] = model.getQuestions().split(",");
				
				response = "[{\"examname\":\"" + model.getName() + "\"},";
				
				for(int x=0; x<dummy.length; x++){
					session.beginTransaction();
					QuestionModel qmodel = session.get(QuestionModel.class, Integer.parseInt(dummy[x]));
					session.getTransaction().commit();
					response += qmodel.getJsonString();
					if(x!=dummy.length-1){
						response += ",";
					}
				}
				response += "]";
				
			}
			session.close();
			return response;
			
		}catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
		
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public String create(@FormParam("name") String name, @FormParam("counts") String counts, @FormParam("status") String status){
		try{
			
			String typecount[] = counts.split(",");
			String questions = "";
			int count;
			for(int x=0; x<11; x++){
				count = Integer.parseInt(typecount[x]);
				if(count != 0){
					if(!questions.isEmpty()){
						questions += ",";
					}
					questions += getquestionids(x+1, count);
				}
			}
			
			ExamModel model = new ExamModel();
			model.setName(name);
			model.setQuestions(questions);
			model.setStatus(status);

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(model);
			
			session.getTransaction().commit();
			session.close();
			
			
			return "{\"return\":\"success\"}";
			
		}catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
	private String getquestionids(int questionType, int count){
		
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
		
		for(int x=0; x<count; x++){
			response += modelList.get((rand1+rand2*x)%size).getIdquestion();
			if(x!=count-1){
				response += ",";
			}
		}
		
		return response;
	}
	
	private long fark(LocalDateTime date1, LocalDateTime date2){
		
		long d1, d2;
		d1 = date1.getMinute()+date1.getHour()*60+date1.getDayOfYear()*24*60+date1.getYear()*365*24*60;
		d2 = date2.getMinute()+date2.getHour()*60+date2.getDayOfYear()*24*60+date2.getYear()*365*24*60;
		
		return d2-d1-60;
	}
	
}
