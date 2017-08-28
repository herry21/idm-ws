package com.lentice.idm.ws.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author herry
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="USR", uniqueConstraints = {
		@UniqueConstraint(columnNames = "USR_KEY"),
		@UniqueConstraint(columnNames = "USR_LOGIN") })
public class User {

	@Id
	@Column(name="USR_KEY", nullable = false, updatable= false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	/*
	@Column(name="ORG_KEY")
	private int orgKey;
	*/
	@ManyToOne(optional = false)
    @JoinColumn(name="ORG_KEY")
	private Organization organization;
	
	@NotBlank(message = "User Login cannot be empty!")
	@Column(name="USR_LOGIN", nullable = false, updatable = false, unique = true)
	private String userLogin;
	
	@Column(name="USR_FIRST_NAME")
	private String firstName;
	
	@Column(name="USR_MIDDLE_NAME")
	private String middleName;
	
	@Column(name="USR_LAST_NAME")
	private String lastName;
	
	@Column(name="USR_DISPLAY_NAME")
	private String displayName;
	
	@Column(name="USR_MANAGER_KEY")
	private int managerKey;
	
	@Column(name="USR_TYPE")
	private String userType;
	
	@Column(name="USR_PASSWORD")
	private String userPassword;
	
	@Column(name="USR_STATUS")
	private String status;
	
	@Column(name="USR_EMAIL")
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name="USR_START_DATE")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="USR_END_DATE")
	private Date endDate;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        })
	@JoinTable(name = "UGM", 
			joinColumns = @JoinColumn(name = "USR_KEY", referencedColumnName = "USR_KEY",nullable = false,updatable = false),
			inverseJoinColumns = @JoinColumn(name = "GRP_KEY", referencedColumnName = "GRP_KEY",nullable = false,updatable = false))
	private Set<Role> roles = new HashSet<Role>(0);
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	/*
	public int getOrgKey() {
		return orgKey;
	}

	public void setOrgKey(int orgKey) {
		this.orgKey = orgKey;
	}
	*/

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getManagerKey() {
		return managerKey;
	}

	public void setManagerKey(int managerKey) {
		this.managerKey = managerKey;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString(){
		return "id="+key+", name="+displayName;
	}
}