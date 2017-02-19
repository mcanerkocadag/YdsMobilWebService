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
import org.hibernate.query.Query;

import models.StatisticsModel;

@Path("/statistics")
public class Statistics {
	
	@POST
	@Path("/resetstatistics")
	@Produces(MediaType.APPLICATION_JSON)
	public String resetStatistics() {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			
			session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("delete from models.StatisticsModel");
			query.executeUpdate();
			session.getTransaction().commit();
			
			
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
	@POST
	@Path("/getallstatistics")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllStatistics() {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings({ "deprecation", "unchecked" })
			List<StatisticsModel> modelList = session.createCriteria(StatisticsModel.class).list();
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
	@Path("/delstatistics")
	@Produces(MediaType.APPLICATION_JSON)
	public String delStatistics(@FormParam("idstatistics") int idstatistics) {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();

			StatisticsModel model = session.get(StatisticsModel.class, idstatistics);
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
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public String add(@FormParam("username") String username, @FormParam("examname") String examname, @FormParam("dogru") int dogru, @FormParam("status") String status){
		try{
			
			StatisticsModel model = new StatisticsModel();
			model.setUsername(username);
			model.setExamname(examname);
			model.setDogru(dogru);
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
	
	//tüm kullanýcýlar içinde top 10 istatistikler
	@POST
	@Path("/listall")
	@Produces(MediaType.APPLICATION_JSON)
	public String listall(){
		try{
			
			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			
			@SuppressWarnings({ "deprecation", "unchecked" })
			List<StatisticsModel> modelList =session.createCriteria(StatisticsModel.class).addOrder(Order.desc("dogru")).setMaxResults(10).list();
			
			session.getTransaction().commit();
			session.close();
			
			String response = "[";
			
			for(int x=0; x<modelList.size(); x++){
				response += modelList.get(x).getJsonString();
				if(x!=modelList.size()-1){
					response += ",";
				}
			}
			response += "]";
			
			return response;
			
		}catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
	//username e göre sadece kullanýcýya ait istatistikler
	@POST
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list(@FormParam("username") String username){
		try{
			
			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			
			@SuppressWarnings({ "deprecation", "unchecked" })
			List<StatisticsModel> modelList =session.createCriteria(StatisticsModel.class).add(Restrictions.eq("username", username)).addOrder(Order.desc("dogru")).setMaxResults(10).list();
			
			session.getTransaction().commit();
			session.close();
			
			String response = "[";
			
			for(int x=0; x<modelList.size(); x++){
				response += modelList.get(x).getJsonString();
				if(x!=modelList.size()-1){
					response += ",";
				}
			}
			response += "]";
			
			return response;
			
		}catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
}
