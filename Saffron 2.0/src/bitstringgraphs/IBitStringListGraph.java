/*
 * @version 1.0 06/03/31
 * @copyright 2006 Positronic Software
 */

package bitstringgraphs;

import bits.IBooleanVariable;
import bitstringlists.IBitStringList;
import exceptions.bitstrings.BitStringGraphException;

public interface IBitStringListGraph extends Cloneable
{
	IBitStringList getData();

	IBooleanVariable getData(int i, int j) throws Exception;

	String getName();

	int getNumberOfVertices();

	void setData(IBooleanVariable[][] iBooleanVariables) throws Exception;

	void setData(int i, int j, IBooleanVariable booleanVariable)
			throws Exception;

	void setName(String name) throws BitStringGraphException;

	String toString();
}
