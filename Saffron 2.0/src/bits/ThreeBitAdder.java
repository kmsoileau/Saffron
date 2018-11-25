package bits;

/**
 * An extension of the IProblem class which performs an add with carry of three
 * BooleanVariables.
 *
 * To use this class, one passes BooleanVariables w, x, y, z and c to the
 * constructor. The ThreeBitAdder object produced is a IProblem, and one may
 * manipulate it using any of the methods provided by the IProblem class.
 *
 * For example, when the IProblem instance p defined by
 *
 * IProblem p=new ThreeBitAdder(w,x,y,z,c);
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * z == w + x + y (without carry) c == (carry bit)
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.11
 * @since 2004/11/24
 */
public class ThreeBitAdder extends Problem implements IProblem
{
	public ThreeBitAdder(IBooleanVariable w, IBooleanVariable x,
			IBooleanVariable y, IBooleanVariable z, IBooleanVariable c)
			throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(w).or(x).orNot(c),
				Clause.newClause().or(w).or(y).orNot(c),
				Clause.newClause().or(w).orNot(z).orNot(c),
				Clause.newClause().or(x).or(y).orNot(c),
				Clause.newClause().or(x).orNot(z).orNot(c),
				Clause.newClause().or(y).orNot(z).orNot(c),
				Clause.newClause().orNot(w).orNot(x).orNot(y).or(z),
				Clause.newClause().or(w).or(x).or(y).orNot(z),
				Clause.newClause().or(w).or(x).orNot(y).or(z),
				Clause.newClause().or(w).orNot(x).or(y).or(z),
				Clause.newClause().or(w).orNot(y).or(z).or(c),
				Clause.newClause().or(w).orNot(x).orNot(y).orNot(z),
				Clause.newClause().orNot(w).or(x).or(y).or(z),
				Clause.newClause().or(x).orNot(y).or(z).or(c),
				Clause.newClause().orNot(w).or(x).orNot(y).orNot(z),
				Clause.newClause().orNot(x).or(y).or(z).or(c),
				Clause.newClause().orNot(w).orNot(x).or(y).orNot(z),
				Clause.newClause().orNot(x).orNot(y).orNot(z).or(c) });
	}
}
/*
public class ThreeBitAdder extends Problem implements IProblem
{
	public ThreeBitAdder(IBooleanVariable w, IBooleanVariable x,
			IBooleanVariable y, IBooleanVariable z, IBooleanVariable c)
			throws Exception
	{
		IBooleanLiteral orw = BooleanLiteral.getBooleanLiteral(c, false);
		IBooleanLiteral orx = BooleanLiteral.getBooleanLiteral(x, false);
		IBooleanLiteral ory = BooleanLiteral.getBooleanLiteral(y, false);
		IBooleanLiteral orz = BooleanLiteral.getBooleanLiteral(z, false);
		IBooleanLiteral orc = BooleanLiteral.getBooleanLiteral(c, false);
		IBooleanLiteral ornotw = BooleanLiteral.getBooleanLiteral(w, true);
		IBooleanLiteral ornotx = BooleanLiteral.getBooleanLiteral(x, true);
		IBooleanLiteral ornoty = BooleanLiteral.getBooleanLiteral(y, true);
		IBooleanLiteral ornotz = BooleanLiteral.getBooleanLiteral(z, true);
		IBooleanLiteral ornotc = BooleanLiteral.getBooleanLiteral(c, true);

		IClause c1 = Clause.asClause(new IBooleanLiteral[]
		{ orw, orx, ornotc });
		IClause c2 = Clause.asClause(new IBooleanLiteral[]
		{ orw, ory, ornotc });
		IClause c3 = Clause.asClause(new IBooleanLiteral[]
		{ orw, ornotz, ornotc });
		IClause c4 = Clause.asClause(new IBooleanLiteral[]
		{ orx, ory, ornotc });
		IClause c5 = Clause.asClause(new IBooleanLiteral[]
		{ orx, ornotz, ornotc });
		IClause c6 = Clause.asClause(new IBooleanLiteral[]
		{ ory, ornotz, ornotc });
		IClause c7 = Clause.asClause(new IBooleanLiteral[]
		{ ornotw, ornotx, ornoty, orz });
		IClause c8 = Clause.asClause(new IBooleanLiteral[]
		{ orw, orx, ory, ornotz });
		IClause c9 = Clause.asClause(new IBooleanLiteral[]
		{ orw, orx, ornoty, orz });
		IClause c10 = Clause.asClause(new IBooleanLiteral[]
		{ orw, ornotx, ory, orz });
		IClause c11 = Clause.asClause(new IBooleanLiteral[]
		{ orw, ornoty, orz, orc });
		IClause c12 = Clause.asClause(new IBooleanLiteral[]
		{ orw, ornotx, ornoty, ornotz });
		IClause c13 = Clause.asClause(new IBooleanLiteral[]
		{ ornotw, orx, ory, orz });
		IClause c14 = Clause.asClause(new IBooleanLiteral[]
		{ orx, ornoty, orz, orc });
		IClause c15 = Clause.asClause(new IBooleanLiteral[]
		{ ornotw, orx, ornoty, ornotz });
		IClause c16 = Clause.asClause(new IBooleanLiteral[]
		{ ornotx, ory, orz, orc });
		IClause c17 = Clause.asClause(new IBooleanLiteral[]
		{ ornotw, ornotx, ory, ornotz });
		IClause c18 = Clause.asClause(new IBooleanLiteral[]
		{ ornotx, ornoty, ornotz, orc });

		this.setClauses(new IClause[]
		{ c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15,
				c16, c17, c18 });
	}
}
*/