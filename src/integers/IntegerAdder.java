/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 10, 2019
 */
package integers;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.NaturalNumberStrictOrderer;

/**
 * 
 *
 */
public class IntegerAdder extends Problem implements IProblem
{
	public IntegerAdder(IInteger X, IInteger Y, IInteger Z) throws Exception
	{
		IProblem[] p = new IProblem[6];

		IProblem pbfxf = new BitFixer(X.getSign(), false);
		IProblem pbfxt = new BitFixer(X.getSign(), true);
		IProblem pbfyf = new BitFixer(Y.getSign(), false);
		IProblem pbfyt = new BitFixer(Y.getSign(), true);
		IProblem pbfzf = new BitFixer(Z.getSign(), false);
		IProblem pbfzt = new BitFixer(Z.getSign(), true);

		IProblem addxyz = new NaturalNumberAdder(X.getAbsValue(),
				Y.getAbsValue(), Z.getAbsValue());

		p[0] = new Disjunction(pbfxf, pbfyf, new Conjunction(addxyz, pbfzt));
		p[1] = new Disjunction(pbfxf, pbfyt, new NaturalNumberStrictOrderer(
				X.getAbsValue(), Y.getAbsValue()), new Conjunction(
				new NaturalNumberAdder(Y.getAbsValue(), Z.getAbsValue(),
						X.getAbsValue()), pbfzt));
		p[2] = new Disjunction(pbfxf, pbfyt, new NaturalNumberOrderer(
				Y.getAbsValue(), X.getAbsValue()), new Conjunction(
				new NaturalNumberAdder(X.getAbsValue(), Z.getAbsValue(),
						Y.getAbsValue()), pbfzf));
		p[3] = new Disjunction(pbfxt, pbfyf, new NaturalNumberStrictOrderer(
				Y.getAbsValue(), X.getAbsValue()), new Conjunction(
				new NaturalNumberAdder(X.getAbsValue(), Z.getAbsValue(),
						Y.getAbsValue()), pbfzt));
		p[4] = new Disjunction(pbfxt, pbfyf, new NaturalNumberOrderer(
				X.getAbsValue(), Y.getAbsValue()), new Conjunction(
				new NaturalNumberAdder(Y.getAbsValue(), Z.getAbsValue(),
						X.getAbsValue()), pbfzf));
		p[5] = new Disjunction(pbfxt, pbfyt, new Conjunction(addxyz, pbfzf));

		this.setClauses(new Conjunction(p).getClauses());
	}
}
