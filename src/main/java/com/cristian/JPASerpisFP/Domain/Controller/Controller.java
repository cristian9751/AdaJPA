package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.List;

public interface Controller<T> {
	public boolean save(T object);
	public boolean delete(T object);
	public List<T> getAll();
}
