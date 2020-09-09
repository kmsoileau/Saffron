package naturalnumbers;

import bits.Clause;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bits.TwoBitAdder;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2019
 */
public class NaturalNumberIncrementer extends Problem implements IProblem
{
	public NaturalNumberIncrementer(INaturalNumber X, INaturalNumber Y) throws Exception
	{
		INaturalNumber C = new NaturalNumber();

		IBooleanVariable bx = X.getBooleanVariable(0);
		IBooleanVariable by = Y.getBooleanVariable(0);
		IBooleanVariable bc = C.getBooleanVariable(0);

		IProblem p1 = new Problem(new IClause[]
		{ Clause.newClause().or(bx).or(by).or(bc), Clause.newClause().or(bx).or(by).orNot(bc),
				Clause.newClause().or(bx).orNot(by).orNot(bc), Clause.newClause().orNot(bx).or(by).or(bc),
				Clause.newClause().orNot(bx).orNot(by).or(bc), Clause.newClause().orNot(bx).orNot(by).orNot(bc) });

		IProblem[] tba = new TwoBitAdder[NaturalNumber.getLength() - 1];
		for (int i = 0; i < tba.length; i++)
			tba[i] = new TwoBitAdder(C.getBooleanVariable(i), X.getBooleanVariable(i + 1), Y.getBooleanVariable(i + 1),
					C.getBooleanVariable(i + 1));
		this.setClauses(new Conjunction(p1, new Conjunction(tba)).getClauses());
	}

	public NaturalNumberIncrementer(INaturalNumber X, INaturalNumber Y, INaturalNumber C) throws Exception
	{
		IBooleanVariable bx = X.getBooleanVariable(0);
		IBooleanVariable by = Y.getBooleanVariable(0);
		IBooleanVariable bc = C.getBooleanVariable(0);

		this.setClauses(new IClause[]
		{ Clause.newClause().or(bx).or(by).or(bc), Clause.newClause().or(bx).or(by).orNot(bc),
				Clause.newClause().or(bx).orNot(by).orNot(bc), Clause.newClause().orNot(bx).or(by).or(bc),
				Clause.newClause().orNot(bx).orNot(by).or(bc), Clause.newClause().orNot(bx).orNot(by).orNot(bc) });
		IProblem[] tba = new TwoBitAdder[NaturalNumber.getLength() - 1];
		for (int i = 0; i < tba.length; i++)
			tba[i] = new TwoBitAdder(C.getBooleanVariable(i), X.getBooleanVariable(i + 1), Y.getBooleanVariable(i + 1),
					C.getBooleanVariable(i + 1));
		IProblem p = new Conjunction(this, new Conjunction(tba));

		this.setClauses(p.getClauses());
	}
}
