package bits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

import exceptions.bits.UnsolvableProblemException;

/**
 * A reader which is created using an object of type
 * <code>positronic.satisfiability.elements.ISolver</code> and has a method
 * <code>parseInstance</code> which translates a
 * <code>positronic.satisfiability.elements.IProblem</code> object into a
 * corresponding <code>org.sat4j.specs.IProblem</code> object.
 * 
 * For example, here's how a <code>KSatReader</code> might be used on a
 * <code>positronic.satisfiability.elements.IProblem</code> called
 * <code>problem</code>:
 * 
 * <p>
 * <code>...</code>
 * </p>
 * <p>
 * <code>ISolver solver = ... // some solver from a solver factory</code>
 * </p>
 * <p>
 * <code>KSatReader reader = new KSatReader(solver);</code>
 * </p>
 * <p>
 * <code>positronic.satisfiability.elements.IProblem problem = ... // some problem from KSat API</code>
 * </p>
 * <p>
 * <code>org.sat4j.specs.IProblem sat4jproblem = reader.parseInstance(problem);</code>
 * </p>
 * <p>
 * <code>if (sat4jproblem.isSatisfiable()) {</code>
 * </p>
 * <p>
 * <code>...</code>
 * </p>
 * <p>
 * <code>}</code>
 * </p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since 2006/01/29
 * 
 * @see org.sat4j.core.VecInt
 * @see org.sat4j.specs.IVecInt
 */
public class KSatReader
{
	private Map<IBooleanLiteral, Integer> BL2sat4jLiteral = new HashMap<IBooleanLiteral, Integer>();
	private List<IBooleanLiteral> booleanLiterals = new ArrayList<IBooleanLiteral>();
	private List<IBooleanVariable> booleanVariables = null;
	private Map<IBooleanVariable, Integer> BV2sat4jVariable = new HashMap<IBooleanVariable, Integer>();
	private int numberOfLiterals = 0;
	private int numberOfVariables = 0;
	private Map<Integer, IBooleanLiteral> sat4jLiteral2BL = new HashMap<Integer, IBooleanLiteral>();
	private IBooleanVariable[] sat4jVariable2BV = null;
	private ISolver solver = null;

	public KSatReader(ISolver solver)
	{
		this.solver = solver;
	}

	public String decode(int[] dimacs)
	{
		IClause cl = new Clause();
		for (int i = 0; i < dimacs.length; i++)
		{
			try
			{
				cl.add((BooleanLiteral) this.getBL(dimacs[i]));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return cl.toString();
	}

	public IBooleanLiteral getBL(int literal)
	{
		return (sat4jLiteral2BL.get(new Integer(literal)));
	}

	public List<IBooleanLiteral> getBooleanLiteralsArrayList()
	{
		return booleanLiterals;
	}

	public List<IBooleanVariable> getBooleanVariablesArrayList()
	{
		return booleanVariables;
	}

	public IBooleanVariable getBV(int variable)
	{
		return sat4jVariable2BV[variable - 1];
	}

	public int getNumberOfLiterals()
	{
		return numberOfLiterals;
	}

	public int getNumberOfVariables()
	{
		return numberOfVariables;
	}

	public int getSat4jLiteral(IBooleanLiteral il)
	{
		return (BL2sat4jLiteral.get(il)).intValue();
	}

	public int getSat4jVariable(IBooleanVariable ib)
	{
		return (BV2sat4jVariable.get(ib)).intValue();
	}

	public org.sat4j.specs.IProblem parseInstance(IProblem problem)
			throws UnsolvableProblemException
	{
		// Assign according to DIMACS format a unique integer to each
		// positronic.satisfiability.elements.IBooleanVariable found in problem
		try
		{
			ArrayList<IBooleanVariable> bvArrayList = new ArrayList<IBooleanVariable>();
			for (int i = 0; i < problem.numberOfClauses(); i++)
			{
				IClause clause = problem.getClause(i);
				if (clause != null)
					for (int j = 0; j < clause.size(); j++)
					{
						IBooleanVariable bv = clause.getLiteralAt(j)
								.getBooleanVariable();
						if (!bvArrayList.contains(bv))
							bvArrayList.add(bv);
					}
			}
			this.booleanVariables = bvArrayList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		this.numberOfVariables = getBooleanVariablesArrayList().size();
		sat4jVariable2BV = new IBooleanVariable[this.numberOfVariables];
		for (int i = 0; i < this.numberOfVariables; i++)
		{
			BV2sat4jVariable.put(getBooleanVariablesArrayList().get(i),
					new Integer(i + 1));
			sat4jVariable2BV[i] = getBooleanVariablesArrayList().get(i);
		}

		// Assign according to DIMACS format a unique integer to each
		// positronic.satisfiability.elements.IBooleanLiteral found in problem
		for (int i = 0; i < problem.getClauses().size(); i++)
		{
			IClause currClause = problem.getClause(i);
			for (int j = 0; j < currClause.size(); j++)
			{
				IBooleanLiteral currBL = null;
				try
				{
					currBL = currClause.getLiteralAt(j);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				if (!getBooleanLiteralsArrayList().contains(currBL))
					getBooleanLiteralsArrayList().add(currBL);
			}
		}
		this.numberOfLiterals = getBooleanLiteralsArrayList().size();
		for (int i = 0; i < this.numberOfLiterals; i++)
		{
			IBooleanLiteral currBL = (getBooleanLiteralsArrayList().get(i));
			Integer objSat4jLiteral = new Integer((currBL.isBarred() ? -1 : 1)
					* this.getSat4jVariable(currBL.getBooleanVariable()));
			BL2sat4jLiteral.put(currBL, objSat4jLiteral);
			sat4jLiteral2BL.put(objSat4jLiteral, currBL);
		}
		this.solver.newVar(this.numberOfVariables);
		for (int i = 0; i < problem.size(); i++)
		{
			IClause currClause = problem.getClause(i);
			IVecInt currIvi = new VecInt();
			for (int j = 0; j < currClause.size(); j++)
			{
				IBooleanLiteral currBL = null;
				try
				{
					currBL = currClause.getLiteralAt(j);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				currIvi.push(this.getSat4jLiteral(currBL));
			}

			try
			{
				this.solver.addClause(currIvi);
			}
			catch (ContradictionException e)
			{
				int[] d = new int[currIvi.size()];
				currIvi.copyTo(d);
			}
		}
		return this.solver;
	}

	public ArrayList<IBooleanLiteral> toBooleanLiterals(int[] dimacs)
	{
		ArrayList<IBooleanLiteral> cl = new ArrayList<IBooleanLiteral>();
		for (int i = 0; i < dimacs.length; i++)
		{
			try
			{
				cl.add(this.getBL(dimacs[i]));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return cl;
	}
}
