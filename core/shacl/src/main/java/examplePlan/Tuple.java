package examplePlan;

import org.eclipse.rdf4j.model.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by havardottestad on 05/07/17.
 */
public class Tuple {

	List<String> line = new ArrayList<>();


	@Override
	public String toString() {
		return "Tuple{" +
			"line=" + Arrays.toString(line.toArray()) +
			'}';
	}
}
