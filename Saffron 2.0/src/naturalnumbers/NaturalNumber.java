/**
 * A BitString representing the binary form of a natural number (i.e., a
 * positive integer).
 *
 * @author  Kerry Michael Soileau
 * <pre>
 * Copyright 2004-2005 Positronic Software.
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre>
 * @version 1.0, 05/05/04
 * @see BitString
 * @see BitStringException
 * @see INaturalNumber
 * @see positronic.util.Number
 */

package naturalnumbers;

import naturalnumbers.exceptions.NaturalNumberException;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bitstrings.BitString;
import bitstrings.exceptions.BitStringException;

@SuppressWarnings("unused")
public class NaturalNumber extends BitString implements INaturalNumber
{
	private static int bits = INaturalNumber.DEFAULTLENGTH;
	private static boolean hasBeenSet = false;
	private static long nNCount;

	public static int getLength()
	{
		if (!hasBeenSet)
		{
			bits = INaturalNumber.DEFAULTLENGTH;
			hasBeenSet = true;
		}
		return bits;
	}

	/*
	 * Call this method to set the bit size of NaturalNumbers so that they can
	 * accommodate natural numbers up to a size specified by you.
	 */
	public static void setLargestNaturalNumber(long nn)
			throws NaturalNumberException
	{
		if (nn < 1)
			throw new NaturalNumberException("Error: Attempted to use method "
					+ "setLargestNaturalNumber with an argument less than 1.");
		setLength((int) Math.ceil(Math.log(nn + 1d) / Math.log(2)));
	}

	/*
	 * Call this method to set explicitly the bit size of NaturalNumbers.
	 */
	public static void setLength(int size) throws NaturalNumberException
	{
		if (!hasBeenSet)
		{
			bits = size;
			hasBeenSet = true;
		}
		else
			throw (new NaturalNumberException(
					"Attempted to change a previously set bit length for the class NaturalNumber."));
	}

	public NaturalNumber() throws Exception
	{
		this("NaturalNumber-" + nNCount++);
	}

	public NaturalNumber(IBitString data) throws Exception
	{
		this("NaturalNumber-" + nNCount++, data);
	}

	public NaturalNumber(long n) throws Exception
	{
		this("NaturalNumber-" + nNCount++, n);
	}

	public NaturalNumber(String name) throws Exception
	{
		super(name, new boolean[NaturalNumber.getLength()]);
	}

	private NaturalNumber(String name, boolean[] data) throws Exception
	{
		this(name, new BitString(name, data));
	}

	public NaturalNumber(String name, IBitString data) throws Exception
	{
		super(name, data.getBVArray(NaturalNumber.getLength()));
	}

	public NaturalNumber(String name, long n) throws Exception
	{
		this(name, new Number(new Number(n), NaturalNumber.getLength())
				.getBitArray());
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null)
			return false; // this is never equal to null.
		if (!(o instanceof NaturalNumber))
			return false;
		else
			return (this.getName().compareTo(((NaturalNumber) o).getName())) == 0;
	}

	@Override
	public String getName()
	{
		return super.getName();
	}

	@Override
	public void setName(String s) throws Exception
	{
		super.setName(s);
	}

	public IBooleanVariable[] toBVArray() throws Exception
	{
		return super.getBVArray();
	}

	@Override
	public long toDecimal()
	{
		long total = 0L;
		for (int i = getLength() - 1; i >= 0; i--)
		{
			IBooleanVariable ib = null;
			try
			{
				ib = this.getBooleanVariable(i);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			if (ib.getValue())
				total = 2 * total + 1;
			else
				total = 2 * total;
		}
		return total;
	}

	@Override
	public String toString()
	{
		return "" + this.toDecimal();
	}
}
