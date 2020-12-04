/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 9, 2019
 */
package bits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

class LiteralDirectory
{
	private Map<IBooleanLiteral, Integer> BL2sat4jLiteral;
	private Map<Integer, IBooleanLiteral> sat4jLiteral2BL;

	public LiteralDirectory(Map<IBooleanLiteral, Integer> bL2sat4jLiteral,
			Map<Integer, IBooleanLiteral> sat4jLiteral2BL)
	{
		this.setBL2sat4jLiteral(bL2sat4jLiteral);
		this.setSat4jLiteral2BL(sat4jLiteral2BL);
	}

	void add(IBooleanLiteral currBL, Integer objSat4jLiteral)
	{
		this.getBL2sat4jLiteral().put(currBL, objSat4jLiteral);
		this.getSat4jLiteral2BL().put(objSat4jLiteral, currBL);
	}

	public Map<IBooleanLiteral, Integer> getBL2sat4jLiteral()
	{
		return BL2sat4jLiteral;
	}

	public Map<Integer, IBooleanLiteral> getSat4jLiteral2BL()
	{
		return sat4jLiteral2BL;
	}

	public void setBL2sat4jLiteral(Map<IBooleanLiteral, Integer> bL2sat4jLiteral)
	{
		BL2sat4jLiteral = bL2sat4jLiteral;
	}

	public void setSat4jLiteral2BL(Map<Integer, IBooleanLiteral> sat4jLiteral2BL)
	{
		this.sat4jLiteral2BL = sat4jLiteral2BL;
	}
}

/**
 * 
 *
 */
public class Sat4j
{
	private static LiteralDirectory directoryLit;
	private static VariableDirectory directoryVar;
	private static int numberOfVariables = 0;

	private static ISolver solver;

