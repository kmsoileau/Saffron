package bitstrings;

import java.util.ArrayList;
import java.util.List;

import bits.BooleanVariable;
import bits.IBitString;
import bits.IBooleanVariable;
import exceptions.bitstrings.BitStringException;

/**
 * An array of fixed length of IBooleanVariables.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.2
 * @since 2005/10/27
 */
public class BitString implements IBitString
{
	private static long bSCount;
	private ArrayList<IBooleanVariable> data;
	private String name;

	public BitString() throws Exception
	{
		this("BitString-" + bSCount++, new boolean[0]);
	}

	public BitString(boolean[] data) throws Exception
	{
		this("BitString-" + bSCount++, data);
	}

	public BitString(char[] data) throws Exception
	{
		this("BitString-" + bSCount++, data);
	}

	public BitString(IBitString b) throws Exception
	{
		this(b.getName(), b.getBVArray());
	}

	public BitString(IBooleanVariable[] data) throws Exception
	{
		this("BitString-" + bSCount++, data);
	}

	public BitString(int bits) throws Exception
	{
		this("BitString-" + bSCount++, new boolean[bits]);
	}

	public BitString(List<IBooleanVariable> reduced) throws Exception
	{
		this(reduced.toArray(new IBooleanVariable[0]));
	}

	public BitString(String data) throws Exception
	{
		this("BitString-" + bSCount++, data);
	}

	public BitString(String name, boolean[] data) throws Exception
	{
		if (data == null)
			throw new BitStringException(
					"Null data passed to BitString constructor.");
		if ("".compareTo(name) == 0)
			throw new BitStringException(
					"Null name passed to BitString constructor.");
		this.setName(name);
		this.setBVData(new IBooleanVariable[data.length]);
		for (int i = 0; i < data.length; i++)
			this.setBooleanVariable(i,
					BooleanVariable.getBooleanVariable(name + "_" + i, data[i]));
	}

