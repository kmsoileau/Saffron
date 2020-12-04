package bits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ISolver;

import bits.exceptions.ProblemException;
import positronic.util.arrays.CompoundList;
import positronic.util.arrays.ImmutableList;

/**
 * A class which represents a collection of IClause objects, and which amounts
 * to a satisfiability problem. Problem is essentially a ArrayList of IClause
 * objects, and additionally provides several useful methods for combining
 * Problem objects, especially performing logical operations such as
 * <code>and</code> and <code>or</code> on such objects.
 * 
 * This class is the superclass of numerous generic satisfiability problems.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.41
 * @since 2004/08/26
 */
public class Problem implements IProblem
{
	public static ISolver defaultSolver()
	{
		return SolverFactory.newMiniSATHeap();
	}

	public static IProblem newProblem()
	{
		return new Problem();
	}

	private ImmutableList backing = new CompoundList();

	/**
	 * Constructs an empty Problem, that is, an instance of Problem which contains
	 * no IClauses.
	 * 
	 */
	public Problem()
	{
	}

	/**
	 * Constructs an instance of Problem which contains the IClauses found in the
	 * parameter clause.
	 * 
	 * @param clause the array of IClauses to comprise the instance of Problem.
	 * @throws Exception An instance of Exception.
	 */
	public Problem(IClause[] clause) throws Exception
	{
		this.setClauses(clause);
	}

	public Problem(IProblem iproblem) throws Exception
	{
		this.backing = ((Problem) iproblem).backing;
	}

	public Problem and(Problem p) throws Exception
	{
		return new Conjunction(this, p);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		IProblem cln = new Problem();
		try
		{
			cln.setClauses(this.getClauses());
		} catch (Exception e)
		{
			throw (new CloneNotSupportedException());
		}
		return cln;
	}

	@Override
	public boolean contains(IClause c) throws Exception
	{
		if (c == null)
			return false;
		for (int i = 0; i < this.size(); i++)
		{
			IClause curr = this.getClause(i);
			if (curr == null)
				continue;
			if (curr.equals(c))
				return true;
		}
		return false;
	}

	public IProblemMessage findModel() throws Exception
	{
		return findModel(Problem.defaultSolver());
	}

	@Override
	public IProblemMessage findModel(ISolver solver) throws Exception
	{
		if (this.size() == 0)
			throw new ProblemException("Empty IProblem was passed to findModel method.");
		Sat4j.init(solver);
		org.sat4j.specs.IProblem sat4jproblem = KSatReader.parseInstance(this);
		if (!sat4jproblem.isSatisfiable())
			return new ProblemMessage(IProblemMessage.UNSATISFIABLE, new ArrayList<IBooleanLiteral>());
		ArrayList<IBooleanLiteral> rl = KSatReader.toBooleanLiterals(sat4jproblem.model());
		IProblem test = this.resolve(rl);
		if (test.size() > 0 || rl.size() == 0)
			return new ProblemMessage(IProblemMessage.UNSATISFIABLE, new ArrayList<IBooleanLiteral>());
		return new ProblemMessage(IProblemMessage.SATISFIABLE, rl);
	}

	public IProblemMessage[] findTwoModels(IBooleanVariable b) throws Exception
	{
		IProblemMessage[] res = new IProblemMessage[2];
		if (!this.getBooleanVariables().contains(b))
			throw new ProblemException("The given IProblem does not depend upon the given IBooleanVariable.");
		else
		{
			IProblem p1 = new Conjunction(this, new BitFixer(b, false));
			IProblem p2 = new Conjunction(this, new BitFixer(b, true));
			res[0] = p1.findModel(Problem.defaultSolver());
			res[1] = p2.findModel(Problem.defaultSolver());
		}
		return res;
	}

	@Override
	public ArrayList<IBooleanVariable> getBooleanVariables() throws Exception
	{
		ArrayList<IBooleanVariable> hs = new ArrayList<IBooleanVariable>();
		for (int i = 0; i < this.size(); i++)
		{
			IClause curr = this.getClause(i);
			if (curr != null)
			{
				IBooleanVariable[] currObjArray = curr.getBooleanVariables();
				for (int j = 0; j < currObjArray.length; j++)
					if (!hs.contains(currObjArray[j]))
						hs.add(currObjArray[j]);
			}
		}
		return hs;
	}

	@Override
	public IClause getClause(int n)
	{
		ImmutableList b = this.backing;
		Object r = b.get(n);
		return (IClause) r;
	}

	@Override
	public IClause[] getClauses()
	{
		return (IClause[]) this.backing.toArray(new IClause[0]);
	}

	public boolean isEmpty()
	{
		return (this.size() == 0);
	}

	@Override
	public Iterator<Object> iterator()
	{
		return this.backing.iterator();
	}

	@Override
	public IProblem resolve(List<IBooleanLiteral> ib) throws Exception
	{
		List<IClause> res = Arrays.asList(((IProblem) this.clone()).getClauses());

		for (int i = 0; i < res.size(); i++)
		{
			IClause c = res.get(i);

			for (int j = 0; j < ib.size(); j++)
			{
				if (c == null)
					break;
				IBooleanLiteral ibcurr = ib.get(j);
				IClause newcl = null;
				try
				{
					newcl = ((Clause) c).resolve(ibcurr.getBooleanVariable(), !ibcurr.isBarred());
				} catch (NullPointerException err)
				{
				}

				c = newcl;
			}
			res.set(i, c);
		}

		int pos = 0;
		while (pos < res.size())
		{
			if (res.get(pos) != null)
				pos++;
			else if (res.get(0) == null)
			{
				return Problem.newProblem();
			}
			else
				res.remove(pos);
		}

		return new Problem(res.toArray(new IClause[0]));
	}

	public void setBacking(ImmutableList backing)
	{
		this.backing = backing;
	}

	@Override
	public void setClauses(IClause[] c) throws Exception
	{
		if (c == null || c.length == 0)
			return;
		this.backing = new CompoundList(c);
	}

	public void setClauses(List<IClause> list) throws Exception
	{
		// if (this.size() > 0)
		// this.removeAllClauses();
		if (list != null)
			this.backing = new CompoundList(list.toArray());
	}

	@Override
	public int size()
	{
		return this.backing.size();
	}

	public boolean solve(ISolver solver) throws Exception
	{
		IProblemMessage s = this.findModel(solver);
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			return true;
		}
		else
			return false;
	}

	public String toDIMACS() throws Exception
	{
		int index = 1;
		HashMap<IBooleanVariable, Integer> lookup = new HashMap<IBooleanVariable, Integer>();
		for (IBooleanVariable curr : this.getBooleanVariables())
		{
			lookup.put(curr, index);
			System.out.println(curr.getName() + "->" + index);
			index++;
		}
		String ret = "p cnf " + this.getBooleanVariables().size() + " " + this.size() + "\n";
		for (IClause currClause : this.getClauses())
		{
			for (int i = 0; i < currClause.size(); i++)
			{
				IBooleanLiteral currBL = ((Clause) currClause).getLiteralAt(i);
				int num = lookup.get(currBL.getBooleanVariable());
				if (currBL.isBarred())
					ret += "-";
				ret += (num + " ");
			}
			ret += "0 \n";
		}
		return ret;
	}

	@Override
	public String toString()
	{
		String res = "\n..";
		for (int i = 0; i < this.size(); i++)
		{
			if (this.getClause(i) != null)
				res += "\n.. " + this.getClause(i).toString();
			else
				res += "\n.. " + "null";
		}
		res += "\n..";
		return res;
	}
}
