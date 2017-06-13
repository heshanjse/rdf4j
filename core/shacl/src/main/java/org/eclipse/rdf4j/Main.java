package org.eclipse.rdf4j;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.sail.SailRepositoryConnection;
import org.eclipse.rdf4j.repository.util.Repositories;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;
import org.eclipse.rdf4j.sail.NotifyingSailConnection;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.eclipse.rdf4j.validation.ShaclSail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {


	public static void main(String[] args) throws IOException {


		SailRepository shacl = new SailRepository(new MemoryStore());
		shacl.initialize();

		try (SailRepositoryConnection connection1 = shacl.getConnection()) {
			RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);
			rdfParser.setRDFHandler(new StatementCollector(){
				@Override
				public void handleStatement(Statement st) {
					connection1.add(st);
				}
			});

			rdfParser.parse(new FileInputStream("shacl.ttl"), "");
		}


		ShaclSail shaclSail = new ShaclSail(new MemoryStore(), shacl);
		shaclSail.initialize();

		try (NotifyingSailConnection connection = shaclSail.getConnection()) {
			connection.begin();
			connection.addStatement(RDFS.CLASS, RDF.TYPE, RDFS.RESOURCE);
			connection.commit();
		}
		try (NotifyingSailConnection connection = shaclSail.getConnection()) {
			connection.begin();
			connection.removeStatements(RDFS.CLASS, RDF.TYPE, RDFS.RESOURCE);
			connection.commit();
		}
	}
}
