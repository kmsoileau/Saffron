/*
 * @version 1.0 06/03/31
 * @copyright 2006 Positronic Software
 */

package bitstringgraphs;

import bits.IBooleanVariable;
import exceptions.bitstrings.BitStringGraphException;

public interface IBitStringGraph extends Cloneable
{
	boolean areConnected(int i, int j);

	void biconnect(int i, int j);

	IBooleanVariable[][] getData();

	IBooleanVariable getData(int i, int j);

	String getName();

	void monoconnect(int i, int j);

	void setData(IBooleanVariable[][] iBooleanVariables);

	void setData(int i, int j, IBooleanVariable booleanVariable);

	void setName(String name) throws BitStringGraphException;

	int size();

	@Override
	String toString();
}
