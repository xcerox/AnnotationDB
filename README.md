Este proyecto explica como crear anotaciones y utilizarlas para crear sentencias SQL.

**Estructura de la entidad persona**
```java
  @Table
  public class Person {
  	@Column(type = ColumnType.String,value = 30)
  	private String firstname;
  	
  	@Column(type = ColumnType.String,value = 100)
  	private String lastname;
  	
  	@Column(type = ColumnType.Integer, constraint = @Constraint(primaryKey = true))
  	private int age;
  }
```

**Ejemplo procesando entidad**
```java
		BuilderTable createtable = new BuilderTable();
		System.out.println(createtable.builder(Person.class));
```

**Resultado:**
  ```SQL
    CREATE TABLE PERSON(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(100),
    AGE INT PRIMARY KEY);
  ```
