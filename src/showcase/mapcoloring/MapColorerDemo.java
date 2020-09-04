/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 11, 2019
 */
package showcase.mapcoloring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import bits.BooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.IUndirectedGraph;
import graphs.MapColorer;
import graphs.UndirectedGraph;
import naturalnumbers.NaturalNumber;

public class MapColorerDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		int numberOfColors = 3;

		HashMap<String, String[]> map = new HashMap<String, String[]>();
		map.put("Florida", new String[]
		{ "Alabama", "Georgia" });
		map.put("Alabama", new String[]
		{ "Tennessee", "Mississippi" });
		map.put("Georgia", new String[]
		{ "Alabama", "Tennessee", "South Carolina" });
		map.put("Tennessee", new String[]
		{ "Arkansas", "Missouri", "Kentucky", "Virginia", "North Carolina" });

		HashMap<String, Integer> directory = new HashMap<String, Integer>();
		HashMap<Integer, String> yrotcerid = new HashMap<Integer, String>();

		HashSet<String> regions = new HashSet<String>(map.keySet());

		for (String[] ar : map.values())
		{
			regions.addAll(Arrays.asList(ar));
		}

		int numberOfRegions = regions.size();

		int index = 0;
		for (String s : regions)
		{
			directory.put(s, index);
			yrotcerid.put(index, s);
			index++;
		}

		/**
		 * Set globals:
		 */
		NaturalNumber.setLargestNaturalNumber(numberOfColors - 1);

		/**
		 * Create Saffron objects and arrays:
		 */
		IUndirectedGraph skeleton = new UndirectedGraph(numberOfRegions);
		INaturalNumber[] coloring = new INaturalNumber[skeleton.size()];

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		for (String s : map.keySet())
		{
			Integer left = directory.get(s);
			for (String currRight : map.get(s))
			{
				skeleton.connect(left, directory.get(currRight));
			}
		}

		IProblem mapColoringConstraint = new MapColorer(skeleton,
				numberOfColors, coloring);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */
		IProblem problem = mapColoringConstraint;

		/**
		 * Solve the IProblem:
		 */
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("\tSOLUTION");
			System.out.println("----------------+-----");
			System.out.println("State   \t|Color");
			System.out.println("----------------+-----");
			for (int i = 0; i < skeleton.size(); i++)
				System.out.println(yrotcerid.get(i) + " \t|  " + coloring[i]);
		}
		else
			System.out.println("No solution.");
	}
}