package br.com.project.builder;

import java.util.Date;

import br.com.project.data.ETransactionType;
import br.com.project.data.Notify;

public class NotifyDataBuilder {

	private final Notify instance;
	
	public NotifyDataBuilder() {
		instance = new Notify();
	}
	
	public NotifyDataBuilder setRequestId(String requestId) {
		this.instance.setRequestId(requestId);
		return this;
	}
	public NotifyDataBuilder setDate(Date date) {
		this.instance.setDate(date);
		return this;
	}
	public NotifyDataBuilder setType(ETransactionType type) {
		this.instance.setType(type);
		return this;
	}
	public Notify build() {
		return instance;
	}
}
