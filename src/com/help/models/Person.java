package com.help.models;

import com.help.db.annotacion.Column;
import com.help.db.annotacion.ColumnType;
import com.help.db.annotacion.Constraint;
import com.help.db.annotacion.Table;

@Table
public class Person {
	@Column(type = ColumnType.String,value = 30)
	private String firstname;
	
	@Column(type = ColumnType.String,value = 100)
	private String lastname;
	
	@Column(type = ColumnType.Integer, constraint = @Constraint(primaryKey = true))
	private int age;
}
