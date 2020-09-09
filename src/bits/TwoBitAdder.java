package bits;

/**
 * An extension of the IProblem class which performs an add with carry of two
 * BooleanVariables.
 *
 * To use this class, one passes BooleanVariables x, y, z and c to the
 * constructor. The TwoBitAdder object produced is a IProblem, and one may
 * manipulate it using any of the methods provided by the IProblem class.
 *
 * For example, when the IProblem instance p defined by
 *
 * IProblem p=new TwoBitAdder(x,y,z,c);
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * z == x + y (without carry) c == (carry bit)
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 2.0
 * @since 2004/11/24
 */
public class TwoBitAdder extends Problem implements IProblem
{
	public TwoBitAdder(IBooleanVariable x, IBooleanVariable y, IBooleanVariable z, IBooleanVariable c) throws Exception
	{
		IBooleanLiteral orc = BooleanLiteral.getBooleanLiteral(c, false);
		IBooleanLiteral orx = BooleanLiteral.getBooleanLiteral(x, false);
		IBooleanLiteral ory = BooleanLiteral.getBooleanLiteral(y, false);
		IBooleanLiteral orz = BooleanLiteral.getBooleanLiteral(z, false);
		IBooleanLiteral ornotc = BooleanLiteral.getBooleanLiteral(c, true);
		IBooleanLiteral ornotx = BooleanLiteral.getBooleanLiteral(x, true);
		IBooleanLiteral ornoty = BooleanLiteral.getBooleanLiteral(y, true);
		IBooleanLiteral ornotz = BooleanLiteral.getBooleanLiteral(z, true);

		IClause c1 = Clause.asClause(new IBooleanLiteral[]
		{ ornotc, orx });
		IClause c2 = Clause.asClause(new IBooleanLiteral[]
		{ ornotc, ory });
		IClause c3 = Clause.asClause(new IBooleanLiteral[]
		{ ornotc, ornotz });
		IClause c4 = Clause.asClause(new IBooleanLiteral[]
		{ orc, ornoty, orz });
		IClause c5 = Clause.asClause(new IBooleanLiteral[]
		{ ornotx, ory, orz });
		IClause c6 = Clause.asClause(new IBooleanLiteral[]
		{ ornotx, ornoty, ornotz });
		IClause c7 = Clause.asClause(new IBooleanLiteral[]
		{ orx, ory, ornotz });
		IClause c8 = Clause.asClause(new IBooleanLiteral[]
		{ orx, ornoty, orz });

		this.setClauses(new IClause[]
		{ c1, c2, c3, c4, c5, c6, c7, c8 }

		);
	}
}