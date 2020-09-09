package bits.strings.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import bits.BooleanVariable;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.strings.BitString;
import bits.strings.lists.exceptions.BitStringListException;
//import bitstrings.ArrayListSet;

/**
 * A wrapper class which encloses an
 * <code>ArrayListSet&lt;IBitString&gt; listData</code> and a
 * <code>String name</code> .
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/28
 */
public class BitStringList implements IBitStringList
{
	private static int bSLCount;

	public static IBitStringList add(IBitStringList first, IBitStringList second) throws Exception
	{
		return BitStringList.add("BitStringList-" + bSLCount++, first, second);
	}

	public static IBitStringList add(String name, IBitStringList first, IBitStringList second) throws Exception
	{
		if (name == null)
			throw new BitStringListException("Passed null String to constructor.");
		if (first == null || second == null)
			throw new BitStringListException("Passed null String to constructor.");

		Collection<IBitString> a = first.toList();
		Collection<IBitString> b = second.toList();
		a.addAll(b);
		IBitString[] newArray = a.toArray(new IBitString[0]);
		IBitStringList res = new BitStringList(name, newArray);
		return res;
	}

	private ArrayList<IBitString> listData;
	private String name;

	public BitStringList() throws Exception
	{
		this("BitStringList-" + bSLCount++, 0, 0);
	}

	public BitStringList(boolean[][] bdata) throws Exception
	{
		this("BitStringList-" + bSLCount++, bdata);
	}

	public BitStringList(IBitString[] data) throws Exception
	{
		this("BitStringList-" + bSLCount++, data);
	}

	public BitStringList(IBitStringList list) throws Exception
	{
		this("BitStringList-" + bSLCount++, list.toArray());
	}

	public BitStringList(IBooleanVariable[][] iBooleanVariables) throws Exception
	{
		int bvlen = iBooleanVariables.length;
		this.setData(new ArrayList<IBitString>(bvlen));
		for (int i = 0; i < bvlen; i++)
			this.listData.add(new BitString(iBooleanVariables[i]));
	}

	public BitStringList(int n) throws Exception
	{
		this("BitStringList-" + bSLCount++, n, 0);
	}

	public BitStringList(String name) throws Exception
	{
		this.setName(name);
	}

	public BitStringList(String name, boolean[][] bdata) throws Exception
	{
		if (name == null)
			throw new BitStringListException("Passed null String to constructor.");
		if (bdata == null)
			throw new BitStringListException("Passed null boolean[][] to constructor.");
		if (bdata.length == 0)
			throw new BitStringListException("Passed boolean[][] with bdata.length==0 to constructor.");
		this.setName(name);
		this.setData(new ArrayList<IBitString>(bdata.length));
		for (int i = 0; i < bdata.length; i++)
		{
			if (bdata[i] != null)
			{
				IBitString o = new BitString(name + "_" + i, new IBooleanVariable[bdata[i].length]);
				this.getData().add(o);
				for (int j = 0; j < this.getBitString(i).size(); j++)
					this.getBitString(i).setBooleanVariable(j,
							BooleanVariable.getBooleanVariable(name + "_" + i + "_" + j, bdata[i][j]));
			}
			else
				this.getData().add(null);
		}
	}

	public BitStringList(String name, IBitString[] data) throws Exception
	{
		if (name == null)
			throw new BitStringListException("Passed null String to constructor.");
		if (data == null)
			throw new BitStringListException("Passed null IBitString[] to constructor.");
		this.setName(name);
		boolean[][] bdata = new boolean[data.length][];
		for (int i = 0; i < bdata.length; i++)
		{
			// if (data[i] == null)
			// throw new BitStringListException(
			// "Attempted to initialize with null IBitString.");
			if (data[i] != null)
			{
				bdata[i] = new boolean[data[i].size()];
				for (int j = 0; j < bdata[i].length; j++)
					bdata[i][j] = data[i].getBooleanVariable(j).getValue();
			}
		}
		this.setData(new BitStringList(name, bdata).getData());
	}

	public BitStringList(String name, int n, int bits) throws Exception
	{
		this.setName(name);
		this.setData(new ArrayList<IBitString>(n));
		for (int i = 0; i < n; i++)
		{
			IBitString o = new BitString(name + "_" + i, new boolean[bits]);
			this.listData.add(o);
		}
	}

