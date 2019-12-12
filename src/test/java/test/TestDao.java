package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import myapp.dao.Dao;

public class TestDao {
	
	static Dao dao;
	
	@BeforeClass
	public static void beforeAll() {
		dao = new Dao();
		dao.init();
	}
	
	@AfterClass
	public static void afterAll() {
		dao.close();
	}
	
	@Before
	public void setUp() {
		// pour plus tard
	}
	
	@After
	public void tearDown() {
		// pour plus tard
	}
	
	@Test
	public void testVide() {
	}
}