package DAO;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.User;
import Entity.Video;

public class VideoDAO extends entity<Video, String> {
	private EntityManager em = JpaUtils.JPA_Utils.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close(); // đóng EntityManager khi bị DAO giải phóng
		super.finalize();
	}

	@Override
	public Video insert(Video entity) {
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
	public Video update(Video entity) {
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
	public Video delete(String key) {
		try {
			System.out.println(key);
			Video entity = this.selectById(key);
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			System.out.println(e);
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Video selectById(String key) {
		Video video = em.find(Video.class, key);
		return video;
	}

	public List<Video> selecByKeyword(String key) {
		String jpql = "SELECT  o FROM video o " + " WHERE o.title LIKE :keyword";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("keyword", "%" + key + "%");
		List<Video> list = query.getResultList();

		return list;

	}

	public List<Video> LikeVideo(String id) {
		String jpql = "SELECT video.title, video.poster,f.likeDate FROM Favorites f \r\n"
				+ "				where f.user.video.Id:=key";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("key", id);
		List<Video> list = query.getResultList();

		return list;

	}

	@Override
	public List<Video> selectAll() {
		String jqpl = "SELECT c FROM Video c";
		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);
		List<Video> list = query.getResultList();
		return list;
	}

	public List<Video> FindAllVideoView(int page, int pageSize) {
		try {
			TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
		;
		}
	}

	public List<Video> findOffsetVideo() {
		em.clear();
		try {
			String sql = "select v from Video v order by v.id OFFSET 6 ROWS";
			TypedQuery<Video> query = em.createQuery(sql, Video.class);
			List<Video> list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public int getNumberPage() {
		Long total = this.countPage();
		int pageNumber = (int) (total / 12);
		if (total % 12 != 0) {
			pageNumber++;
		}
		return pageNumber;
	}

	public Long countPage() {
		try {
			String sql = "SELECT count(v) FROM Video v";
			TypedQuery<Long> query = em.createQuery(sql, Long.class);
			return query.getSingleResult();
		} finally {
		
		}
	}
//	public List<Video> selectAll(boolean existIsActive) {
//	    StringBuilder jpql = new StringBuilder("SELECT v FROM Video v");
//	    if (existIsActive) {
//	        jpql.append(" WHERE isActive = 1");
//	    }
//	    TypedQuery<Video> query = em.createQuery(jpql.toString(), Video.class);
//	    return query.getResultList();
//	}
//
//	    // Phương thức để lấy danh sách video trên một trang cụ thể
//	    public List<Video> FindAllVideoView(int pageNumber, int pageSize) {
//	        String jpql = "SELECT v FROM Video v"; // JPQL truy vấn
//	        TypedQuery<Video> query = em.createQuery(jpql, Video.class)
//	                .setFirstResult((pageNumber - 1) * pageSize)
//	                .setMaxResults(pageSize);
//	        return query.getResultList();
//	    }
}
