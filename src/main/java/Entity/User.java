package Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT o FROM User o"),
	@NamedQuery(name="User.findByRole", query = "SELECT o FROM User o WHERE o.admin=:role"),
})

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(
			name="User.spFindByEmail",
			procedureName = "sp_FindByEmail",
			resultClasses = {User.class},
			parameters = @StoredProcedureParameter(name="email", type = String.class)
		)
})


@Entity
@Table(name="Users")
public class User {
	@Id
	String id;
	String password;
	String fullname;
	String email;
	Boolean admin=false;
	@OneToMany(mappedBy = "user")
	List<Favorite> favorites;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<Favorite> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
	
	
}
