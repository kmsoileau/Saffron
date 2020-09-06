package naturalnumbers.lists;

import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import naturalnumbers.NaturalNumber;
import naturalnumbers.Number;
import naturalnumbers.lists.exceptions.NaturalNumberListException;

/**
 * Copyright (c) 2005 Positronic Software
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NaturalNumberList extends BitStringList implements
		INaturalNumberList
{
	private static int nNLCount;
	private INaturalNumber[] arrayN;
	private String name;

	public NaturalNumberList(INaturalNumber[] data) throws Exception
	{
		this("NaturalNumberList-" + nNLCount++, data);
	}

	public NaturalNumberList(int n) throws Exception
	{
		this("NaturalNumberList-" + nNLCount++, n);
	}

	public NaturalNumberList(long[] data) throws Exception
	{
		this("NaturalNumberList-" + nNLCount++, data);
	}

	public NaturalNumberList(String name, INaturalNumber[] data)
			throws Exception
	{
		if (name == null)
			throw new NaturalNumberListException(
					"Passed null String to constructor.");
		if (data == null)
			throw new NaturalNumberListException(
					"Passed null INaturalNumber[] to constructor.");
		this.name = name;
		this.arrayN = new INaturalNumber[data.length];
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] == null)
				continue;

			IBooleanVariable[] ary = data[i].getBVArray();
			IBitString b = new BitString(name + "_" + i, ary);
			this.arrayN[i] = new NaturalNumber(name + "_" + i, b);
		}
	}

	public NaturalNumberList(String name, int n) throws Exception
	{
		this.name = name;
		this.arrayN = new INaturalNumber[n];
		for (int i = 0; i < this.arrayN.length; i++)
			this.arrayN[i] = new NaturalNumber();
	}

	public NaturalNumberList(String name, long[] data) throws Exception
	{
		if (name == null)
			throw new NaturalNumberListException(
					"Passed null String to constructor.");
		if (data == null)
			throw new NaturalNumberListException(
					"Passed null long[] to constructor.");

		this.name = name;
		this.arrayN = new INaturalNumber[data.length];
		for (int i = 0; i < this.arrayN.length; i++)
		{
			// setNaturalNumber(i, data[i]);
			Number n = new Number(name, data[i]);
			n = new Number(n, NaturalNumber.getLength());
			this.arrayN[i] = new NaturalNumber(name + "_" + i, new BitString(
					n.getBitArray()));
		}
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public INaturalNumber getNaturalNumber(int i)
			throws NaturalNumberListException
	{
		if (i < 0 || i > this.size() - 1)
			throw new NaturalNumberListException(
					"Attempted to index out of range in method getNaturalNumber.");
		return this.arrayN[i];
	}

	@Override
	public INaturalNumber[] getNaturalNumberArray()
	{
		return this.arrayN;
	}

	@Override
	public void setName(String name) throws NaturalNumberListException
	{
		if (name == null)
			throw new NaturalNumberListException(
					"Passed a null String to method setName.");

		this.name = name;
	}

	public void setNaturalNumber(int i, INaturalNumber naturalNumber)
			throws NaturalNumberListException
	{
		if (i < 0 || i > this.size() - 1)
			throw new NaturalNumberListException(
					"Attempted to index out of range in method getNaturalNumber.");
		if (naturalNumber == null)
			throw new NaturalNumberListException(
					"Passed a null INaturalNumber to method setNaturalNumber.");

		this.arrayN[i] = naturalNumber;
	}

	public void setNaturalNumber(int index, long data) throws Exception
	{
		if (index < 0 || index > this.size())
			throw new NaturalNumberListException(
					"Attempted to index out of range.");
		if (data < 0L)
			throw new NaturalNumberListException(
					"Passed negative long to constructor.");

		Number n = new Number(this.getName(), data);
		n = new Number(n, NaturalNumber.getLength());
		boolean[] bool = n.getBitArray();
		IBitString ib = new BitString(name, bool);
		this.arrayN[index] = new NaturalNumber(this.getName() + "_" + index, ib);
	}

	@Override
	public int size()
	{
		return this.arrayN.length;
	}

	@Override
	public String toString()
	{
		String res = "[";
		if (this.size() > 0)
		{
			try
			{
				res += this.getNaturalNumber(0).toString();
			}
			catch (NaturalNumberListException e)
			{
				e.printStackTrace();
			}
			for (int i = 1; i < this.size(); i++)
				try
				{
					res += "," + this.getNaturalNumber(i);
				}
				catch (NaturalNumberListException e)
				{
					e.printStackTrace();
				}
		}
		return res + "]";
	}
}