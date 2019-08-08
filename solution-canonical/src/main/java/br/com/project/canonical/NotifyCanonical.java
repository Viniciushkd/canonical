package br.com.project.canonical;

import java.util.Date;

import br.com.project.data.ETransactionType;

public class NotifyCanonical implements INotifyCanonical{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Object entity;
	
	private final Date dateInsert;
	
	private final ETransactionType type;
	
	public NotifyCanonical(final Builder builder) {
		this.entity = builder.entity;
		this.dateInsert = builder.dateInsert;
		this.type = builder.type;
	}

	public static class Builder implements T, DateInsert, Type {
		private Object entity;
		private Date dateInsert;
		private ETransactionType type;
		
		@Override
		public DateInsert t(Object entity) {
			this.entity = entity;
			return this;
		}
		@Override
		public Type dateInsert(Date dateInsert) {
			this.dateInsert = dateInsert;
			return this;
		}
		@Override
		public Builder type(ETransactionType type) {
			this.type = type;
			return this;
		}
		
		public NotifyCanonical build(){
			return new NotifyCanonical(this);
		}
	}
	
	public Object entity() {
		return entity;
	}
	
	@Override
	public Date dateInsert() {
		return dateInsert;
	}

	@Override
	public ETransactionType type() {
		return type;
	}
	
	public interface T {
		public DateInsert t(Object entity);
	}
	
	public interface DateInsert {
		public Type dateInsert(Date dateInsert);
	}
	
	public interface Type {
		public Builder type(final ETransactionType type);
	}
	
	public static final T builder() {
		return new Builder();
	}
	
}
