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
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="USER_NAME")
    @NotEmpty //make sure name is not empty
	private String userName;
	@Column(name="PASSWORD")
	private String password;
    @Transient
    private String confirmPassword;
	@Column(name="EMAIL", unique = true)
	private String email;
    @Column(name="FULL_NAME")
    private String fullName;
    @Column(name="ABOUT")
    private String about;
    @Column(name="DOB")
    private String dob;
    @Column(name="PHOTO")
    private String photo;

    public User() {
		this(0, "username", "password", "email", "full name");
	}
	
	public User(int id, String username, String password, String email, String full_name) {
		super();
		this.id = id;
		this.userName = username;
		this.password = password;
		this.email = email;
        this.fullName = full_name;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", username=" + userName + ", email=" + email
				+ "]";
	}

}
