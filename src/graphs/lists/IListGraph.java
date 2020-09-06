/*
 * @version 1.0 06/03/31
 * @copyright 2006 Positronic Software
 */

package graphs.lists;

import bits.IBooleanVariable;
import bits.strings.lists.IBitStringList;

public interface IListGraph extends Cloneable
{
	IBitStringList getData();

	IBooleanVariable getData(int i, int j) throws Exception;

	String getName();

	int getNumberOfVertices();

	void setData(IBooleanVariable[][] iBooleanVariables) throws Exception;

	void setData(int i, int j, IBooleanVariable booleanVariable)
			throws Exception;

	void setName(String name) throws Exception;

	@Override
	String toString();
}
