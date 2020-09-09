/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 6, 2019
 */
package showcase.rectanglepacking;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.rectangles.INaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;
import naturalnumbers.rectangles.packing.RectanglePacker;
import utility.IntegerPair;

/**
 * 
 *
 */
public class RectanglePackerDemo extends Problem implements IProblem
{
	private static boolean decide(INaturalNumberRectangle rect, int row, int col)
	{
		return rect.getAltitude().getLower().getValue() <= row && row <= rect.getAltitude().getUpper().getValue()
				&& rect.getBase().getLower().getValue() <= col && col <= rect.getBase().getUpper().getValue();
	}

	public static void main(String[] args) throws Exception
	{

		/**
		 * Set Java variables:
		 */

		int columns = 8;
		int rows = 8;

		IntegerPair[] data = new IntegerPair[]
		{ new IntegerPair(3, 3), new IntegerPair(2, 4), new IntegerPair(3, 3), new IntegerPair(3, 4),
				new IntegerPair(2, 2), new IntegerPair(2, 2), new IntegerPair(4, 2), new IntegerPair(2, 2) };

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		INaturalNumberRectangle enclosure = new NaturalNumberRectangle();
		INaturalNumberRectangle[] shiftedRectangle = new NaturalNumberRectangle[data.length];

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p1 = new RectanglePacker(data, enclosure, shiftedRectangle);

		IProblem p2 = new NaturalNumberRectangleFixer(enclosure, 0, 0, columns, rows);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(p1, p2);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.print("\n");
			for (int row = rows - 1; row >= 0; row--)
			{
				String ret = "";
				for (int col = 0; col <= columns - 1; col++)
				{
					if (decide(shiftedRectangle[0], row, col))
					{
						ret += " A";
						continue;
					}
					if (decide(shiftedRectangle[1], row, col))
					{
						ret += " B";
						continue;
					}
					if (decide(shiftedRectangle[2], row, col))
					{
						ret += " C";
						continue;
					}
					if (decide(shiftedRectangle[3], row, col))
					{
						ret += " D";
						continue;
					}
					if (decide(shiftedRectangle[4], row, col))
					{
						ret += " E";
						continue;
					}
					if (decide(shiftedRectangle[5], row, col))
					{
						ret += " F";
						continue;
					}
					if (decide(shiftedRectangle[6], row, col))
					{
						ret += " G";
						continue;
					}
					if (decide(shiftedRectangle[7], row, col))
					{
						ret += " H";
						continue;
					}
					ret += " +";
				}
				System.out.println(ret);
			}
		}
		else
			System.out.println("No solution.");
	}
}
