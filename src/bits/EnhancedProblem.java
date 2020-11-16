package bits;

import java.util.List;

import positronic.util.arrays.CompoundList;
import utility.EquivalenceRelation;

public class EnhancedProblem extends Problem implements IProblem
{
	public static String dump(List<?> s)
	{
		String ret = "\n";
		for (Object o : s)
		{
			IBooleanLiteral curr = (IBooleanLiteral) o;
			if (curr.isBarred())
				ret += (curr.getBooleanVariable().getName() + " = false\n");
			else
				ret += (curr.getBooleanVariable().getName() + " = true\n");
		}
		return ret;
	}

	public static IProblem trivialProblem() throws Exception
	{
		IClause cl = new Clause();
		IBooleanVariable bv = BooleanVariable.getBooleanVariable();
		cl.add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(bv, false));
		cl.add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(bv, true));
		IProblem ret = Problem.newProblem();
		((Problem) ret).setBacking(new CompoundList(cl));
		return ret;
	}

	public static IProblem unsolvableProblem() throws Exception
	{
		return new Problem(new IClause[]
		{ new Clause() });
	}

	public EnhancedProblem()
	{
		super();
	}

	public EnhancedProblem(IProblem problem)
	{
		try
		{
			super.setClauses(problem.getClauses());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EquivalenceRelation buildEquivalenceRelation()
	{
		EquivalenceRelation e = new EquivalenceRelation();

		for (int i = 0; i < this.size(); i++)
		{
			Object[] objary = this.getClause(i).getBooleanVariables();
			for (int j = 0; j < objary.length; j++)
				for (int k = 0; k < objary.length; k++)
					e.add(objary[j], objary[k]);
		}
		return e;
	}

	public IProblemMessage[] findTwoModels(IBooleanVariable[] b) throws Exception
	{
		IProblem res[] = new IProblem[b.length];
		for (int i = 0; i < res.length; i++)
		{
			IProblemMessage[] ret = findTwoModels(b[i]);
			if (ret != null & ret.length == 2 && ret[0] != null && ret[0].getLiterals().size() > 0
					&& ret[1].getLiterals() != null && ret[1].getLiterals().size() > 0)
				return ret;
		}
		return null;
	}

	public IProblem resolve(IBooleanVariable b, boolean value) throws Exception
	{
		IClause[] c = new IClause[this.size()];
		for (int i = 0; i < this.size(); i++)
			c[i] = this.getClause(i).resolve(b, value);
		IProblem ret = Problem.newProblem();
		ret.setClauses(c);
		return ret;
	}

	public EnhancedProblem toThreeSatProblem() throws Exception
	{
		if (this.size() == 0)
			return this;
		EnhancedProblem problem = null;
		if (this.getClause(0) != null)
			problem = new EnhancedProblem(((Clause) this.getClause(0)).ThreeSATProblem());
		for (int i = 1; i < this.size(); i++)
			if (this.getClause(i) != null)
				problem = new EnhancedProblem(new Conjunction(problem, ((Clause) this.getClause(i)).ThreeSATProblem()));
		return problem;
	}

	/**
	 * <p>
	 * &lt;xml version="1.0" encoding="UTF-8" ?&gt;
	 * <p>
	 * &lt;!ELEMENT Literal EMPTY &gt;
	 * <p>
	 * &lt;!ATTLIST Literal variable NMTOKEN #REQUIRED &gt;
	 * <p>
	 * &lt;!ATTLIST Literal barred ( false | true ) #REQUIRED &gt;
	 * <p>
	 * &lt;!ELEMENT Problem ( Clause+ ) &gt;
	 * <p>
	 * &lt;!ELEMENT Clause ( Literal+ ) &gt;
	 */
	/**
	 * @return String
	 */
	public String toXML()
	{
		String res = "<Problem>\n";
		for (int i = 0; i < this.size(); i++)
		{
			res += "\t<Clause>\n";
			Object[] obary = (this.getClause(i)).toArray();
			for (int j = 0; j < obary.length; j++)
			{
				IBooleanLiteral b = (IBooleanLiteral) (obary[j]);
				res += "\t\t<Literal variable=\"" + b.getBooleanVariable().getName() + "\" barred=\"";
				if (b.isBarred())
					res += "true\"/>\n";
				else
					res += "false\"/>\n";
			}
			res += "\t</Clause>\n";
		}
		res += "</Problem>";
		return res;
	}
}
