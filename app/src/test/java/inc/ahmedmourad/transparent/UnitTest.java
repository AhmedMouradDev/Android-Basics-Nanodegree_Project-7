package inc.ahmedmourad.transparent;

import org.junit.Test;

import inc.ahmedmourad.transparent.query.Query;
import inc.ahmedmourad.transparent.query.elements.Group;

import static junit.framework.Assert.assertEquals;

public class UnitTest {

	@Test
	public void query_isValid() {

		final String query = Query.with("A")
				.and()
				.beginGroup()
				.param("B")
				.or()
				.param("C")
				.endGroup()
				.or()
				.beginGroup()
				.param("C")
				.and()
				.group(Group.with("D").or().param("E"))
				.endGroup()
				.toString();

		final String query1 = Query.with("A")
				.and()
				.param("B")
				.and()
				.param("B")
				.or()
				.beginGroup()
				.param("B")
				.or()
				.param("B")
				.endGroup()
				.or()
				.and()
				.beginGroup()
				.param("C")
				.and()
				.group(Group.with("D").or().param("E"))
				.endGroup()
				.toString();

		final String query2 = Query.with("A")
				.and()
				.beginGroup()
				.param("B")
				.or()
				.param("C")
				.endGroup()
				.or()
				.beginGroup()
				.param("D")
				.and()
				.group(Group.with("E").or().param("F"))
				.endGroup()
				.toString();

		final Query query3 = Query.with("A")
				.and()
				.beginGroup()
				.param("B")
				.or()
				.param("C")
				.endGroup()
				.or()
				.beginGroup()
				.param("D")
				.and()
				.beginGroup()
				.param("E")
				.or()
				.param("F")
				.and()
				.beginGroup()
				.param("G")
				.and()
				.param("H")
				.endGroup()
				.and()
				.group(Group.with("I").or().param("J"))
				.or()
				.beginGroup()
				.param("K")
				.and()
				.param("L")
				.endGroup()
				.and()
				.group(Group.with("M").or().param("N"))
				.and()
				.param("O")
				.endGroup()
				.and()
				.param("P")
				.endGroup();

		System.out.println(query);
		System.out.println(query1);
		System.out.println(query2);
		System.out.println(query3.toString());

		final String json = query3.toJson(true);

		System.out.println(json);

		final String query4 = Query.fromJson(json).toString();

		System.out.println(query4);

		System.out.println(Query.empty().toJson());

		assertEquals("\"A\" AND (\"B\" OR \"C\") OR (\"D\" AND (\"E\" OR \"F\" AND (\"G\" AND \"H\") AND (\"I\" OR \"J\") OR (\"K\" AND \"L\") AND (\"M\" OR \"N\") AND \"O\") AND \"P\")", query3.toString());
		assertEquals("\"A\" AND (\"B\" OR \"C\") OR (\"D\" AND (\"E\" OR \"F\" AND (\"G\" AND \"H\") AND (\"I\" OR \"J\") OR (\"K\" AND \"L\") AND (\"M\" OR \"N\") AND \"O\") AND \"P\")", query4);
	}
}
