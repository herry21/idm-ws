package com.lentice.idm.ws.model;

import java.util.Date;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author herry
 *
 */
@Entity
@Table(name="ITP")
public class ITParam {

	@Id
	@Column(name="ITP_KEY")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	/*
	@Column(name="ITR_KEY")
	private int itrKey;
	*/
	@ManyToOne(optional = false)
    @JoinColumn(name="ITR_KEY")
	private ITResource itr;
	
	/*
	@Column(name="IPD_KEY")
	private int ipdKey;
	*/
	@ManyToOne(optional = false)
    @JoinColumn(name="IPD_KEY")
	private ITParamDefinition ipd;
	
	@Column(name="ITP_FIELD_VALUE")
	private String fieldValue;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ITP_CREATE")
	private Date createDate;
	
	@Column(name="ITP_CREATEBY")
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ITP_UPDATE")
	private Date updateDate;
	
	@Column(name="ITP_UPDATEBY")
	private String updateBy;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	/*
	public int getItrKey() {
		return itrKey;
	}

	public void setItrKey(int itrKey) {
		this.itrKey = itrKey;
	}
	*/
	
	public ITResource getItr() {
		return itr;
	}

	public void setItr(ITResource itr) {
		this.itr = itr;
	}
	/*
	public int getIpdKey() {
		return ipdKey;
	}

	public void setIpdKey(int ipdKey) {
		this.ipdKey = ipdKey;
	}
	*/

	public ITParamDefinition getIpd() {
		return ipd;
	}

	public void setIpd(ITParamDefinition ipd) {
		this.ipd = ipd;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
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
		return "id="+key+", value="+fieldValue;
	}
}