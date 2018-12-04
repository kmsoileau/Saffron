package bits;

import java.util.ArrayList;

import exceptions.bits.BitArrayPartitionException;

/**
 * For example, suppose <code>partition</code> is as follows:
 * 
 * <blockquote>
 * ____________|__0___1___2___3___4_
 * <p>
 * partition[0]| x00 x01 x02 x03 x04
 * <p>
 * partition[1]| x10 x11 x12 x13 x14
 * <p>
 * partition[2]| x20 x21 x22 x23 x24
 * <p>
 * </blockquote>
 * 
 * where the <code>x</code>'s are <code>IBooleanVariable</code>s. then new
 * <code>BitArrayPartition(
 * partition</code>) returns an <code>IProblem</code> that imposes the
 * following:
 * <p>
 * Exactly one of these
 * <p>
 * <code>{new BitFixer(x00,true), new BitFixer(x10,true), new BitFixer(x20,true)}</code>
 * <p>
 * is satisfied
 * <p>
 * and exactly one of these
 * <p>
 * <code>{new BitFixer(x01,true), new BitFixer(x11,true), new BitFixer(x21,true)}</code>
 * <p>
 * is satisfied
 * <p>
 * and exactly one of these
 * <p>
 * <code>{new BitFixer(x02,true), new BitFixer(x12,true), new BitFixer(x22,true)}</code>
 * <p>
 * is satisfied
 * <p>
 * and exactly one of these
 * <p>
 * <code>{new BitFixer(x03,true), new BitFixer(x13,true), new BitFixer(x23,true)}</code>
 * <p>
 * is satisfied
 * <p>
 * and exactly one of these
 * <p>
 * <code>{new BitFixer(x04,true), new BitFixer(x14,true), new BitFixer(x24,true)}</code>
 * <p>
 * is satisfied.
 * <p>
 * This class is useful for partitioning a set into several disjoint subsets.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/11/05
 */
public class BitArrayPartition extends Problem implements IProblem
{
	public BitArrayPartition(IBooleanVariable[][] bvArray) throws Exception
	{
		if (bvArray == null)
			throw new BitArrayPartitionException(
					"A null partition was passed to constructor.");
		int partitions = bvArray.length;
		if (partitions == 0)
			throw new BitArrayPartitionException(
					"A partition of zerolength was passed to constructor.");
		if (bvArray[0] == null)
			throw new BitArrayPartitionException(
					"A partition with partition[0]==null was passed to constructor.");
		int bits = bvArray[0].length;

		ArrayList<ArrayList<IBooleanVariable>> bitArrayList = new ArrayList<ArrayList<IBooleanVariable>>();
		IProblem[] p = new IProblem[bits];

		for (int j = 0; j < bits; j++)
		{
			ArrayList<IBooleanVariable> curr = new ArrayList<IBooleanVariable>();
			bitArrayList.add(curr);
			for (int i = 0; i < partitions; i++)
				curr.add(bvArray[i][j]);
			p[j] = new BitExclusiveSelector(curr);
		}

		IProblem problem = new Conjunction(p);
		this.setClauses(problem.getClauses());
	}

	public BitArrayPartition(Partition partition) throws Exception
	{
		this(partition.getBackingArray());
	}
}