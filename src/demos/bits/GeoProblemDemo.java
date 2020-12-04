package demos.bits;

import java.util.ArrayList;

import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;

public class GeoProblemDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");

		ArrayList<IClause> p1 = new ArrayList<IClause>();
		p1.add(new Clause().or(x).orNot(y));
		p1.add(new Clause().orNot(x).or(y));

		Problem prob = new Problem(p1.toArray(new IClause[0]));

		// Combine the constraints into a Problem object :

		GeoProblemDemo gee = new GeoProblemDemo(prob);
		System.out.println(gee);
		System.out.println(gee.doProblem());
	}

	public GeoProblemDemo(Problem p1) throws Exception
	{
		super.setClauses(p1.getClauses());
	}

	private String doBooleanLiteral(IBooleanLiteral bl)
	{
		if (this.size() < 1)
			return "1";
		String ret = "";
		IBooleanVariable bv = bl.getBooleanVariable();
		boolean barred = bl.isBarred();
		String name = bv.getName();
		if (barred)
			ret = "(3./8+1./16*(" + name + "*" + name + "+" + "1./" + name + "/" + name + ")+1./4*(" + name + "+1./"
					+ name + ")";
		else
			ret = "(3./8+1./16*(" + name + "*" + name + "+" + "1./" + name + "/" + name + ")-1./4*(" + name + "+1./"
					+ name + ")";
		return ret;
	}

	private String doClause(IClause c) throws Exception
	{
		int sz = c.size();
		if (sz < 1)
			return "0";
		String ret = "";
		IBooleanLiteral bl;
		for (int i = 0; i < sz; i++)
		{
			bl = ((Clause) c).getLiteralAt(i);
			if (i == 0)
				ret += doBooleanLiteral(bl);
			else
				ret += "*" + doBooleanLiteral(bl);
		}

		return ret;
	}

	public String doProblem() throws Exception
	{
		int sz = this.size();
		if (sz < 1)
			return "0";
		String ret = "";

		for (int i = 0; i < sz; i++)
		{
			IClause c = this.getClause(i);
			if (i == 0)
				ret += doClause(c);
			else
				ret += "\n" + "+" + doClause(c);
		}

		return ret;
	}
}