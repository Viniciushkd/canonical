package br.com.project.canonical;

import java.io.Serializable;
import java.util.Date;

import br.com.project.data.ETransactionType;

public interface INotifyCanonical extends Serializable {

//	public T entity();
	
	public Date dateInsert();
	
	public ETransactionType type();
}
