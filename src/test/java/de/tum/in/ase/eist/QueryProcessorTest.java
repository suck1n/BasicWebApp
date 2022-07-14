package de.tum.in.ase.eist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class QueryProcessorTest {

	private final QueryProcessor queryProcessor = new QueryProcessor();

	@Test
	void testEmptyStringIfCannotProcessQuery() {
		assertEquals("", queryProcessor.process("test"));
	}

	@Test
	void testKnowsAboutShakespeare() {
		String actual = queryProcessor.process("Shakespeare");
		if (!actual.contains("playwright")) {
			fail("The QueryProcessor does not know about Shakespeare.");
		}
	}

	@Test
	void testSquareAndCube() {
		String act = queryProcessor.process("5aa74350: which of the following numbers is both a square and a cube: 121, 521, 1444, 400");
		if (!act.contains("121")) {
			fail("Nope: " + act);
		}
	}

	@Test
	void testLargest() {
		String act = queryProcessor.process("afcaaf80: which of the following numbers is the largest: 772, 382, 9, 97");
		if (!act.contains("772")) {
			fail("Got: " + act);
		}
	}

	@Test
	void isNotCaseSensitive() {
		String actual = queryProcessor.process("shakespeare");
		if (!actual.contains("playwright")) {
			fail("Your QueryProcessor should not be case sensitive.");
		}
	}

}
