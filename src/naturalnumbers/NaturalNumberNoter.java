package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.BitNoter;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberNoter extends Problem implements IProblem
{
	public NaturalNumberNoter(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		BitNoter[] bnnb = new BitNoter[NaturalNumber.getLength()];
		for (int i = 0; i < bnnb.length; i++)
			bnnb[i] = new BitNoter(X.getBooleanVariable(i),
					Y.getBooleanVariable(i));
		IProblem p = new Conjunction(bnnb);
		this.setClauses(p.getClauses());
	}
}