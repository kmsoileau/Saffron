package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.BitAnder;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberAnder extends Problem implements IProblem
{
	public NaturalNumberAnder(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		int span = NaturalNumber.getLength();
		BitAnder[] bnnb = new BitAnder[span];
		for (int i = 0; i < bnnb.length; i++)
			bnnb[i] = new BitAnder(X.getBooleanVariable(i),
					Y.getBooleanVariable(i), Z.getBooleanVariable(i));
		IProblem p = new Conjunction(bnnb);
		this.setClauses(p.getClauses());
	}
}