	@Override
	public boolean add(IBitString o)
	{
		try
		{
			return this.getData().add(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public IBitStringList add(IBitStringList list) throws Exception
	{
		return BitStringList.add(this.getName(), this, list);
	}

	@Override
	public void add(int j, BitString bitString)
	{
		add(j, (IBitString) bitString);
	}

	public void add(int n, IBitString o)
	{
		try
		{
			// ((ArrayListSet<Object>)(this.getData())).add(n,o);
			this.getData().add(n, o);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean addAll(Collection<? extends IBitString> c)
	{
		try
		{
			return this.getData().addAll(c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean addAll(int n, Collection<IBitString> c)
	{
		try
		{
			return this.getData().addAll(n, c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void clear()
	{
		try
		{
			this.getData().clear();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		try
		{
			return new BitStringList(this.getName(), this.getData().toArray(new IBitString[0]));
		} catch (CloneNotSupportedException err)
		{
			throw err;
		} catch (Exception err)
		{
			System.err.println("Attempt to clone BitStringList failed.");
			return null;
		}
	}

	public boolean contains(Object o)
	{
		try
		{
			return this.getData().contains(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean containsAll(Collection<?> c)
	{
		try
		{
			return this.getData().containsAll(c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean equals(Object anObject)
	{
		if (anObject == null)
			return false; // this is never equal to null.
		if (!(anObject instanceof BitStringList))
			return false;
		try
		{
			if (!this.getData().containsAll(((BitStringList) anObject).getData()))
				return false;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			if (!((BitStringList) anObject).getData().containsAll(this.getData()))
				return false;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	public IBitString get(int n)
	{
		try
		{
			return this.getData().get(n);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IBitString getBitString(int i) throws Exception
	{
		if (i < 0 || i > this.size() - 1)
			throw new BitStringListException("Attempted to index out of range.");
		return (this.getData().get(i));
	}

	public ArrayList<IBitString> getData() throws Exception
	{
		if (this.listData == null)
			throw new BitStringListException("Attempted to access listData while listData==null.");
		return this.listData;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public int indexOf(Object o)
	{
		try
		{
			return this.getData().indexOf(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean isEmpty()
	{
		return this.listData == null;
	}

	public Iterator<IBitString> iterator()
	{
		try
		{
			return this.getData().iterator();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public int lastIndexOf(Object o)
	{
		try
		{
			return this.getData().lastIndexOf(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	public ListIterator<IBitString> listIterator()
	{
		try
		{
			return this.getData().listIterator();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public ListIterator<IBitString> listIterator(int n)
	{
		try
		{
			return this.getData().listIterator(n);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public IBitString remove(int n)
	{
		try
		{
			return this.getData().remove(n);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean remove(Object o)
	{
		try
		{
			return this.getData().remove(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeAll(Collection<?> c)
	{
		try
		{
			return this.getData().removeAll(c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean retainAll(Collection<?> c)
	{
		try
		{
			return this.getData().retainAll(c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void set(int i, BitString bitString)
	{
		set(i, (IBitString) bitString);
	}

	public IBitString set(int n, IBitString o)
	{
		try
		{
			if (n < 0 || n > this.getData().size() - 1)
				throw new BitStringListException("Attempted to index out of range.");
		} catch (BitStringListException e1)
		{
			e1.printStackTrace();
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}
		try
		{
			// return ((ArrayListSet<Object>)this.getData()).set(n,o);
			return this.getData().set(n, o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void setBitString(int i, IBitString bitString) throws Exception
	{
		if (i < 0 || i > this.size() - 1)
			throw new BitStringListException("Attempted to index out of range.");
		if (bitString == null)
			throw new BitStringListException("Passed null IBitString to setBitString method.");
		this.getData().set(i, bitString);
	}

	public void setData(ArrayList<IBitString> arrayList) throws Exception
	{
		if (arrayList == null)
			throw new BitStringListException("Passed null ArrayListSet to setData method.");
		this.listData = arrayList;
	}

	public void setName(String name) throws Exception
	{
		if (name == null)
			throw new BitStringListException("Passed null String to setName method.");

		this.name = name;
	}

	@Override
	public int size()
	{
		try
		{
			ArrayList<IBitString> dt = this.getData();
			return dt.size();

		} catch (Exception e)
		{
			return 0;
		}
	}

	public List<IBitString> subList(int m, int n)
	{
		try
		{
			return this.getData().subList(m, n);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IBitString[] toArray()
	{
		try
		{
			return this.getData().toArray(new IBitString[0]);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toBits()
	{
		String res = "$";
		if (this.size() > 0)
		{
			try
			{
				res += this.getBitString(0).toBits();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			for (int i = 1; i < this.size(); i++)
				try
				{
					res += "," + this.getBitString(i).toBits();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
		}
		return res + "$";
	}

	@Override
	public ArrayList<IBitString> toList() throws Exception
	{
		return this.listData;
	}

	@Override
	public String toString()
	{
		String res = "$";
		if (this.size() > 0)
		{
			try
			{
				res += this.getBitString(0).toString();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			for (int i = 1; i < this.size(); i++)
				try
				{
					res += "," + this.getBitString(i);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
		}
		return res + "$";
	}
}