package DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import Entity.Report;
import Entity.User;
import Entity.Video;

public class ReportDAO {
	private EntityManager em = JpaUtils.JPA_Utils.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close(); // đóng EntityManager khi bị DAO giải phóng
		super.finalize();
	}

	public List<Report> SumLike() {
		String jpql = "SELECT new Report(o.video.title, count(o), max(o.likeDate), min(o.likeDate))"
				+ " FROM Favorite o GROUP BY o.video.title";
		TypedQuery<Report> query = em.createQuery(jpql, Report.class);
		List<Report> list = query.getResultList();
		return list;
	}

	public List<User> SelectByLikeTitle(String title1) {
		System.out.println(title1);
		StoredProcedureQuery query = em.createStoredProcedureQuery("Report.favoriteByTitle");
		query.setParameter("title", title1);
		List<User> list = query.getResultList();
		return list;
	}


}
