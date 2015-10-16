package main;

import com.help.models.Person;
import com.help.procesor.BuilderTable;

public class Main {

	public static void main(String[] args) {
		BuilderTable createtable = new BuilderTable();
		System.out.println(createtable.builder(Person.class));
	}

}
