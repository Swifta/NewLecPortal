package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the DATABASE_SIZE database table.
 * 
 */
@Entity
@Table(name="DATABASE_SIZE")
@NamedQuery(name="DatabaseSize.findAll", query="SELECT d FROM DatabaseSize d")
public class DatabaseSize implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"Database\"")
	private String database;

	@Column(name="\"Database Size\"")
	private String database_Size;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Date\"")
	private Date date;

	@Column(name="\"Free space\"")
	private String free_space;

	@Column(name="\"Used space\"")
	private String used_space;

	public DatabaseSize() {
	}

	public String getDatabase() {
		return this.database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDatabase_Size() {
		return this.database_Size;
	}

	public void setDatabase_Size(String database_Size) {
		this.database_Size = database_Size;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFree_space() {
		return this.free_space;
	}

	public void setFree_space(String free_space) {
		this.free_space = free_space;
	}

	public String getUsed_space() {
		return this.used_space;
	}

	public void setUsed_space(String used_space) {
		this.used_space = used_space;
	}

}