package naturalnumbers;

import naturalnumbers.exceptions.NumberException;

/**
 * A class which represents a number by wrapping a
 * <code>boolean[] bitArray</code>, a <code>String
 * name</code> and a <code>boolean overflow</code>.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since May 4, 2005
 */
public class Number implements INumber
{

	private boolean[] bitArray;
	private String name;
	private boolean overflow;

	public Number(boolean[] bitArray, boolean overflow) throws NumberException
	{
		this("", bitArray, overflow);
		this.setName(super.toString());
	}

	public Number(long n) throws NumberException
	{
		this("", n);
		this.setName(super.toString());
	}

	public Number(Number n, int padsize) throws NumberException
	{
		this("", n, padsize);
		this.setName(super.toString());
	}

	public Number(String name, boolean[] bitArray, boolean overflow)
			throws NumberException
	{
		if (bitArray == null)
			throw new NumberException(
					"A null bitArray was passed to a constructor.");
		if (bitArray.length > NaturalNumber.getLength())
			throw new NumberException(
					"Attempted to use too large of a number in Number constructor.");
		this.setBitArray(bitArray);
		this.setOverflow(overflow);
		this.setName(name);
	}

	public Number(String name, long n) throws NumberException
	{
		if (n < 0)
			throw new NumberException(
					"A negative long was passed to a constructor.");

		if (n == 0)
		{
			this.setBitArray(new boolean[1]);
			this.setOverflow(false);
			this.setName(name);
		}
		if (n > 0)
		{
			int size = (int) (Math.log(n) / Math.log(2)) + 1;
			if (size > NaturalNumber.getLength())
				throw new NumberException(
						"Attempted to use too large of a number in Number constructor.");

			this.setBitArray(new boolean[size]);
			this.setOverflow(false);
			int count = 0;
			long current = n;
			while (current > 0)
			{
				long bit = current - 2 * (current / 2);
				if (bit == 1)
					this.setBit(count, true);
				else
					this.setBit(count, false);
				current = current / 2;
				count++;
			}
			this.setName(name);
		}
	}

	public Number(String name, Number n, int padsize) throws NumberException
	{
		if (n == null)
			throw new NumberException(
					"A null Number was passed to a constructor.");
		if (padsize < 0)
			throw new NumberException(
					"A negative int was passed to a constructor.");
		if (n.getSize() < padsize)
		{
			this.setBitArray(new boolean[padsize]);
			for (int i = 0; i < n.getSize(); i++)
				this.setBit(i, n.getBit(i));
		}
		else
		{
			if (n.getSize() > NaturalNumber.getLength())
				throw new NumberException(
						"Attempted to use too large of a number in Number constructor.");
			this.setBitArray(new boolean[n.getSize()]);
			for (int i = 0; i < n.getSize(); i++)
				this.setBit(i, n.getBit(i));
		}
		this.setName(name);
	}

	public boolean equals(Number n)
	{
		if (n == null)
			return false; // this is never equal to null.
		if (!(n instanceof Number))
			return false;
		else
			return (this.getName().compareTo(n.getName()) == 0);
	}

	@Override
	public boolean getBit(int i) throws NumberException
	{
		if (i < 0 || i > this.getSize() - 1)
			throw new NumberException(
					"Attempted to index out of range in method getBit.");
		return this.getBitArray()[i];
	}

	@Override
	public boolean[] getBitArray()
	{
		return bitArray;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public int getSize()
	{
		return this.bitArray.length;
	}

	public boolean isOverflow()
	{
		return overflow;
	}

	@Override
	public void setBit(int i, boolean b) throws NumberException
	{
		if (i < 0 || i > this.getSize() - 1)
			throw new NumberException(
					"Attempted to index out of range in method setBit.");
		this.getBitArray()[i] = b;
	}

	@Override
	public void setBitArray(boolean[] bitArray) throws NumberException
	{
		if (bitArray == null)
			throw new NumberException(
					"A null boolean[] was passed to a constructor.");
		this.bitArray = bitArray;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public void setOverflow(boolean overflow)
	{
		this.overflow = overflow;
	}

	@Override
	public String toString()
	{
		String res = "<";
		res += (name + "=");
		for (int i = this.getSize() - 1; i >= 0; i--)
		{
			try
			{
				if (this.getBit(i))
					res += "1";
				else
					res += "0";
			}
			catch (NumberException e)
			{
				e.printStackTrace();
			}
		}
		res += ">";
		return res;
	}
}