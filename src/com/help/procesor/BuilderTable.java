package com.help.procesor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.help.db.annotacion.AnnotacionException;
import com.help.db.annotacion.Column;
import com.help.db.annotacion.Constraint;
import com.help.db.annotacion.Table;

import static com.help.util.EmptyUtil.*;

public class BuilderTable {
	
	public String builder(Class<?> model) throws AnnotacionException 
	{
		Table table = model.getAnnotation(Table.class);
		if(table.equals(null))
			throw new AnnotacionException("No contiene la anotacion de table");
		
		String tableName = EMPTY_STRING;
		if(table.name().isEmpty())
			tableName = model.getSimpleName().toUpperCase();
		else
			tableName = table.name();
		
		List<String> columns = getColumns(model);
		
		if(columns.isEmpty())
			throw new AnnotacionException("No contiene campos para la tabla");
		
		StringBuilder buildTable = new StringBuilder();
		
		buildTable.append("CREATE TABLE ");
		buildTable.append(tableName);
		buildTable.append("(");
		
		for(String column : columns){
			buildTable.append("\n ");
			buildTable.append(column);
			buildTable.append(",");
		}
		
		String cleanComma = buildTable.substring(EMPTY_INTEGER, buildTable.length() - 1); 
		return cleanComma + ");";
	}

	private List<String> getColumns(Class<?> model){
		
		List<String> columns = new ArrayList<>();
		StringBuilder sqlText;
		for(Field field: model.getDeclaredFields()){
			
			String columnName = EMPTY_STRING;
			Annotation[] annotation = field.getAnnotations();
			
			if(annotation.length > EMPTY_INTEGER){
				if(annotation[0] instanceof Column){
					Column column = (Column) annotation[0];
					
					if(column.name().isEmpty())
						columnName = field.getName().toUpperCase();
					else
						columnName = column.name();
					
					sqlText = new StringBuilder();
					sqlText.append(columnName);
					
					switch(column.type()){
						case Integer:
							sqlText.append(" INT");
							break;
						case String:
							sqlText.append(" VARCHAR(");
							sqlText.append(column.value());
							sqlText.append(")");
							break;
						default:
							throw new AnnotacionException("Tipos de datos no Admitidos");
					}
					
					sqlText.append(getConstraints(column.constraint()));
					columns.add(sqlText.toString());
				}
				
			}else
				continue;
		}
		return columns;	
	}

	private String getConstraints(Constraint constraints){
		StringBuilder textConstraint = new StringBuilder();
		
		if(!constraints.allowNull())
			textConstraint.append(" NOT NULL");
		if(constraints.primaryKey())
			textConstraint.append(" PRIMARY KEY");
		if(constraints.unique())
			textConstraint.append(" UNIQUE");
		
		return textConstraint.toString();
	}

}
