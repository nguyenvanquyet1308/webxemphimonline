package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.Video;



public abstract class entity <E, K>{
		abstract public E insert(E entity);
	    abstract public E update(E entity);
	    abstract public E delete(K key);
	    abstract public List<E> selectAll();
	    abstract public E selectById(K key);
		private EntityManager em = JpaUtils.JPA_Utils.getEntityManager();
		
	    public List<E> findAll(Class<E> clazz, boolean existIsActive, int pageNumber, int pageSize) {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive == true) {
				sql.append(" WHERE isActive = 1");
			}
			TypedQuery<E> query = em.createQuery(sql.toString(), clazz);
			//Vi query index tu 0 nen phai tru 1 ( trang 1)
			query.setFirstResult((pageNumber - 1) * pageSize);
			
			//Vi tri bat dau
			query.setMaxResults(pageSize);
			//So luong thuc the toi da - Theo Slide
			return query.getResultList();
			/*
			 * 5 phan tu : Muon 1 trang co 2 phan tu => Tong so trang : 3 trang 1: 2pt trang
			 * 2: 2pt trang 3: 1pt
			 */
		}
	    // Phương thức để lấy danh sách video trên một trang cụ thể
	    public List<Video> FindAllVideoView(int pageNumber, int pageSize) {
	        String jpql = "SELECT v FROM Video v"; // JPQL truy vấn
	        TypedQuery<Video> query = em.createQuery(jpql, Video.class)
	                .setFirstResult((pageNumber - 1) * pageSize)
	                .setMaxResults(pageSize);
	        return query.getResultList();
	    }
}
