/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 27, 2019
 */
package integers.lists;

import java.util.Arrays;

import integers.IInteger;
import integers.Integer;
import integers.lists.exceptions.IntegerListException;

/**
 * 
 *
 */
public class IntegerList implements IIntegerList
{
	private static int iILCount;

	private IInteger[] arrayI;
	private String name;

	public IntegerList(IInteger[] data) throws Exception
	{
		this("IntegerList-" + iILCount++, data);
	}

	public IntegerList(long[] data) throws Exception
	{
		this("IntegerList-" + iILCount++, data);
	}

	public IntegerList(String name, IInteger[] data) throws Exception
	{
		if (name == null)
			throw new IntegerListException("Passed null String to constructor.");
		if (data == null)
			throw new IntegerListException(
					"Passed null IInteger[] to constructor.");
		this.name = name;
		this.arrayI = data;
	}

	public IntegerList(String name, long[] data) throws Exception
	{
		if (name == null)
			throw new IntegerListException("Passed null String to constructor.");
		if (data == null)
			throw new IntegerListException("Passed null long[] to constructor.");

		this.name = name;

		this.arrayI = new IInteger[data.length];
		for (int i = 0; i < data.length; i++)
		{
			this.arrayI[i] = new Integer(data[i]);
		}
	}

	@Override
	public IInteger getInteger(int i) throws Exception
	{
		return this.arrayI[i];
	}

	@Override
	public IInteger[] getIntegerArray()
	{
		return this.arrayI;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public String toString()
	{
		return "IntegerList [arrayI=" + Arrays.toString(arrayI) + ", name="
				+ name + "]";
	}
}
