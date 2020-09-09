package naturalnumbers.rectangles;

import naturalnumbers.intervals.INaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberInterval;

public class NaturalNumberRectangle implements INaturalNumberRectangle
{
	private static int index = 0;

	private INaturalNumberInterval altitude;
	private INaturalNumberInterval base;

	public NaturalNumberRectangle() throws Exception
	{
		this("NNR-" + index++);
	}

	public NaturalNumberRectangle(String name) throws Exception
	{
		super();
		this.base = new NaturalNumberInterval(name + "_base");
		this.altitude = new NaturalNumberInterval(name + "_altitude");
	}

	@Override
	public INaturalNumberInterval getAltitude()
	{
		return altitude;
	}

	@Override
	public INaturalNumberInterval getBase()
	{
		return base;
	}

	@Override
	public void setAltitude(INaturalNumberInterval altitude)
	{
		this.altitude = altitude;
	}

	@Override
	public void setBase(INaturalNumberInterval base)
	{
		this.base = base;
	}

	@Override
	public String toString()
	{
		return base + "&commat;" + altitude;
	}
}
