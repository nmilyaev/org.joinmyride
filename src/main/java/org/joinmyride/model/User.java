package org.joinmyride.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author pankaj
 *
 */
@Entity
@Table(name="user")
//        uniqueConstraints=@UniqueConstraint(columnNames={"username"}))
public class User implements Serializable {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="USER_NAME")
    @NotEmpty //make sure name is not empty
	private String username;
	@Column(name="PASSWORD")
    @NotEmpty //make sure password is not empty
	private String password;
    @Transient
    private String confirmPassword;
	@Column(name="EMAIL", unique = true)
	private String email;

	public User() {
		this(0, "username", "password", "email");
	}
	
	public User(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

    public String getConfirmPassword() {   return confirmPassword; }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email
				+ "]";
	}

}
