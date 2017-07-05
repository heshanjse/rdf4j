package examplePlan;

import java.util.Iterator;

/**
 * Created by havardottestad on 05/07/17.
 */
public class MAin {

	public static void main(String[] args) {

		Select select = new Select(new DataSource());
		Filter filter = new Filter(select, t -> true);

		Iterator<Tuple> iterator = filter.iterator();

		while(iterator.hasNext()){
			Tuple next = iterator.next();
			System.out.println(next);
		}
	}

}
