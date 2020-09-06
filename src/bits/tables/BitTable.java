/*
 * BitTable.java	1.0 05/04/28
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.tables;

import bits.IBitString;
import bits.IBooleanVariable;
import bits.strings.BitString;

public class BitTable implements IBitTable
{
	/*
	 * public static void interpret(List<IBooleanLiteral> list) { if(list!=null)
	 * for(int i=0;i<list.size();i++) list.get(i).load(); }
	 */

	private IBooleanVariable[][] data;
	private String name;

	public BitTable(IBooleanVariable[][] data)
	{
		this("", data);
	}

	public BitTable(String name, IBooleanVariable[][] data)
	{
		this.setName(name);
		this.setData(data);
	}

	@Override
	public IBooleanVariable getBooleanVariable(int i, int j)
	{
		return this.getData()[i][j];
	}

	public IBooleanVariable[][] getData()
	{
		return data;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public boolean isSameSizeAs(IBitTable b)
	{
		if (this.numberColumns() != b.numberColumns())
			return false;
		if (this.numberRows() != b.numberRows())
			return false;
		return true;
	}

	@Override
	public int numberColumns()
	{
		return this.getData()[0].length;
	}

	@Override
	public int numberRows()
	{
		return this.getData().length;
	}

	@Override
	public void setBooleanVariable(int i, int j, IBooleanVariable ib)
	{
		this.getData()[i][j] = ib;
	}

	public void setData(IBooleanVariable[][] data)
	{
		this.data = data;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public IBitString[] toBitStringArray() throws Exception
	{
		IBitString[] res = new IBitString[numberRows()];
		for (int i = 0; i < res.length; i++)
			res[i] = new BitString(this.getName() + "_" + i, this.getData()[i]);
		return res;
	}

	@Override
	public String toString()
	{
		String ret = "";
		for (int row = 0; row < numberRows(); row++)
		{
			for (int column = 0; column < this.getData()[0].length; column++)
				if (this.getBooleanVariable(row, column).getValue())
					ret += "T";
				else
					ret += "F";
			ret += "\n";
		}
		return ret;
	}
}