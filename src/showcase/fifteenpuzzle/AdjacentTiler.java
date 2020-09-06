package showcase.fifteenpuzzle;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberFixer;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 22, 2019
 */
public class AdjacentTiler extends Problem implements IProblem
{
	public AdjacentTiler(INaturalNumber I, INaturalNumber J) throws Exception
	{
		IProblem[] pArray = new IProblem[]
		{
				new Conjunction(new NaturalNumberFixer(I, 0),
						new NaturalNumberFixer(J, 1)),
				new Conjunction(new NaturalNumberFixer(I, 0),
						new NaturalNumberFixer(J, 4)),
				new Conjunction(new NaturalNumberFixer(I, 1),
						new NaturalNumberFixer(J, 0)),
				new Conjunction(new NaturalNumberFixer(I, 1),
						new NaturalNumberFixer(J, 2)),
				new Conjunction(new NaturalNumberFixer(I, 1),
						new NaturalNumberFixer(J, 5)),
				new Conjunction(new NaturalNumberFixer(I, 2),
						new NaturalNumberFixer(J, 1)),
				new Conjunction(new NaturalNumberFixer(I, 2),
						new NaturalNumberFixer(J, 3)),
				new Conjunction(new NaturalNumberFixer(I, 2),
						new NaturalNumberFixer(J, 6)),
				new Conjunction(new NaturalNumberFixer(I, 3),
						new NaturalNumberFixer(J, 2)),
				new Conjunction(new NaturalNumberFixer(I, 3),
						new NaturalNumberFixer(J, 7)),
				new Conjunction(new NaturalNumberFixer(I, 4),
						new NaturalNumberFixer(J, 0)),
				new Conjunction(new NaturalNumberFixer(I, 4),
						new NaturalNumberFixer(J, 5)),
				new Conjunction(new NaturalNumberFixer(I, 4),
						new NaturalNumberFixer(J, 8)),
				new Conjunction(new NaturalNumberFixer(I, 5),
						new NaturalNumberFixer(J, 1)),
				new Conjunction(new NaturalNumberFixer(I, 5),
						new NaturalNumberFixer(J, 4)),
				new Conjunction(new NaturalNumberFixer(I, 5),
						new NaturalNumberFixer(J, 6)),
				new Conjunction(new NaturalNumberFixer(I, 5),
						new NaturalNumberFixer(J, 9)),
				new Conjunction(new NaturalNumberFixer(I, 6),
						new NaturalNumberFixer(J, 2)),
				new Conjunction(new NaturalNumberFixer(I, 6),
						new NaturalNumberFixer(J, 5)),
				new Conjunction(new NaturalNumberFixer(I, 6),
						new NaturalNumberFixer(J, 7)),
				new Conjunction(new NaturalNumberFixer(I, 6),
						new NaturalNumberFixer(J, 10)),
				new Conjunction(new NaturalNumberFixer(I, 7),
						new NaturalNumberFixer(J, 3)),
				new Conjunction(new NaturalNumberFixer(I, 7),
						new NaturalNumberFixer(J, 6)),
				new Conjunction(new NaturalNumberFixer(I, 7),
						new NaturalNumberFixer(J, 11)),
				new Conjunction(new NaturalNumberFixer(I, 8),
						new NaturalNumberFixer(J, 4)),
				new Conjunction(new NaturalNumberFixer(I, 8),
						new NaturalNumberFixer(J, 9)),
				new Conjunction(new NaturalNumberFixer(I, 8),
						new NaturalNumberFixer(J, 12)),
				new Conjunction(new NaturalNumberFixer(I, 9),
						new NaturalNumberFixer(J, 5)),
				new Conjunction(new NaturalNumberFixer(I, 9),
						new NaturalNumberFixer(J, 8)),
				new Conjunction(new NaturalNumberFixer(I, 9),
						new NaturalNumberFixer(J, 10)),
				new Conjunction(new NaturalNumberFixer(I, 9),
						new NaturalNumberFixer(J, 13)),
				new Conjunction(new NaturalNumberFixer(I, 10),
						new NaturalNumberFixer(J, 6)),
				new Conjunction(new NaturalNumberFixer(I, 10),
						new NaturalNumberFixer(J, 9)),
				new Conjunction(new NaturalNumberFixer(I, 10),
						new NaturalNumberFixer(J, 11)),
				new Conjunction(new NaturalNumberFixer(I, 10),
						new NaturalNumberFixer(J, 14)),
				new Conjunction(new NaturalNumberFixer(I, 11),
						new NaturalNumberFixer(J, 7)),
				new Conjunction(new NaturalNumberFixer(I, 11),
						new NaturalNumberFixer(J, 10)),
				new Conjunction(new NaturalNumberFixer(I, 11),
						new NaturalNumberFixer(J, 15)),
				new Conjunction(new NaturalNumberFixer(I, 12),
						new NaturalNumberFixer(J, 8)),
				new Conjunction(new NaturalNumberFixer(I, 12),
						new NaturalNumberFixer(J, 13)),
				new Conjunction(new NaturalNumberFixer(I, 13),
						new NaturalNumberFixer(J, 9)),
				new Conjunction(new NaturalNumberFixer(I, 13),
						new NaturalNumberFixer(J, 12)),
				new Conjunction(new NaturalNumberFixer(I, 13),
						new NaturalNumberFixer(J, 14)),
				new Conjunction(new NaturalNumberFixer(I, 14),
						new NaturalNumberFixer(J, 10)),
				new Conjunction(new NaturalNumberFixer(I, 14),
						new NaturalNumberFixer(J, 13)),
				new Conjunction(new NaturalNumberFixer(I, 14),
						new NaturalNumberFixer(J, 15)),
				new Conjunction(new NaturalNumberFixer(I, 15),
						new NaturalNumberFixer(J, 11)),
				new Conjunction(new NaturalNumberFixer(I, 15),
						new NaturalNumberFixer(J, 14)) };

		this.setClauses(new Disjunction(pArray).getClauses());
	}
}
