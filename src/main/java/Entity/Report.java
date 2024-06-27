package Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries({
		
		@NamedStoredProcedureQuery(name = "Report.favoriteByTitle",
		procedureName = "spLikeTheoVideo",
		parameters = {
		@StoredProcedureParameter(name="title", type = String.class)},
		resultClasses = {Report.class})
})
@Entity
public class Report {
	@Id
	Serializable group;
	Long like;
	Date newest;
	Date oldest;

	public Report(Serializable group, Long like, Date newest, Date oldest) {

		this.group = group;
		this.like = like;
		this.newest = newest;
		this.oldest = oldest;
	}

	public Report() {

	}

	public Serializable getGroup() {
		return group;
	}

	public void setGroup(Serializable group) {
		this.group = group;
	}

	public Long getLike() {
		return like;
	}

	public void setLike(Long like) {
		this.like = like;
	}

	public Date getNewest() {
		return newest;
	}

	public void setNewest(Date newest) {
		this.newest = newest;
	}

	public Date getOldest() {
		return oldest;
	}

	public void setOldest(Date oldest) {
		this.oldest = oldest;
	}

}
