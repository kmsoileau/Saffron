package naturalnumbers.rectangles;

import naturalnumbers.intervals.INaturalNumberInterval;

public interface INaturalNumberRectangle
{
	int HORIZONTAL = 0;

	int VERTICAL = 0;

	INaturalNumberInterval getAltitude();

	INaturalNumberInterval getBase();

	void setAltitude(INaturalNumberInterval altitude);

	void setBase(INaturalNumberInterval base);

	@Override
	String toString();
}
