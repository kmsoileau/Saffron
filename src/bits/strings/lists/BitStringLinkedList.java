package bits.strings.lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import bits.BooleanVariable;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.strings.BitString;
import bits.strings.lists.exceptions.BitStringLinkedListException;
import utility.ArrayListSet;

public class BitStringLinkedList implements IBitStringLinkedList
{
	private static int bSLLCount;

	public static IBitStringLinkedList add(IBitStringLinkedList first, IBitStringLinkedList second) throws Exception
	{
		return BitStringLinkedList.add("BitStringLinkedList-" + bSLLCount++, first, second);
	}

	public static IBitStringLinkedList add(String name, IBitStringLinkedList first, IBitStringLinkedList second)
			throws Exception
	{
		if (name == null)
			throw new BitStringLinkedListException("Passed null String to constructor.");
		if (first == null || second == null)
			throw new BitStringLinkedListException("Passed null String to constructor.");

		Collection<IBitString> a = first.toList();
		Collection<IBitString> b = second.toList();
		a.addAll(b);
		IBitString[] newArray = a.toArray(new IBitString[0]);
		IBitStringLinkedList res = new BitStringLinkedList(name, newArray);
		return res;
	}

	private LinkedList<IBitString> backing;
	private String name;

	public BitStringLinkedList() throws BitStringLinkedListException
	{
		this.setName("BitStringLinkedList-" + bSLLCount++);
	}

	public BitStringLinkedList(boolean[][] bdata) throws Exception
	{
		this("BitStringLinkedList-" + bSLLCount++, bdata);
	}

	public BitStringLinkedList(IBitString[] data) throws Exception
	{
		this("BitStringLinkedList-" + bSLLCount++, data);
	}

	public BitStringLinkedList(IBitStringLinkedList list) throws Exception
	{
		this("BitStringLinkedList-" + bSLLCount++, list.toArray());
	}

	public BitStringLinkedList(int n) throws Exception
	{
		this("BitStringLinkedList-" + bSLLCount++, n);
	}

	public BitStringLinkedList(String name, boolean[][] bdata) throws Exception
	{
		if (name == null)
			throw new BitStringLinkedListException("Passed null String to constructor.");
		if (bdata == null)
			throw new BitStringLinkedListException("Passed null boolean[][] to constructor.");
		if (bdata.length == 0)
			throw new BitStringLinkedListException("Passed boolean[][] with bdata.length==0 to constructor.");
		this.backing = new LinkedList<IBitString>();
		this.setName(name);
		for (int i = 0; i < bdata.length; i++)
		{
			IBitString o = new BitString(name + "_" + i, new IBooleanVariable[bdata[i].length]);
			backing.add(o);
			for (int j = 0; j < this.getBitString(i).size(); j++)
				this.getBitString(i).setBooleanVariable(j,
						BooleanVariable.getBooleanVariable(name + "_" + i + "_" + j, bdata[i][j]));
		}
	}

	public BitStringLinkedList(String name, IBitString[] data) throws Exception
	{
		if (name == null)
			throw new BitStringLinkedListException("Passed null String to constructor.");
		if (data == null)
			throw new BitStringLinkedListException("Passed null IBitString[] to constructor.");
		this.setName(name);
		boolean[][] bdata = new boolean[data.length][];
		for (int i = 0; i < bdata.length; i++)
		{
			if (data[i] == null)
				throw new BitStringLinkedListException("Attempted to initialize with null IBitString.");
			bdata[i] = new boolean[data[i].size()];
			for (int j = 0; j < bdata[i].length; j++)
				bdata[i][j] = data[i].getBooleanVariable(j).getValue();
			this.add(new BitString(bdata[i]));
		}
	}

	public BitStringLinkedList(String name, int n) throws Exception
	{
		this.setName(name);
		for (int i = 0; i < n; i++)
		{
			IBitString o = new BitString(name + "_" + i, (boolean[]) null);
			backing.add(o);
		}
	}

	@Override
	public boolean add(IBitString o)
	{
		try
		{
			return backing.add(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public IBitStringLinkedList add(IBitStringLinkedList list) throws Exception
	{
		return BitStringLinkedList.add(this.getName(), this, list);
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
			backing.add(n, o);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean addAll(Collection<IBitString> c)
	{
		return addAllBitStrings(c);
	}

	public boolean addAll(int n, Collection<IBitString> c)
	{
		try
		{
			return backing.addAll(n, c);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	private boolean addAllBitStrings(Collection<IBitString> c)
	{
		try
		{
			return backing.addAll(c);
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
			backing.clear();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Object clone()
	{
		try
		{
			return new BitStringLinkedList(this.getName(), backing.toArray(new IBitString[0]));
		} catch (CloneNotSupportedException err)
		{
			try
			{
				throw err;
			} catch (CloneNotSupportedException e)
			{
				e.printStackTrace();
				return null;
			}
		} catch (Exception err)
		{
			System.err.println("Attempt to clone BitStringLinkedList failed.");
			return null;
		}
	}

	public boolean contains(Object o)
	{
		try
		{
			return backing.contains(o);
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
			return backing.containsAll(c);
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
		if (!(anObject instanceof BitStringLinkedList))
			return false;
		try
		{
			if (!backing.containsAll((Collection<?>) anObject))
				return false;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			if (!((Collection<?>) anObject).containsAll((Collection<?>) this))
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
			return backing.get(n);
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
			throw new BitStringLinkedListException("Attempted to index out of range.");
		return (backing.get(i));
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	public int indexOf(Object o)
	{
		try
		{
			return backing.indexOf(o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean isEmpty()
	{
		try
		{
			return backing.size() == 0;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public Iterator<IBitString> iterator()
	{
		try
		{
			return backing.iterator();
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
			return backing.lastIndexOf(o);
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
			return backing.listIterator();
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
			return backing.listIterator(n);
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
			return backing.remove(n);
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
			return backing.remove(o);
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
			return backing.removeAll(c);
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
			return backing.retainAll(c);
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
			return backing.set(n, o);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void setBitString(int i, IBitString bitString) throws Exception
	{
		if (i < 0 || i > this.size() - 1)
			throw new BitStringLinkedListException("Attempted to index out of range.");
		if (bitString == null)
			throw new BitStringLinkedListException("Passed null IBitString to setBitString method.");
		backing.set(i, bitString);
	}

	public void setName(String name) throws BitStringLinkedListException
	{
		if (name == null)
			throw new BitStringLinkedListException("Passed null String to setName method.");

		this.name = name;
	}

	@Override
	public int size()
	{
		try
		{
			return backing.size();
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	public List<IBitString> subList(int m, int n)
	{
		try
		{
			return backing.subList(m, n);
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
			return backing.toArray(new IBitString[0]);
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
	public ArrayListSet<IBitString> toList() throws Exception
	{
		return (ArrayListSet<IBitString>) backing.subList(0, backing.size() - 1);
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
