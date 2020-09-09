/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 9, 2019
 */
package demos.graphs.generalized.directed;

import java.util.HashMap;

import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import graphs.generalized.DirectedGraph;
import graphs.generalized.IDirectedGraph;

/**
 * 
 *
 */
public class Demo2 extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		int[][] data = new int[][]
		{
				{ 0, 1 },
				{ 0, 3 },
				{ 1, 1 },
				{ 1, 2 },
				{ 1, 4 },
				{ 2, 4 },
				{ 2, 5 },
				{ 3, 4 },
				{ 4, 6 },
				{ 5, 7 },
				{ 6, 5 },
				{ 6, 7 } };

		IDirectedGraph graph = new DirectedGraph("G", data);

		System.out.println(graph);

		HashMap<Integer, INaturalNumber> verts = graph.getVertices();
		INaturalNumber n0 = verts.get(2);
		INaturalNumber n1 = verts.get(4);

		System.out.println("Disconnect:");
		graph.disconnect(n0, n1);
		System.out.println(graph);
	}
}
