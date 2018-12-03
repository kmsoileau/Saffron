package bits;

import java.util.ArrayList;

import exceptions.bits.BitArrayPartitionException;

/**
 * For example, suppose <code>partition</code> is as follows:
 * 
 * <code><p>
 * ____________|___0___1___2___3___4_
 * <p>
 * partition[0]| x00 x01 x02 x03 x04
 * <p>
 * partition[1]| x10 x11 x12 x13 x14
 * <p>
 * partition[2]| x20 x21 x22 x23 x24
 * <p>
 * </code>
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
	public BitArrayPartition(IBooleanVariable[][] partition) throws Exception
	{
		if (partition == null)
			throw new BitArrayPartitionException(
					"A null partition was passed to constructor.");
		int partitions = partition.length;
		if (partitions == 0)
			throw new BitArrayPartitionException(
					"A partition of zerolength was passed to constructor.");
		if (partition[0] == null)
			throw new BitArrayPartitionException(
					"A partition with partition[0]==null was passed to constructor.");
		int bits = partition[0].length;

		@SuppressWarnings("unchecked")
		ArrayList<IBooleanVariable>[] bitArrayList = new ArrayList[bits];
		IProblem[] p = new IProblem[bits];

		for (int j = 0; j < bits; j++)
		{
			bitArrayList[j] = new ArrayList<IBooleanVariable>();
			for (int i = 0; i < partitions; i++)
				bitArrayList[j].add(partition[i][j]);
			p[j] = new BitExclusiveSelector(bitArrayList[j]);
		}

		IProblem problem = new Conjunction(p);
		this.setClauses(problem.getClauses());
	}
}