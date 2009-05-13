package nl.ru.cmbi.why_not.executables;

import nl.ru.cmbi.why_not.list.Lister;

import org.junit.Ignore;
import org.junit.Test;

public class ListerTest {
	@Test
	public void DSSPWithWithoutWith() throws Exception {
		Lister.main(new String[] { "DSSP", "withFile", "withoutParentFile", "withoutComment" });
	}

	@Test
	@Ignore
	public void STRUCTUREFACTORSWithoutWithoutWith() throws Exception {
		Lister.main(new String[] { "STRUCTUREFACTORS", "withoutFile", "withoutParentFile", "withComment" });
	}

	@Test
	@Ignore
	public void STRUCTUREFACTORSWithoutWithoutWithThisComment() throws Exception {
		Lister.main(new String[] { "STRUCTUREFACTORS", "withoutFile", "withoutParentFile", "withoutComment", "_refln.status column missing" });
	}
}
