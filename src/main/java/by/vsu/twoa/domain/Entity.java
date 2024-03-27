package by.vsu.twoa.domain;

import java.io.Serializable;

abstract public class Entity implements Serializable {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
