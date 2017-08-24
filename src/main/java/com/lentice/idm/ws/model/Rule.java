package com.lentice.idm.ws.model;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import java.util.Set;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="RUL", uniqueConstraints = {@UniqueConstraint(columnNames = "RUL_NAME") })
public class Rule {
	@Id
	@Column(name="RUL_KEY", nullable = false, updatable= false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	@NotBlank(message = "Rule Name cannot be empty!")
	@Column(name="RUL_NAME", nullable = false, updatable = false, unique = true)
	private String name;
	
	@Column(name="RUL_OPERATOR")
	private String operator;
	
	@Temporal(TemporalType.DATE)
	@Column(name="RUL_CREATE", updatable = false)
	private Date createDate;
	
	@Column(name="RUL_CREATEBY", updatable = false)
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="RUL_UPDATE")
	private Date updateDate;
	
	@Column(name="RUL_UPDATEBY")
	private String updateBy;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "RUL_has_RUE", 
			joinColumns = {@JoinColumn(name = "RUL_KEY", referencedColumnName = "RUL_KEY")},
			inverseJoinColumns = { @JoinColumn(name = "RUE_KEY", referencedColumnName = "RUE_KEY") })
	private Set<RuleElement> ruleElements = new HashSet<RuleElement>(0);
	
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

	public Set<RuleElement> getRuleElements() {
		return ruleElements;
	}

	public void setRuleElements(Set<RuleElement> ruleElements) {
		this.ruleElements = ruleElements;
	}
}
