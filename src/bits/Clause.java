package bits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import bits.exceptions.ClauseException;

/**
 * A class which represents a satisfiability clause. IClause is essentially an
 * ArrayList of IBooleanLiteral objects, and additionally provides several
 * useful methods for manipulating and evaluating the truth value of an IClause.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.01
 * @since Aug 26, 2004
 */
public class Clause extends ArrayList<IBooleanLiteral> implements IClause
{
	private static final long serialVersionUID = -9088489304501148454L;

	public static IClause asClause(IBooleanLiteral[] bl) throws Exception
	{
		IClause ret = Clause.newClause();
		for (int i = 0; i < bl.length; i++)
		{
			((Clause) ret).add((BooleanLiteral) bl[i]);
		}
		return ret;
	}

	/**
	 * This static method returns a new IClause.
	 * 
	 * @return IClause
	 */
	public static IClause newClause()
	{
		return new Clause();
	}

	/**
	 * This static method returns a new IClause made up of a random selection of
	 * IBooleanLiterals created using IBooleanVariables in the given
	 * IBooleanVariable[].
	 * 
	 * @return IClause
	 * @throws Exception An instance of Exception
	 * @param bv IBooleanVariable[]
	 */
	public static IClause randomClause(IBooleanVariable[] bv) throws Exception
	{
		IClause ret = Clause.newClause();
		int number = bv.length;
		for (int i = 0; i < number; i++)
		{
			double r = Math.random();
			int choice = (int) (3 * r);
			if (choice == 0)
				;
			if (choice == 1)
				((Clause) ret).add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(bv[i], false));
			if (choice == 2)
				((Clause) ret).add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(bv[i], true));
		}
		return ret;
	}

	/**
	 * This method adds a IBooleanLiteral to this.
	 * 
	 * @return boolean
	 * @throws ClauseException An instance of Exception
	 * @throws Exception       An instance of Exception
	 * @param b BooleanLiteral
	 */
	public boolean add(BooleanLiteral b) throws Exception
	{
		if (b == null)
			throw new ClauseException("A null IBooleanLiteral was passed to the add method.");
		if (!this.contains(b))
		{
			super.add(b);
			return true;
		}
		else
			return false;
	}

	/**
	 * This method adds a IBooleanLiteral to this.
	 * 
	 * @throws ClauseException An instance of Exception
	 */
