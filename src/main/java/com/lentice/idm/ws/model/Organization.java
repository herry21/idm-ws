package com.lentice.idm.ws.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author herry
 *
 */
@Entity
@Table(name="ORG")
public class Organization {

	@Id
	@Column(name="ORG_KEY")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	@Column(name="ORG_NAME")
	private String name;
	
	@Column(name="ORG_PARENT_KEY")
	private int parentKey;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ORG_CREATE")
	private Date createDate;
	
	@Column(name="ORG_CREATEBY")
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ORG_UPDATE")
	private Date updateDate;
	
	@Column(name="ORG_UPDATEBY")
	private String updateBy;
	
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

	public int getParentKey() {
		return parentKey;
	}

	public void setParentKey(int parentKey) {
		this.parentKey = parentKey;
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