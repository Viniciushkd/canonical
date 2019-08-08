package br.com.project.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notify")
public class Notify implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String requestId;
	private Date date;
	private ETransactionType type;
	
	public Notify() {}

	public Notify(String requestId, Date date, ETransactionType type) {
		super();
		this.requestId = requestId;
		this.date = date;
		this.type = type;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ETransactionType getType() {
		return type;
	}

	public void setType(ETransactionType type) {
		this.type = type;
	}
	
}