//	@Override
//	public void addLiteral(IBooleanLiteral bl) throws Exception
//	{
//		if (bl == null)
//			throw new ClauseException("Null IBooleanLiteral was passed to addLiteral method.");
//		this.add(bl);
//	}

	/**
	 * This method compares this to an IClause. The comparison is first done
	 * according to size, then on the "lowest" IBooleanLiteral in each IClause.
	 * 
	 * @return int
	 * @throws ClauseException An instance of Exception
	 * @param o IClause
	 */
	public int compareTo(IClause o) throws Exception
	{
		if (o == null)
			throw new ClauseException("A null Object was passed to the compareTo method.");

		int d = super.size() - ((ArrayList<?>) o).size();
		if (d != 0)
			return d;
		else
		{
			Object[] thisIt = this.getBooleanLiterals();
			Arrays.sort(thisIt);
			IBooleanLiteral thisFirst = (IBooleanLiteral) (thisIt[0]);
			Object[] oIt = o.getBooleanLiterals();
			Arrays.sort(oIt);
			IBooleanLiteral oFirst = (IBooleanLiteral) (oIt[0]);
			return thisFirst.compareTo(oFirst);
		}
	}

	/**
	 * This method compares this to an Object. The comparison is first done
	 * according to size, then on the "lowest" IBooleanLiteral in each IClause.
	 * 
	 * @return int
	 */
	@Override
	public int compareTo(Object o)
	{
		try
		{
			return compareTo(o);
		} catch (Exception e)
		{
			System.out.println("The compareTo method failed on Object " + o + ".");
			e.printStackTrace();
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * This method determines whether a given IBooleanLiteral is a member of this.
	 * 
	 * @return boolean
	 * @throws ClauseException An instance of Exception
	 * @param bl IBooleanLiteral
	 */
	public boolean contains(IBooleanLiteral bl) throws Exception
	{
		if (bl == null)
			throw new ClauseException("A null IBooleanLiteral was passed to the contains method.");

		Iterator<IBooleanLiteral> it = this.iterator();
		while (it.hasNext())
		{
			Object obj = it.next();
			IBooleanLiteral blc = (IBooleanLiteral) obj;
			if (blc.equals(bl))
				return true;
		}
		return false;
	}

	/**
	 * This method determines whether a given IBooleanVariable appears in this.
	 * 
	 * @return boolean
	 * @throws ClauseException An instance of Exception
	 * @param bv IBooleanVariable
	 */
	public boolean contains(IBooleanVariable bv) throws Exception
	{
		if (bv == null)
			throw new ClauseException("A null IBooleanVariable was passed to the contains method.");

		Iterator<IBooleanLiteral> it = this.iterator();
		while (it.hasNext())
		{
			Object obj = it.next();
			IBooleanLiteral blc = (IBooleanLiteral) obj;
			if (blc.getBooleanVariable().equals(bv))
				return true;
		}
		return false;
	}

	/**
	 * This method determines whether a given IClause is true.
	 * 
	 * @return boolean
	 */
	public boolean evaluate()
	{
		Iterator<IBooleanLiteral> it = this.iterator();

		while (it.hasNext())
			if ((it.next()).evaluate())
				return true;

		return false;
	}

	@Override
	public IBooleanLiteral[] getBooleanLiterals()
	{
		return super.toArray(new IBooleanLiteral[0]);
	}

	/**
	 * This method returns an Object[] of the IBooleanVariables appearing in this.
	 * 
	 * @return Object[]
	 */
	@Override
	public IBooleanVariable[] getBooleanVariables()
	{
		return this.getBooleanVariablesList().toArray(new IBooleanVariable[0]);
	}

	/**
	 * This method returns an ArrayList of the IBooleanVariables appearing in this.
	 * 
	 * @return ArrayList
	 */
	public ArrayList<IBooleanVariable> getBooleanVariablesList()
	{
		ArrayList<IBooleanVariable> res = new ArrayList<IBooleanVariable>();
		Object[] bl = this.getBooleanLiterals();
		// for (int i = 0; i < bl.length; i++)
		for (int i = 0; i < bl.length; i++)
		{
			IBooleanVariable curr = ((IBooleanLiteral) bl[i]).getBooleanVariable();
			if (!res.contains(curr))
				res.add(curr);
		}

		return res;
	}

	/**
	 * This method returns the IBooleanLiteral at a given index position in this.
	 * 
	 * @return IBooleanLiteral
	 * @throws ClauseException An instance of Exception
	 */
	@Override
	public IBooleanLiteral getLiteralAt(int n) throws ClauseException
	{
		if (n < 0)
			throw new ClauseException("A negative number was passed to getLiteralAt method.");
		return (super.get(n));
	}

	@Override
	public Iterator<IBooleanLiteral> iterator()
	{
		return super.iterator();
	}

	@Override
	public IClause or(IBooleanVariable bv) throws Exception
	{
		if (bv == null)
			throw new ClauseException("A null IBooleanVariable was passed to the or method.");

		this.add(BooleanLiteral.getBooleanLiteral(bv, false));
		return this;
	}

	@Override
	public IClause orNot(IBooleanVariable bv) throws Exception
	{
		if (bv == null)
			throw new ClauseException("A null IBooleanVariable was passed to the orNot method.");

		this.add(BooleanLiteral.getBooleanLiteral(bv, true));
		return this;
	}

	@Override
	public boolean remove(IBooleanLiteral b)
	{
		return super.remove(b);
	}

//	@Override
//	public IClause resolve(IBooleanLiteral ib) throws Exception
//	{
//		if (ib == null)
//			throw new ClauseException("Null IBooleanLiteral was passed to resolve method.");
//
//		return this.resolve(ib.getBooleanVariable(), !ib.isBarred());
//	}

	@Override
	public IBooleanLiteral removeBooleanLiteral(int i)
	{
		return super.remove(i);
	}

	// An empty IClause ={} cannot be satisfied.
	// A null IClause =null is satisfied trivially.
	public IClause resolve(IBooleanVariable b, boolean value) throws Exception
	{
		if (b == null)
			throw new ClauseException("Null IBooleanVariable was passed to resolve method.");
		IClause c = (IClause) (this.clone());
		Iterator<IBooleanLiteral> it = this.iterator();
		while (it.hasNext())
		{
			Object obj = it.next();
			IBooleanLiteral bl = (IBooleanLiteral) obj;
			if (bl.getBooleanVariable().equals(b))
				if (bl.isBarred() == value)
					c.remove(bl);
				else
				{
					c = null;
					break;
				}
		}
		return c;
		/*
		 * if(!(c.isEmpty())) return c; else return null;
		 */
	}

	@Override
	public int size()
	{
		return super.size();
	}

	public IProblem ThreeSATProblem() throws Exception
	{
		if (this.size() < 4)
			return (new Problem(new IClause[]
			{ this }));

		Clause left = new Clause();
		left.add(this.getLiteralAt(0));
		left.add(this.getLiteralAt(1));
		Clause right = new Clause();
		for (int i = 2; i < this.size(); i++)
		{
			right.add(this.getLiteralAt(i));
		}
		IBooleanVariable bv = BooleanVariable.getBooleanVariable();
		IBooleanLiteral blunbarred = BooleanLiteral.getBooleanLiteral(bv, false);
		IBooleanLiteral blbarred = BooleanLiteral.getBooleanLiteral(bv, true);
		left.add(blunbarred);
		right.add(blbarred);
		IProblem problem = new Conjunction(left.ThreeSATProblem(), right.ThreeSATProblem());
		return problem;
	}

	public HashMap<IBooleanVariable, Integer> toMap()
	{
		if (this.size() < 1)
			return null;
		HashMap<IBooleanVariable, Integer> map = new HashMap<IBooleanVariable, Integer>();
		for (IBooleanLiteral bl : this)
		{
			map.put(bl.getBooleanVariable(), bl.isBarred() ? 2 : 1);
		}

		return map;
	}

	@Override
	public String toString()
	{
		String res = "{";
		Object[] obj = this.getBooleanLiterals();
		for (int j = 0; j < obj.length; j++)
			res += obj[j];
		return res + "}";
	}
}