package examplePlan;

import java.util.Iterator;

/**
 * Created by havardottestad on 05/07/17.
 */
public class Filter  implements PlanNode{

	private final Condition condition;
	PlanNode above;


	public Filter(PlanNode select, Condition condition) {
		above = select;
		this.condition = condition;

	}

	@Override
	public Iterator<Tuple> iterator() {
		return new Iterator<Tuple>() {

			Iterator<Tuple> aboveIterator = above.iterator();

			Tuple next = null;

			@Override
			public boolean hasNext() {

				if(next == null){
					getNext();
				}

				return next != null;
			}

			private void getNext() {
				while(aboveIterator.hasNext()){
					Tuple tempNExt = aboveIterator.next();
					if(condition.condition(tempNExt)){
						next = tempNExt;
						return;
					}
				}
			}

			@Override
			public Tuple next() {
				if(next == null){
					getNext();
				}

				Tuple tempNext = next;

				next = null;

				return tempNext;
			}
		};
	}

	interface Condition{
		boolean condition(Tuple string);
	}
}
