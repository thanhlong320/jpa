package com.axonactive.jpa.persistence;

public abstract class AbstractCRUDBean<T extends IEntity> {
	
	protected abstract PersistenceService<T> getPersistenceService();
	
	public T save(T entity) {
		return this.getPersistenceService().save(entity);
	}
	
	public T update(T entity) {
		return this.getPersistenceService().update(entity);
	}
	
	public void removeEntity(T entity) {
		this.getPersistenceService().removeEntity(entity);
	}

	public void remove(Integer id) {
		this.getPersistenceService().remove(id);
	}

	public T findById(Integer id) {
		return this.getPersistenceService().findById(id);
	}
	
}

