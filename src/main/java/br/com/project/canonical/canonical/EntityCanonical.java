package br.com.project.canonical.canonical;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EntityCanonical implements IEntityCanonical {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4259297471464788629L;

	private final String firstName;
	
	private final String lastName;
	
	private final int age;
	
	private final java.util.Date date;
	
	public EntityCanonical(final Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.date = builder.date;
	}
	
	public static class Builder implements FirstName, LastName, Age, Date {
		private String firstName;
		private String lastName;
		private int age;
		private java.util.Date date;
		
		@Override
		public Age date(java.util.Date date) {
			this.date = date;
			return this;
		}
		@Override
		public LastName age(int age) {
			this.age = age;
			return this;
		}
		@Override
		public FirstName lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		@Override
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public EntityCanonical build() {
			return new EntityCanonical(this);
		}
	}

	public interface Date {
		public Age date(java.util.Date date);
	}

	public interface Age {
		public LastName age(int age);
	}
	
	public interface LastName {
		public FirstName lastName(String lastName);
	}
	
	public interface FirstName {
		public Builder firstName(final String firstName);
	}
	
	public static final Date builder() {
		return new Builder();
	}

	@Override
	public String firstName() {
		return firstName;
	}
	@Override
	public String lastName() {
		return lastName;
	}
	@Override
	public int age() {
		return age;
	}
	@Override
	public java.util.Date date() {
		return date;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
