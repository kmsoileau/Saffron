package naturalnumbers.binpacking;

public class Bin
{
	private long capacity;
	private String name;

	public Bin(String name, long capacity)
	{
		super();
		this.capacity = capacity;
		this.name = name;
	}

	public long getCapacity()
	{
		return capacity;
	}

	public String getName()
	{
		return name;
	}

	public void setCapacity(long capacity)
	{
		this.capacity = capacity;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}