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
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author herry
 *
 */
@Entity
@DynamicUpdate(value=false)
@Table(name="ITD")
public class ITDefinition {

	@Id
	@Column(name="ITD_KEY")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	@Column(name="ITD_NAME")
	@Size(min=2,max=30,message="Name must between 2 and 30 characters")
	private String name;
	
	@Column(name="ITD_DESC")
	private String description;
	
	@Column(name="ITD_INSERT_MULTIPLE")
	private int insertMultiple;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="ITD_CREATE", updatable= false)
	private Date createDate;
	
	@Column(name="ITD_CREATEBY", updatable= false)
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="ITD_UPDATE")
	private Date updateDate;
	
	@Column(name="ITD_UPDATEBY")
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInsertMultiple() {
		return insertMultiple;
	}

	public void setInsertMultiple(int insertMultiple) {
		this.insertMultiple = insertMultiple;
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