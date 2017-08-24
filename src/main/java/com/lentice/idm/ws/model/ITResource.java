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
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author herry
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="ITR", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ITR_KEY"),
		@UniqueConstraint(columnNames = "ITR_NAME") })
public class ITResource {

	@Id
	@Column(name="ITR_KEY",nullable = false, updatable= false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	@Column(name="ITR_NAME")
	@Size(min=5, max=30, message="<b><i>IT Resource Name</i></b> must between 5 and 30 characters")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ITR_CREATE")
	private Date createDate;
	
	@Column(name="ITR_CREATEBY")
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ITR_UPDATE")
	private Date updateDate;
	
	@Column(name="ITR_UPDATEBY")
	private String updateBy;

	/*
	@Column(name="ITD_KEY")
	private int itrTypeKey;
	*/
	
	@ManyToOne(optional = false)
    @JoinColumn(name="ITD_KEY")
	private ITDefinition itDefinition;
	
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
	public int getItrTypeKey() {
		return itrTypeKey;
	}

	public void setItrTypeKey(int itrTypeKey) {
		this.itrTypeKey = itrTypeKey;
	}
	*/
	@Override
	public String toString(){
		return "id="+key+", name="+name;
	}
}