package org.eclipse.rdf4j.AST;

import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.repository.sail.SailRepositoryConnection;
import org.eclipse.rdf4j.vocabulary.SH;

/**
 * Created by heshanjayasinghe on 6/10/17.
 */

/*


[] a sh:Shape;

    sh:property [
        sh:path ex:ssn;
        sh:maxCount 1;
        sh:datatype xsd:string;
        sh:pattern "^\\d{3}-\\d{2}-\\d{4}$"

    ], [

        sh:path ex:worksFor;
        sh:class ex:Company;
        sh:nodeKind sh:IRI;


    ].


 */


class TEMP{

    static ValueFactory vf = SimpleValueFactory.getInstance();

    public static void main(String[] args) {


        MaxCountPropertyShape maxCountPropertyShapeExSsn = new MaxCountPropertyShape(null, null);
        maxCountPropertyShapeExSsn.maxCount = 1;
        Path path = new Path(null, null);
        path.path = vf.createIRI("http://example.org/ssn");
        maxCountPropertyShapeExSsn.path = path;


        DatatypePropertyShape datatypePropertyShape = new DatatypePropertyShape(null, null);
        datatypePropertyShape.datatype = vf.createIRI("http://..../string");
        Path path2 = new Path(null, null);
        path2.path = vf.createIRI("http://example.org/ssn");
        datatypePropertyShape.path = path2;




    }



}




public class MinCountPropertyShape extends PathPropertyShape{

    Integer minCount;

    public MinCountPropertyShape(Resource next, SailRepositoryConnection connection) {
        super(next,connection);
        ValueFactory vf = connection.getValueFactory();
        minCount = Integer.parseInt(connection.getStatements(next, vf.createIRI(SH.BASE_URI, "minCount"), null, true).next().getObject().stringValue());

    }

    @Override
    public String toString() {
        return "MinCountPropertyShape{" +
                "minCount=" + minCount +
                '}';
    }
}
