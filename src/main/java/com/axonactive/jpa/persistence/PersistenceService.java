package com.axonactive.jpa.persistence;

import javax.persistence.Query;

public interface PersistenceService<T> {
	
	T save(T entity);
	
	T update(T update);
	
	void removeEntity(T entity);
	
	void remove(Integer id);

	T findById(Integer id);

	Query createQuery(String query);

	void setPersistenceClass(Class<T> persistenceClass);
	
}
