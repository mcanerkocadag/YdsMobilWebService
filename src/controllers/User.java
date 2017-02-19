package controllers;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import models.UserModel;

@Path("/users")
public class User {

	//admin tarafýndan kullanýcý bilgileri güncelleme
	@POST
	@Path("/updateuser")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@FormParam("iduser") int iduser, @FormParam("username") String username,
			@FormParam("password") String password, @FormParam("email") String email,
			@FormParam("status") String status) {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();

			session.beginTransaction();
			UserModel model = session.get(UserModel.class, iduser);
			session.getTransaction().commit();

			if (!username.equals("")) {
				model.setUsername(username);
			}
			if (!password.equals("")) {
				model.setPassword(password);
			}
			if (!email.equals("")) {
				model.setEmail(email);
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
	@Path("/deluser")
	@Produces(MediaType.APPLICATION_JSON)
	public String delUser(@FormParam("iduser") int iduser) {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();

			UserModel model = session.get(UserModel.class, iduser);
			session.delete(model);

			session.getTransaction().commit();
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}

	@GET
	@Path("/getallusers")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsers() {
		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings({ "unchecked", "deprecation" })
			List<UserModel> modelList = session.createCriteria(UserModel.class).addOrder(Order.desc("iduser")).list();

			session.getTransaction().commit();
			session.close();

			// json array formatýna çevriliyor.
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
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public String register(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("email") String email) {
		try {

			UserModel model = new UserModel();
			model.setUsername(username);
			model.setPassword(password);
			model.setEmail(email);
			model.setStatus("xxx");

			Session session = new Configuration().configure().buildSessionFactory().openSession();
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

	// sign in control
	@POST
	@Path("/signin")
	@Produces(MediaType.APPLICATION_JSON)
	public String signIn(@FormParam("username") String username, @FormParam("password") String password) {
		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			UserModel model = (UserModel) session.createCriteria(UserModel.class)
					.add(Restrictions.eq("username", username)).list().get(0);

			session.getTransaction().commit();
			session.close();

			if (model.getPassword().equals(password)) {
				return model.getJsonString();
			} else {
				return "{\"return\":\"wrong password\"}";
			}

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}

	@POST
	@Path("/changeinfo")
	@Produces(MediaType.APPLICATION_JSON)
	public String changeInfo(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("email") String email) {
		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings("deprecation")
			UserModel model = (UserModel) session.createCriteria(UserModel.class)
					.add(Restrictions.eq("username", username)).list().get(0);
			session.getTransaction().commit();

			// þifre ve email alaný boþ býrakýlmamýþsa yeni deðerlere güncelle
			if (!password.isEmpty()) {
				model.setPassword(password);
			}
			if (!email.isEmpty()) {
				model.setEmail(email);
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

}
