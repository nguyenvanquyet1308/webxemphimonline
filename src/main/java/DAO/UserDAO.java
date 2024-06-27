package DAO;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Entity.User;
import Entity.Video;


public class UserDAO extends entity<User, String>{
	private EntityManager em = JpaUtils.JPA_Utils.getEntityManager();
	@Override
	protected void finalize() throws Throwable {
		em.close(); // đóng EntityManager khi bị DAO giải phóng
		super.finalize();
	}
	@Override
	public User insert(User entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity); // insert
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			 throw new RuntimeException(e);
		}
	
	}
	@Override
	public User update(User entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity); // update
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			 throw new RuntimeException(e);
		}
	}
	@Override
	public User delete(String id) {
		try {
			User entity = this.selectById(id);
			em.getTransaction().begin();			
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<User> selectAll() {
		String jqpl = "SELECT c FROM User c";
		TypedQuery<User> query = em.createQuery(jqpl, User.class);
		List<User> list = query.getResultList();
		return list;
		
	}
	@Override
	public User selectById(String key) {
		User user = em.find(User.class, key);	
		return user;
	}
	public void UpdatePassword(String Passwordnew, String email) {
	    EntityTransaction transaction = em.getTransaction();
	    try {
	        transaction.begin();

	        String jqpl = "UPDATE User u SET u.password = :passwordnew WHERE u.email = :to";
	        javax.persistence.Query query = em.createQuery(jqpl);
	        query.setParameter("passwordnew", Passwordnew);
	        query.setParameter("to", email);

	        query.executeUpdate();

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback(); // Rollback giao dịch nếu có lỗi xảy ra
	        }
	        e.printStackTrace();
	        throw e; // Ném lại ngoại lệ để xử lý ở mức cao hơn
	    }
	}


	

}
