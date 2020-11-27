package positronic.util.arrays;

import java.util.Collection;
import java.util.Iterator;

public interface ImmutableList
{
	public boolean contains(Object obj);

	@SuppressWarnings("rawtypes")
	public boolean containsAll(Collection c);

	public Object get(int index);

	public boolean isEmpty();

	public Iterator<Object> iterator();

	public int lastIndexOf(Object o);

	public int size();

	public Object[] toArray();

	public Object[] toArray(Object[] a);
}