	public BitString(String name, char[] data) throws Exception
	{
		if (name == null)
			throw new BitStringException("Null String passed to constructor.");
		if (data == null)
			throw new BitStringException("Null char[] passed to constructor.");
		this.setName(name);
		this.data = new ArrayList<IBooleanVariable>();
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] != '0' && data[i] != '1')
				throw new BitStringException(
						"Construction failed on bad char[] data.");
			if (data[i] == '0')
				this.setBooleanVariable(i, BooleanVariable.getBooleanVariable(
						name + "_" + i, false));
			if (data[i] == '1')
				this.setBooleanVariable(i, BooleanVariable.getBooleanVariable(
						name + "_" + i, true));
		}
	}

	public BitString(String name, IBooleanVariable[] data) throws Exception
	{
		if (data == null)
			throw new BitStringException("Null data passed to constructor.");
		if (name == null || "".compareTo(name) == 0)
			throw new BitStringException("Null name passed to constructor.");
		this.setName(name);
		this.setBVData(new IBooleanVariable[data.length]);
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] == null)
				data[i] = BooleanVariable.getBooleanVariable();
			this.setBooleanVariable(i, data[i]);
		}
	}

	/*
	 * public BitString(String name, IBooleanVariable[] data) throws Exception {
	 * if (data == null) throw new
	 * BitStringException("Null data passed to constructor."); if (name == null
	 * || "".compareTo(name) == 0) throw new
	 * BitStringException("Null name passed to constructor.");
	 * this.setName(name); this.setBVData(new IBooleanVariable[data.length]);
	 * for (int i = 0; i < data.length; i++) { if (data[i] == null) data[i] =
	 * BooleanVariable.getBooleanVariable(); this.setBooleanVariable( i,
	 * BooleanVariable.getBooleanVariable(name + "_" + i, data[i].getValue()));
	 * } }
	 */

	public BitString(String name, int bits) throws Exception
	{
		this(name, new boolean[bits]);
	}

	public BitString(String name, String data) throws Exception
	{
		this(name, data.toCharArray());
	}

	@Override
	public List<IBooleanVariable> asList()
	{
		return this.data;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		try
		{
			IBitString b = new BitString(this.getName(), this.getBVArray());
			return b;
		}
		catch (Exception err)
		{
			if (err instanceof CloneNotSupportedException)
				throw (CloneNotSupportedException) err;
			else
			{
				System.err
						.println("Exception: Attempt to clone BitString failed.");
				return null;
			}
		}
	}

	/*
	 * public boolean equals(Object anObject) { if (anObject == null) return
	 * false; // Nothing is equal to a null Object. if (!(anObject instanceof
	 * IBitString)) return false; if (!this.data.containsAll(((IBitString)
	 * anObject).asList())) return false; if (!((IBitString)
	 * anObject).asList().containsAll(this.asList())) return false; return true;
	 * }
	 */

	@Override
	public IBooleanVariable getBooleanVariable(int i) throws Exception
	{
		if (i < 0 || this.size() - 1 < i)
			throw new BitStringException(
					"Attempted to use getBooleanVariable outside range.");

		return this.asList().get(i);
	}

	@Override
	public IBooleanVariable[] getBVArray() throws Exception
	{
		return getBVArray(this.size());
	}

	@Override
	public IBooleanVariable[] getBVArray(int n) throws Exception
	{
		if (n < 1)
			throw new BitStringException(
					"Passed zero or a negative int to getBVArray method.");

		IBooleanVariable[] b = new IBooleanVariable[n];
		for (int i = 0; i < this.size() && i < n; i++)
			b[i] = this.getBooleanVariable(i);
		for (int i = this.size(); i < n; i++)
			b[i] = BooleanVariable.getBooleanVariable();
		return b;
	}

	/*
	 * public IBooleanVariable[] getBVData() { return
	 * (IBooleanVariable[])this.asList().toArray(new IBooleanVariable[0]); }
	 */

	@Override
	public String getName()
	{
		return this.name;
	}

	public int index(IBooleanVariable b) throws Exception
	{
		if (b == null)
			throw new BitStringException(
					"Null IBooleanVariable was passed to index method.");

		for (int i = 0; i < this.size(); i++)
			if (this.getBooleanVariable(i).equals(b))
				return i;
		return -1;
	}

	@Override
	public void setBooleanVariable(int i, IBooleanVariable ib) throws Exception
	{
		if (i < 0)
			throw new BitStringException(
					"Attempted to use setBooleanVariable outside range.");
		if (ib == null)
			throw new BitStringException(
					"Null IBooleanVariable passed to constructor.");
		ArrayList<IBooleanVariable> a = (ArrayList<IBooleanVariable>) this
				.asList();
		while (a.size() <= i)
			a.add(BooleanVariable.getBooleanVariable(this.getName() + "_" + i));
		a.set(i, ib);
	}

	public void setBVData(IBooleanVariable[] data) throws Exception
	{
		if (data == null)
			throw new BitStringException(
					"Passed null String to setBVData method.");
		this.data = new ArrayList<IBooleanVariable>(data.length);
		for (int i = 0; i < data.length; i++)
			this.asList().add(data[i]);
	}

	/*
	 * public void setDataList(List a) throws Exception { if(a==null) throw new
	 * BitStringException("Passed null List to setDataList method.");
	 * this.data=(ArrayList)a; }
	 */

	@Override
	public void setName(String name) throws Exception
	{
		if (name == null)
			throw new BitStringException(
					"Passed null String to setName method.");

		this.name = name;
	}

	@Override
	public int size()
	{
		return this.asList().size();
	}

	@Override
	public String toBits()
	{
		String ret = "";
		for (int column = 0; column < this.size(); column++)
			try
			{
				if (this.getBooleanVariable(column).getValue())
					ret += "1";
				else
					ret += "0";
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return ret;
	}

	public boolean[] toBooleanArray() throws Exception
	{
		IBooleanVariable[] a = this.getBVArray();
		boolean[] res = new boolean[a.length];
		for (int i = 0; i < res.length; i++)
			res[i] = a[i].getValue();
		return res;
	}

	public IBooleanVariable[] toBooleanVariableArray()
	{
		return this.data.toArray(new IBooleanVariable[0]);
	}

	@Override
	public String toString()
	{
		String ret = "";
		for (int column = 0; column < this.size(); column++)
			try
			{
				if (this.getBooleanVariable(column).getValue())
					ret += "T";
				else
					ret += "F";
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return ret;
	}
}