package bcel.cardcenter.cbs.aarofat.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedNativeQueries({ 
	@NamedNativeQuery(
			name = "changePasswd", 
			query = "call changepassword(:userId,:oldPasswd,:passwd)", 
			resultClass = User.class) 
})

@NamedQueries({
	@NamedQuery(
			name="getUserByIdnPasswd",
			query="from User usr where usr.userId = :userId and SHA1(usr.passwd) = :passwd"
	),
	@NamedQuery(
			name = "getUserById",
			query = "from User usr where usr.userId like :userId"
	)
})
@Entity
@Table(name="CBS_USER")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="USERID")
	private String userId;
	@Column(name="FIRSTNAME")
	private String firstName;
	@Column(name="LASTNAME")
	private String lastName;
	@Column(name="PASSWD")
	private String passwd;
	@Column(name="EMAIL")
	private String email;
	@Column(name="ROLE")
	private String role;
	
	public void setUserId(String userId){
		this.userId=userId;
	}
	
	public String getUserId(){
		return this.userId;
	}
	
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setPasswd(String passwd){
		this.passwd=passwd;
	}
	
	public String getPasswd(){
		return this.passwd;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	
	public String getEmail(){
		return this.email;
	}
	public void setRole(String role){
		this.role=role;
	}
	
	public String getRole(){
		return this.role;
	}
}
