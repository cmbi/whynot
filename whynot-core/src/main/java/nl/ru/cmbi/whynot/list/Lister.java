package nl.ru.cmbi.whynot.list;

import java.util.SortedSet;

import nl.ru.cmbi.whynot.hibernate.SpringUtil;
import nl.ru.cmbi.whynot.hibernate.GenericDAO.DatabankDAO;
import nl.ru.cmbi.whynot.hibernate.GenericDAO.EntryDAO;
import nl.ru.cmbi.whynot.model.Databank;
import nl.ru.cmbi.whynot.model.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Lister {
	private enum SelectionType {
		VALID, OBSOLETE, MISSING, ANNOTATED, UNANNOTATED
	};

	public static void main(String... args) {
		String dbname = "DATABASE";
		String selection = "VALID|OBSOLETE|MISSING|ANNOTATED|UNANNOTATED";
		if (args.length != 2 || !args[0].matches(dbname) || !args[1].matches(selection))
			throw new IllegalArgumentException("Usage: lister " + dbname + " " + selection);

		Lister lister = (Lister) SpringUtil.getContext().getBean("lister");
		lister.list(dbname, SelectionType.valueOf(selection));
	}

	@Autowired
	EntryDAO	entdao;

	private void list(String dbname, SelectionType selection) {
		switch (selection) {//TODO
		case VALID:

		case OBSOLETE:

		case MISSING:

		case ANNOTATED:

		case UNANNOTATED:

		}
	}

	public static void main_old(String[] args) throws Exception {
		String dbname = "DATABASE";
		String fileFilter = "withFile|withoutFile";
		String parentFilter = "withParentFile|withoutParentFile";
		String commentFilter = "withComment|withoutComment";
		String comment = "[\"Example comment\"]";

		if (args.length != 4 && args.length != 5 || !args[1].matches(fileFilter) || !args[2].matches(parentFilter) || !args[3].matches(commentFilter))
			throw new IllegalArgumentException("Usage: lister " + dbname + " " + fileFilter + " " + parentFilter + " " + commentFilter + " " + comment);

		dbname = args[0];
		fileFilter = args[1];
		parentFilter = args[2];
		commentFilter = args[3];
		comment = null;
		if (args.length == 5)
			comment = args[4];

		Lister lister = (Lister) SpringUtil.getContext().getBean("lister");
		lister.list_old(dbname, fileFilter, parentFilter, commentFilter, comment);
	}

	@Autowired
	private DatabankDAO	dbdao;

	public void list_old(String dbname, String fileFilter, String parentFilter, String commentFilter, String comment) throws Exception {
		Databank db = dbdao.findByExample(new Databank(dbname), "id", "reference", "filelink", "parent", "regex", "crawltype", "entries");
		if (db == null)
			new IllegalArgumentException("Databank with name " + dbname + " not found.");

		dbdao.enableFilter(fileFilter);
		dbdao.enableFilter(parentFilter);
		if (comment == null)
			dbdao.enableFilter(commentFilter);
		else
			dbdao.enableFilter(commentFilter.replace("Comment", "ThisComment"), "comment", comment);

		SortedSet<Entry> entries = db.getEntries();
		System.out.println("#" + dbname + " " + fileFilter + " " + parentFilter + " " + commentFilter + ": " + entries.size() + " entries");
		for (Entry entry : entries)
			System.out.println(entry);

		Logger.getLogger(getClass()).debug("list DATABASE " + fileFilter + " " + parentFilter + " " + commentFilter + " \"" + comment + "\": Succes");
	}
}
