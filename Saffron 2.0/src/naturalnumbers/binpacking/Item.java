package naturalnumbers.binpacking;

public class Item
{
	private String name;
	private long size;

	public Item(String name, long size)
	{
		this.setName(name);
		this.setSize(size);
	}

	public String getName()
	{
		return name;
	}

	public long getSize()
	{
		return size;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setSize(long size)
	{
		this.size = size;
	}

	@Override
	public String toString()
	{
		return getName() + "(" + getSize() + ")";
	}
}