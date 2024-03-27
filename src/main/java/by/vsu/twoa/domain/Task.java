package by.vsu.twoa.domain;

import java.util.Date;

public class Task extends Entity {
	private String name;
	private Date created;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
