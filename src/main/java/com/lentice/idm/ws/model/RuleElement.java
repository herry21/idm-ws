package com.lentice.idm.ws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="RUE", uniqueConstraints = {@UniqueConstraint(columnNames = "RUE_KEY") })
public class RuleElement {
	@Id
	@Column(name="RUE_KEY", nullable = false, updatable= false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	@Column(name="RUE_ELEMENT_TYPE", nullable = false)
	private String type;
	
	@Column(name="RUE_ELEMENT_NAME", nullable = false)
	private String name;
	
	@Column(name="RUE_OPERATION", nullable = false)
	private String operator;
	
	@Column(name="RUE_ELEMENT_VALUE", nullable = false)
	private String value;
	
	@Temporal(TemporalType.DATE)
	@Column(name="RUE_CREATE", updatable = false)
	private Date createDate;
	
	@Column(name="RUE_CREATEBY", updatable = false)
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="RUE_UPDATE")
	private Date updateDate;
	
	@Column(name="RUE_UPDATEBY")
	private String updateBy;
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
