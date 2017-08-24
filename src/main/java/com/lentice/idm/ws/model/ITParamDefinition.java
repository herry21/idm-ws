package com.lentice.idm.ws.model;

import java.util.Date;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author herry
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="IPD", uniqueConstraints = {
		@UniqueConstraint(columnNames = "IPD_KEY"),
		@UniqueConstraint(columnNames = "IPD_NAME") })
public class ITParamDefinition {

	@Id
	@Column(name="IPD_KEY",nullable = false, updatable= false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	/*
	@Column(name="ITD_KEY")
	private int itdKey;
	*/
	@ManyToOne(optional = false)
    @JoinColumn(name="ITD_KEY")
	private ITDefinition itDefinition;
	
	@Column(name="IPD_NAME")
	private String name;
	
	@Column(name="IPD_DEFAULT_VALUE")
	private String defaultValue;
	
	@Column(name="IPD_ENCRYPTED")
	private int encrypted;
	
	@Temporal(TemporalType.DATE)
	@Column(name="IPD_CREATE")
	private Date createDate;
	
	@Column(name="IPD_CREATEBY")
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="IPD_UPDATE")
	private Date updateDate;
	
	@Column(name="IPD_UPDATEBY")
	private String updateBy;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	public ITDefinition getItDefinition() {
		return itDefinition;
	}

	public void setItDefinition(ITDefinition itDefinition) {
		this.itDefinition = itDefinition;
	}

	/*
	public int getItdKey() {
		return itdKey;
	}

	public void setItdKey(int itdKey) {
		this.itdKey = itdKey;
	}
	*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(int encrypted) {
		this.encrypted = encrypted;
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

	@Override
	public String toString(){
		return "id="+key+", name="+name;
	}
}