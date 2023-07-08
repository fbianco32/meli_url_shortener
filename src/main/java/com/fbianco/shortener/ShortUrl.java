package com.fbianco.shortener;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="ShortUrl", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class ShortUrl {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="URL", length=255, nullable=false)
	private String url;
	
	@Column(name="insert_time", nullable=true)
	private Date insertTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String get() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}
