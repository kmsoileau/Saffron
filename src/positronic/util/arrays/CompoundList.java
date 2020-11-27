package positronic.util.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

final public class CompoundList implements ImmutableList
{
	private final HashMap<Object, Integer> lookup = new HashMap<Object, Integer>();

	private final HashMap<Integer, Object> pukool = new HashMap<Integer, Object>();

	private final ArrayList<Object> backing = new ArrayList<Object>();

	private final List<Object> keysList;

	public CompoundList()
	{
		this(new Object[0]);
	}

	public CompoundList(ArrayList<Object> list)
	{
		this(list.toArray());
	}

	public CompoundList(Object obj)
	{
		this(new Object[]
		{ obj });
	}

	public CompoundList(Object obj1, Object obj2)
	{
		this(new Object[]
		{ obj1, obj2 });
	}

	public CompoundList(Object obj1, Object obj2, Object obj3)
	{
		this(new Object[]
		{ obj1, obj2, obj3 });
	}

	public CompoundList(Object[] array)
	{
		this.backing.addAll(Arrays.asList(array));

		int index = 0;
		for (Object o : this.backing)
		{
			lookup.put(o, index);
			pukool.put(index, o);
			if (o instanceof CompoundList)
			{
				index += ((CompoundList) o).size();
			}
			else
			{
				index++;
			}
		}

		this.keysList = Arrays.asList(this.getPukool().keySet().toArray());
		this.keysList.sort(null);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Object clone()
	{
		return new CompoundList((ArrayList<Object>) this.backing.clone());
	}

	@Override
	public boolean contains(Object obj)
	{
		for (Object o : this.backing)
		{
			if (o instanceof CompoundList)
			{
				CompoundList curr = (CompoundList) o;
				if (curr.contains(obj))
					return true;
			}
			else if (o.equals(obj))
				return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean containsAll(Collection c)
	{
		for (Iterator iterator = c.iterator(); iterator.hasNext();)
		{
			Object o = iterator.next();
			if (!this.contains(o))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public Object get(int index)
	{
		Integer si = this.seekItem(index);
		Object o = this.pukool.get(si);
		if (o instanceof CompoundList)
		{
			CompoundList cp = (CompoundList) o;
			// System.out.println("\n\n" + cp + "\n" + (index - si) + "\n" + cp.pukool);
			return cp.get(index - si);

		}
		else
			return o;
	}

	public ArrayList<Object> getBacking()
	{
		return backing;
	}

	public HashMap<Object, Integer> getLookup()
	{
		return lookup;
	}

	public HashMap<Integer, Object> getPukool()
	{
		return pukool;
	}

	@Override
	public boolean isEmpty()
	{
		return this.size() == 0;
	}

	@Override
	public Iterator<Object> iterator()
	{
		Iterator<Object> ww = this.backing.iterator();
		return ww;
	}

	@Override
	public int lastIndexOf(Object o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	Integer seekItem(int n)
	{
		int sz = this.keysList.size();
		Integer curr = -1;
		for (int i = 0; i < sz; i++)
		{
			Integer newcurr = (Integer) this.keysList.get(i);
			if (newcurr > n)
				break;
			curr = newcurr;
		}

		return curr;
	}

	@Override
	public int size()
	{
		int ret = 0;
		for (Object o : this.backing)
		{
			if (o instanceof CompoundList)
			{
				ret += ((CompoundList) o).size();
			}
			else
				ret++;
		}
		return ret;
	}

	@Override
	public Object[] toArray()
	{
		ArrayList<Object> ret = new ArrayList<Object>();
		int sz = this.backing.size();
		for (int i = 0; i < sz; i++)
		{
			Object curr = this.backing.get(i);
			if (curr instanceof CompoundList)
			{
				CompoundList currcl = (CompoundList) curr;
				Object[] q = currcl.toArray();
				for (int j = 0; j < q.length; j++)
				{
					ret.add(q[j]);
				}
			}
			else
				ret.add(curr);
		}

		return ret.toArray();
	}

	@Override
	public Object[] toArray(Object[] a)
	{
		ArrayList<Object> ret = new ArrayList<Object>();
		int sz = this.backing.size();
		for (int i = 0; i < sz; i++)
		{
			Object curr = this.backing.get(i);
			if (curr instanceof CompoundList)
			{
				CompoundList currcl = (CompoundList) curr;
				Object[] q = currcl.toArray();
				for (int j = 0; j < q.length; j++)
				{
					ret.add(q[j]);
				}
			}
			else
				ret.add(curr);
		}

		return ret.toArray(a);
	}

	@Override
	public String toString()
	{
		String ret = "";
		for (Object o : backing)
		{
			if (o instanceof CompoundList)
			{
				ret += "[ " + ((CompoundList) o).toString() + "] ";
			}
			else
				ret += o.toString() + " ";
		}
		return ret;
	}
}