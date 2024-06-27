package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.Video;

public class FavoriteDAO {
	private EntityManager em = JpaUtils.JPA_Utils.getEntityManager();

	public List<Video> findAllByUserId(String id) {
		em.clear();
		try {
			String sql = "SELECT f.video FROM Favorite f WHERE f.user.id = :id";
			TypedQuery<Video> query = em.createQuery(sql, Video.class);
			query.setParameter("id", id);
			List<Video> list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
}
