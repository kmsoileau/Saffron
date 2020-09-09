package naturalnumbers.rectangles;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.exceptions.NaturalNumberFixerException;
import naturalnumbers.intervals.INaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberIntervalEqualizer;
import naturalnumbers.intervals.NaturalNumberIntervalFixer;
import utility.IntegerPair;

public class NaturalNumberRectangleFixer extends Problem implements IProblem
{
	public NaturalNumberRectangleFixer(INaturalNumberRectangle rectangle, INaturalNumberInterval base,
			INaturalNumberInterval altitude) throws Exception
	{
		if (rectangle == null)
			throw new NaturalNumberFixerException("A null INaturalNumberRectangle was passed to the constructor.");
		if (base == null || altitude == null)
			throw new NaturalNumberFixerException("A null INaturalNumberInterval was passed to the constructor.");

		INaturalNumberInterval bas = rectangle.getBase();
		INaturalNumberInterval alt = rectangle.getAltitude();

		IProblem p = new Conjunction(new NaturalNumberIntervalEqualizer(bas, base),
				new NaturalNumberIntervalEqualizer(alt, altitude));

		this.setClauses(p.getClauses());
	}

	public NaturalNumberRectangleFixer(INaturalNumberRectangle rectangle, int base, int altitude) throws Exception
	{
		this(rectangle, 0, 0, base, altitude);
	}

	public NaturalNumberRectangleFixer(INaturalNumberRectangle rectangle, int x, int y, int base, int altitude)
			throws Exception
	{
		if (rectangle == null)
			throw new NaturalNumberFixerException("A null INaturalNumberRectangle was passed to the constructor.");
		if (x < 0 || y < 0 || base < 1 || altitude < 1)
			throw new NaturalNumberFixerException("An int less than 1 was passed to the constructor.");

		INaturalNumberInterval A = new NaturalNumberInterval();
		INaturalNumberInterval B = new NaturalNumberInterval();

		IProblem problem = new Conjunction(new NaturalNumberIntervalFixer(A, x, x + base - 1),
				new NaturalNumberIntervalFixer(B, y, y + altitude - 1),
				new NaturalNumberRectangleFixer(rectangle, A, B));

		this.setClauses(problem.getClauses());
	}

	public NaturalNumberRectangleFixer(INaturalNumberRectangle rectangle, IntegerPair integerPair) throws Exception
	{
		this(rectangle, integerPair.getI1(), integerPair.getI2());
	}

	public NaturalNumberRectangleFixer(INaturalNumberRectangle rectangle, IntegerPair lowerLeft, IntegerPair upperRight)
			throws Exception
	{
		this(rectangle, lowerLeft.getI1(), lowerLeft.getI2(), upperRight.getI1() - lowerLeft.getI1() + 1,
				upperRight.getI2() - lowerLeft.getI2() + 1);
	}
}