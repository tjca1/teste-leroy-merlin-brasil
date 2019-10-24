package br.com.tjca1.leroy.merlin.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.tjca1.leroy.merlin.jpa.converter.BooleanToStringConverter;

/**
 * 
 * @author Thiago Araujo
 *
 */
@Entity
@Table(name = "PRODUCT")
public class ORM implements Serializable {

	private static final long serialVersionUID = 1377723219074110154L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "CODE", nullable = false, unique = true)
	private Long code;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "FREE_SHIPPING", nullable = false)
	@Convert(converter = BooleanToStringConverter.class)
	private Boolean freeShipping;
	
	@Column(name = "DESC", nullable = false)
	private String desc;

	@Column(name = "PRICE", nullable = false)
	private BigDecimal price;

	@Column(name = "CATEGORY_ID", nullable = false)
	private Long categoryId;

	public ORM() {
		super();
	}

	public ORM(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getProductName() {
		return name;
	}

	public void setProductName(String Name) {
		this.name = Name;
	}

	public Boolean getFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(Boolean freeShipping) {
		this.freeShipping = freeShipping;
	}

	public String getProductDesc() {
		return desc;
	}

	public void setProductDesc(String desc) {
		this.desc = desc;
	}

	public BigDecimal getProductPrice() {
		return price;
	}

	public void setProductPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	

}
