package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.BitUnequalizer;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberUnequalizer extends Problem implements IProblem
{
	public NaturalNumberUnequalizer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		BitUnequalizer[] thba = new BitUnequalizer[NaturalNumber.getLength()];
		for (int i = 0; i < NaturalNumber.getLength(); i++)
			thba[i] = new BitUnequalizer(X.getBooleanVariable(i),
					Y.getBooleanVariable(i));
		IProblem p1 = new Disjunction(thba);
		this.setClauses(p1.getClauses());
	}
}