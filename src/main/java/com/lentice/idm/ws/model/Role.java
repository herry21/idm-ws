package com.lentice.idm.ws.model;

import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author herry
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="GRP")
public class Role {

	@Id
	@Column(name="GRP_KEY", nullable = false, updatable= false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int key;
	
	@Column(name="GRP_NAME")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="GRP_CREATE")
	private Date createDate;
	
	@Column(name="GRP_CREATEBY")
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="GRP_UPDATE")
	private Date updateDate;
	
	@Column(name="GRP_UPDATEBY")
	private String updateBy;
	/*
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private Set<User> users = new HashSet<User>(0);
	*/
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "RUM", 
			joinColumns = {@JoinColumn(name = "GRP_KEY", referencedColumnName = "GRP_KEY")},
			inverseJoinColumns = { @JoinColumn(name = "RUL_KEY", referencedColumnName = "RUL_KEY") })
	private Set<Rule> rules = new HashSet<Rule>(0);
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/*
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	*/
	@Override
	public String toString(){
		return "id="+key+", name="+name;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}
}