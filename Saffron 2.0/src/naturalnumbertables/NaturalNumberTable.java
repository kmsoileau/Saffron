/*
 * NaturalNumberTable.java	1.1 05/11/08
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package naturalnumbertables;

import bits.IBitString;
import bits.INaturalNumber;
import naturalnumbers.NaturalNumber;
import naturalnumbertables.exceptions.NaturalNumberTableException;

public class NaturalNumberTable implements INaturalNumberTable
{
	/**
	 * A static integer which records the number of
	 * <tt>NaturalNumberTable</code>
	 * 's created without specifying a name.
	 */
	private static int nNTCount;

	private INaturalNumber[][] data;

	private String name;

	public NaturalNumberTable(INaturalNumber[][] data)
			throws NaturalNumberTableException, CloneNotSupportedException
	{
		this("NaturalNumberTable-" + nNTCount++, data);
	}

	public NaturalNumberTable(int rows, int columns) throws Exception
	{
		this("NaturalNumberTable-" + nNTCount++, rows, columns);
	}

	public NaturalNumberTable(String name, INaturalNumber[][] data)
			throws NaturalNumberTableException, CloneNotSupportedException
	{
		if (name == null || "".compareTo(name) == 0)
			throw new NaturalNumberTableException(
					"Null or empty String passed to a constructor.");
		if (data == null)
			throw new NaturalNumberTableException(
					"Null INaturalNumber[][] passed to a constructor.");
		this.name = name;
		this.data = new INaturalNumber[data.length][data[0].length];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				this.data[i][j] = (INaturalNumber) ((IBitString) data[i][j])
						.clone();
	}

	public NaturalNumberTable(String name, int rows, int columns)
			throws Exception
	{
		this.name = name;
		this.data = new INaturalNumber[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				this.data[i][j] = new NaturalNumber(name + "_" + i + "_" + j);
	}

	public String getName()
	{
		return name;
	}

	@Override
	public INaturalNumber getNaturalNumber(int i, int j) throws Exception
	{
		if (i < 0 || j < 0 || i > this.getNumberOfRows() - 1
				|| j > this.getNumberOfColumns() - 1)
			throw new NaturalNumberTableException("Index was out of range.");
		return this.data[i][j];
	}

	@Override
	public int getNumberOfColumns() throws Exception
	{
		if (data[0] == null)
			throw new NaturalNumberTableException(
					"Null data passed to a constructor.");
		return data[0].length;
	}

	@Override
	public int getNumberOfRows()
	{
		return data.length;
	}

	@Override
	public boolean isSameSizeAs(INaturalNumberTable n) throws Exception
	{
		if (this.getNumberOfRows() != n.getNumberOfRows())
			return false;
		if (this.getNumberOfColumns() != n.getNumberOfColumns())
			return false;
		return true;
	}

	public void set(int i, int j, INaturalNumber value) throws Exception
	{
		if (value == null)
			throw new NaturalNumberTableException(
					"Null data passed to a constructor.");
		this.data[i][j] = value;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		String ret = this.getName() + "\n";
		for (int i = 0; i < this.getNumberOfRows(); i++)
		{
			try
			{
				for (int j = 0; j < this.getNumberOfColumns(); j++)
					ret += this.getNaturalNumber(i, j) + " ";
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			ret += "\n";
		}
		return ret;
	}
}