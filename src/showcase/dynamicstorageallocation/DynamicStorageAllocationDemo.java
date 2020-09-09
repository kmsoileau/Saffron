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
package showcase.dynamicstorageallocation;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.rectangles.INaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;
import naturalnumbers.rectangles.packing.RectanglePacker;

class Allocateable
{
	private int arrivalTime;
	private int departureTime;
	private int size;

	public Allocateable(int size, int arrivalTime, int departureTime)
	{
		super();
		this.size = size;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	public int getArrivalTime()
	{
		return arrivalTime;
	}

	public int getDepartureTime()
	{
		return departureTime;
	}

	public int getSize()
	{
		return size;
	}

	public void setArrivalTime(int arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}

	public void setDepartureTime(int departureTime)
	{
		this.departureTime = departureTime;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public String toString(String marker)
	{
		String ret = "";

		for (int row = departureTime; row >= arrivalTime; row--)
		{
			ret += "\n" + row + ":";
			for (int i = 0; i < size; i++)
			{
				ret += marker;
			}
		}
		return ret;
	}
}

/**
 * 
 *
 */
public class DynamicStorageAllocationDemo extends Problem implements IProblem
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

		int memoryUnits = 8;
		int timeSlots = 8;

		// new Allocateable(size, arrivalTime, departureTime)
		Allocateable[] data = new Allocateable[]
		{ new Allocateable(2, 2, 3), new Allocateable(3, 3, 4), new Allocateable(1, 1, 5), new Allocateable(5, 5, 7),
				new Allocateable(2, 0, 7) };

		int dataBlocks = data.length;

		System.out.println("\nCONSOLE OUTPUT");
		System.out.println("\nJOBS");
		for (int i = 0; i < dataBlocks; i++)
			System.out.println(data[i].toString("" + i));

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		INaturalNumberRectangle enclosure = new NaturalNumberRectangle();

		INaturalNumberRectangle[] rectangle = new NaturalNumberRectangle[dataBlocks];
		for (int i = 0; i < dataBlocks; i++)
			rectangle[i] = new NaturalNumberRectangle();

		INaturalNumberRectangle[] shiftedRectangle = new NaturalNumberRectangle[dataBlocks];

		INaturalNumber absdx[] = new INaturalNumber[dataBlocks];
		INaturalNumber absdy[] = new INaturalNumber[dataBlocks];
		for (int i = 0; i < dataBlocks; i++)
		{
			absdx[i] = new NaturalNumber();
			absdy[i] = new NaturalNumber();
		}

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem[] rectangleFixers = new IProblem[dataBlocks];
		for (int i = 0; i < dataBlocks; i++)
			rectangleFixers[i] = new NaturalNumberRectangleFixer(rectangle[i], 0, data[i].getArrivalTime(),
					data[i].getSize(), data[i].getDepartureTime() + 1 - data[i].getArrivalTime());

		IProblem p1 = new Conjunction(rectangleFixers);

		IProblem p2 = new RectanglePacker(rectangle, enclosure, shiftedRectangle, absdx, absdy);

		IProblem p3 = new NaturalNumberRectangleFixer(enclosure, 0, 0, memoryUnits, timeSlots);

		IProblem[] p4Array = new IProblem[dataBlocks];
		for (int i = 0; i < dataBlocks; i++)
			p4Array[i] = new NaturalNumberFixer(absdy[i], 0);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(p1, p2, p3, new Conjunction(p4Array));

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage message = problem.findModel(Problem.defaultSolver());
		if (message.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(message.getLiterals());

			System.out.println("\nSOLUTION");

			// for (int i = 0; i < dataBlocks; i++)
			// {
			// System.out.println("rectangle[" + i + "]=" + rectangle[i]);
			// System.out.println("shiftedRectangle[" + i + "]="
			// + shiftedRectangle[i]);
			// System.out.println("absdx[" + i + "]=" + absdx[i]);
			// System.out.println("absdy[" + i + "]=" + absdy[i]);
			// }

			System.out.print("\n");
			for (int row = timeSlots - 1; row >= 0; row--)
			{
				String ret = "";
				a: for (int col = 0; col <= memoryUnits - 1; col++)
				{
					for (int j = 0; j < dataBlocks; j++)
					{
						if (decide(shiftedRectangle[j], row, col))
						{
							ret += j + " ";
							continue a;
						}
					}
					ret += "+ ";
				}
				System.out.println(ret);
			}
		}
		else
			System.out.println("No solution.");
	}
}
