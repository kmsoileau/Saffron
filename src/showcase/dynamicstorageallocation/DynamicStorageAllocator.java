/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 7, 2019
 */
package showcase.dynamicstorageallocation;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.rectangles.INaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangleCongruenter;
import naturalnumbers.rectangles.NaturalNumberRectangleContainer;
import naturalnumbers.rectangles.NaturalNumberRectangleDisjointer;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;
import utility.IntegerPair;

/**
 * 
 *
 */
public class DynamicStorageAllocator extends Problem implements IProblem
{

	public DynamicStorageAllocator(IntegerPair[] data, INaturalNumberRectangle enclosure,
			INaturalNumberRectangle[] shiftedRectangle, INaturalNumber[] sigma) throws Exception
	{
		int len = data.length;

		INaturalNumberRectangle[] rectangle = new NaturalNumberRectangle[len];
		for (int i = 0; i < len; i++)
			rectangle[i] = new NaturalNumberRectangle();

		for (int i = 0; i < len; i++)
			sigma[i] = new NaturalNumber();

		for (int i = 0; i < len; i++)
			shiftedRectangle[i] = new NaturalNumberRectangle();

		IProblem[] rectangleFixers = new IProblem[len];
		for (int i = 0; i < len; i++)
			rectangleFixers[i] = new NaturalNumberRectangleFixer(rectangle[i], data[i]);

		IProblem[] rectangleCongruenters = new IProblem[len];

		for (int i = 0; i < len; i++)
			rectangleCongruenters[i] = new NaturalNumberRectangleCongruenter(shiftedRectangle[i], rectangle[i],
					INaturalNumberRectangle.HORIZONTAL, sigma[i]);

		IProblem[] rectangleContainers = new IProblem[len];
		for (int i = 0; i < len; i++)
			rectangleContainers[i] = new NaturalNumberRectangleContainer(shiftedRectangle[i], enclosure);

		this.setClauses(new Conjunction(new Conjunction(rectangleFixers), new Conjunction(rectangleCongruenters),
				new NaturalNumberRectangleDisjointer(shiftedRectangle), new Conjunction(rectangleContainers))
						.getClauses());
	}
}
