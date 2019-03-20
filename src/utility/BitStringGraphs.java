/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 17, 2019
 */
package utility;

import bitstringgraphs.BitStringGraph;
import bitstringgraphs.IBitStringGraph;

/**
 * 
 *
 */
public class BitStringGraphs
{
	public static IBitStringGraph randomGraph(int numberOfVertices)
			throws Exception
	{
		IBitStringGraph graph = new BitStringGraph(numberOfVertices);
		for (int i = 0; i < numberOfVertices; i++)
			for (int j = i + 1; j < numberOfVertices; j++)
			{
				if (Math.random() > 2. / 3)
				{
					graph.biconnect(i, j);
				}
				else if (Math.random() > 1. / 3)
				{
					graph.biconnect(j, i);
				}
			}
		return graph;
	}
}
