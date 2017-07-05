package examplePlan;

import java.util.Iterator;

/**
 * Created by havardottestad on 05/07/17.
 */
public class Select implements PlanNode {
	DataSource dataSource;

	public Select(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Iterator<Tuple> iterator() {

		return new Iterator<Tuple>() {

			int counter = 0;

			@Override
			public boolean hasNext() {
				return dataSource.strings.length>counter;
			}

			@Override
			public Tuple next() {

				String string = dataSource.strings[counter];
				counter++;
				Tuple tuple = new Tuple();
				tuple.line.add(string);
				return tuple;
			}
		};
	}
}