	static void createSolver(IProblem problem)
	{
		getSolver().newVar(getNumberOfVariables());
		for (int i = 0; i < problem.size(); i++)
		{
			IClause currClause = problem.getClause(i);
			IVecInt currIvi = new VecInt();
			for (int j = 0; j < currClause.size(); j++)
			{
				IBooleanLiteral currBL = null;
				try
				{
					currBL = ((Clause) currClause).getLiteralAt(j);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				currIvi.push(getSat4jLiteral(currBL));
			}

			try
			{
				getSolver().addClause(currIvi);
			} catch (ContradictionException e)
			{
				int[] d = new int[currIvi.size()];
				currIvi.copyTo(d);
			}
		}
	}

	static void DIMACSBLassign(IProblem problem)
	{
		// Assign according to DIMACS format a unique integer to each
		// positronic.satisfiability.elements.IBooleanLiteral found in problem

		List<IBooleanLiteral> booleanLiterals = new ArrayList<IBooleanLiteral>();
		int numberOfLiterals = 0;

		for (int i = 0; i < problem.size(); i++)
		{
			IClause currClause = problem.getClause(i);
			for (int j = 0; j < currClause.size(); j++)
			{
				IBooleanLiteral currBL = null;
				try
				{
					currBL = ((Clause) currClause).getLiteralAt(j);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				if (!booleanLiterals.contains(currBL))
					booleanLiterals.add(currBL);
			}
		}
		numberOfLiterals = booleanLiterals.size();
		for (int i = 0; i < numberOfLiterals; i++)
		{
			IBooleanLiteral currBL = booleanLiterals.get(i);
			Integer objSat4jLiteral = new Integer(
					(currBL.isBarred() ? -1 : 1) * getSat4jVariable(currBL.getBooleanVariable()));

			Sat4j.getDirectoryLit().add(currBL, objSat4jLiteral);

		}
	}

	static void DIMACSBVassign(IProblem problem)
	{
		// Assign according to DIMACS format a unique integer to each
		// positronic.satisfiability.elements.IBooleanVariable found in problem

		List<IBooleanVariable> booleanVariables = null;
		try
		{
			ArrayList<IBooleanVariable> bvArrayList = new ArrayList<IBooleanVariable>();
			for (int i = 0; i < problem.size(); i++)
			{
				IClause clause = problem.getClause(i);
				if (clause != null)
					for (int j = 0; j < clause.size(); j++)
					{
						IBooleanVariable bv = ((Clause) clause).getLiteralAt(j).getBooleanVariable();
						if (!bvArrayList.contains(bv))
							bvArrayList.add(bv);
					}
			}
			booleanVariables = bvArrayList;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		Sat4j.setNumberOfVariables(booleanVariables.size());
		Sat4j.getDirectoryVar().setSat4jVariable2BV(new IBooleanVariable[Sat4j.getNumberOfVariables()]);
		for (int i = 0; i < Sat4j.getNumberOfVariables(); i++)
		{
			Sat4j.getDirectoryVar().add(booleanVariables, i);
		}
	}

	static IBooleanLiteral getBL(int literal)
	{
		return (Sat4j.getDirectoryLit().getSat4jLiteral2BL().get(new Integer(literal)));
	}

	public static LiteralDirectory getDirectoryLit()
	{
		return directoryLit;
	}

	public static VariableDirectory getDirectoryVar()
	{
		return directoryVar;
	}

	public static int getNumberOfVariables()
	{
		return numberOfVariables;
	}

	private static int getSat4jLiteral(IBooleanLiteral il)
	{
		return (getDirectoryLit().getBL2sat4jLiteral().get(il)).intValue();
	}

	private static int getSat4jVariable(IBooleanVariable ib)
	{
		return (Sat4j.getDirectoryVar().getBV2sat4jVariable().get(ib)).intValue();
	}

	public static ISolver getSolver()
	{
		return solver;
	}

	public static void init(org.sat4j.specs.ISolver solver)
	{
		initialize();
		setSolver(solver);
	}

	public static void initialize()
	{
		setDirectoryLit(
				new LiteralDirectory(new HashMap<IBooleanLiteral, Integer>(), new HashMap<Integer, IBooleanLiteral>()));
		setDirectoryVar(new VariableDirectory(new HashMap<IBooleanVariable, Integer>(), null));
	}

	public static void setDirectoryLit(LiteralDirectory directoryLit)
	{
		Sat4j.directoryLit = directoryLit;
	}

	public static void setDirectoryVar(VariableDirectory directoryVar)
	{
		Sat4j.directoryVar = directoryVar;
	}

	public static void setNumberOfVariables(int numberOfVariables)
	{
		Sat4j.numberOfVariables = numberOfVariables;
	}

	public static void setSolver(ISolver solver)
	{
		Sat4j.solver = solver;
	}
}

class VariableDirectory
{
	private Map<IBooleanVariable, Integer> BV2sat4jVariable = new HashMap<IBooleanVariable, Integer>();
	private IBooleanVariable[] sat4jVariable2BV = null;

	public VariableDirectory(Map<IBooleanVariable, Integer> bV2sat4jVariable, IBooleanVariable[] sat4jVariable2BV)
	{
		this.setBV2sat4jVariable(bV2sat4jVariable);
		this.setSat4jVariable2BV(sat4jVariable2BV);
	}

	public void add(List<IBooleanVariable> booleanVariables, int i)
	{
		this.getBV2sat4jVariable().put(booleanVariables.get(i), new Integer(i + 1));
		this.getSat4jVariable2BV()[i] = booleanVariables.get(i);
	}

	public Map<IBooleanVariable, Integer> getBV2sat4jVariable()
	{
		return BV2sat4jVariable;
	}

	public IBooleanVariable[] getSat4jVariable2BV()
	{
		return sat4jVariable2BV;
	}

	public void setBV2sat4jVariable(Map<IBooleanVariable, Integer> bV2sat4jVariable)
	{
		BV2sat4jVariable = bV2sat4jVariable;
	}

	public void setSat4jVariable2BV(IBooleanVariable[] sat4jVariable2BV)
	{
		this.sat4jVariable2BV = sat4jVariable2BV;
	}
}
