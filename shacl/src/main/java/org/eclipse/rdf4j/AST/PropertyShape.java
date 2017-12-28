/*******************************************************************************
 * Copyright (c) 2016 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.rdf4j.AST;

import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.vocabulary.SHACL;
import org.eclipse.rdf4j.plan.PlanNode;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepositoryConnection;
import org.eclipse.rdf4j.validation.ShaclSailConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heshan Jayasinghe
 */
public class PropertyShape implements PlanGenerator {

	Resource id;

	SailRepositoryConnection connection;

	public PropertyShape(Resource id, SailRepositoryConnection connection) {
		this.id = id;
		this.connection = connection;
	}

	@Override
	public PlanNode getPlan(ShaclSailConnection shaclSailConnection, Shape shape) {
		throw new IllegalStateException("Should never get here!!!");
	}

	public static class Factory {

		static List<PropertyShape> ret;

		static List<PropertyShape> getProprtyShapes(Resource ShapeId, SailRepositoryConnection connection) {
			ret = new ArrayList<>();
			System.out.println(ret.size());
			RepositoryResult<Statement> propertyShapeIds = connection.getStatements(ShapeId, SHACL.PROPERTY,
					null);
			while (propertyShapeIds.hasNext()) {
				Resource propertyShapeId = (Resource)propertyShapeIds.next().getObject();
				if (hasMinCount(propertyShapeId, connection)) {
					ret.add(new MinCountPropertyShape(propertyShapeId, connection));
					System.out.println("okkkkkk");
				}
				System.out.println(ret.size());
			}
			return ret;
		}

		private static boolean hasMaxCount(Resource propertyShapeId, SailRepositoryConnection connection,
				List<PropertyShape> ret)
		{
			for (PropertyShape propertyShape : ret) {
				if (propertyShape instanceof MaxCountPropertyShape)
					return true;
			}
			return false;
		}

		private static boolean hasMinCount(Resource id, SailRepositoryConnection connection) {
			if (connection.hasStatement(id, SHACL.MIN_COUNT, null, true)) {
				System.out.println("Has statement");
				return true;
			}
			else {
				return false;
			}
		}
	}
}


