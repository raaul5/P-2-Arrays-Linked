package ule.ed.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;


public class ArrayEDListTest {
	private ArrayEDList<String> lista;
	
	@Before
	public void setUp() {
		 lista= new ArrayEDList<String>();
	}

	@Test
	public void ArrayVaciaTest() {
		assertEquals(0,lista.size());
	}
	
	// test addFirst comprueba que dispara excepción
	@Test(expected=NullPointerException.class)
	public void ArrayAddFirstElementoNuloTest() {
			lista.addFirst(null);
	}
	
	@Test
	public void ArrayAddFirstTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
	}
	
	@Test
	public void ArrayAddFirstExpandCapacityTest() {
		lista=new ArrayEDList<String>(3);
		
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("10");
		Assert.assertEquals("(10 7 3 2 )", lista.toString());		
	}


	//test de iteradores
	@Test
	public void ArrayIteratorTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		Iterator<String>  iter=lista.iterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void ArrayEvenIteratorNElemesParTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("8");
		Assert.assertEquals("(8 7 3 2 )", lista.toString());

		Iterator<String>  iter=lista.evenPositionsIterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	
	
	
	// TEST ITERADORES EN LISTA VACÍA
	@Test(expected=NoSuchElementException.class)
	public void ArrayNextListaVaciaTest() {
			lista.iterator().next();	
	}
	

	// test size
	@Test
	public void sizeTest() {
		assertEquals(0, lista.size());
	}

	// test isEmpty
	@Test
	public void isEmptyTest() {
		assertTrue(lista.isEmpty());
		lista.addLast("hola");
	}

	// test addFirst
	@Test 
	public void addFirstTest() {
		lista.addFirst("Hola");
		assertEquals(1, lista.size());
	}

	// test addLast
	@Test
	public void addLastTest() {
		lista.addLast("Hola");
		assertEquals(1, lista.size());
		lista.addLast("Adios");
		assertEquals(2, lista.size());
	}

	// test addPenult
	@Test 
	public void addPenultTest() {
		lista.addPenult("holaa");
		assertEquals(1, lista.size());
		lista.addPenult("holaa");
		assertEquals(2, lista.size());
	}
	
	// test addPos
	@Test
	public void addPosTest() {
		lista.addPos("hola", 0);
		assertEquals(1, lista.size());
		lista.addPos("holaa", 1);
		assertEquals(2, lista.size());	
	}

	// test removeFirst

	// test removeLast

	// test removePenult

	// test removeElem

	// test getElemPos

	// test getPosLast

	// test removeAll

	// test toString

	// test clear

	// test getPosFirst

	// test listOfRepeatedElem

	// test countElem

	// test iterator

	// test evenPositionsIterator

	// test OddPositionsIterator

	// test OddEvenIterator

	
	
}
	
	
