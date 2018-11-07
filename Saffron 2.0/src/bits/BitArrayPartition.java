package bits;

import java.util.ArrayList;

/**
 * 
 * 
 * For example, suppose <code>partition</code> is as follows:
 * 
 * 
 * <pre>
 *                0   1   2   3   4
 *               ------------------- 
 * partition[0]: x00 x01 x02 x03 x04
 * partition[1]: x10 x11 x12 x13 x14
 * partition[2]: x20 x21 x22 x23 x24
 * 
 * where the x's are IBooleanVariables.
 * 
 * <pre>
 * then new BitArrayPartition(<code>partition</code>) returns an <code>IProblem</code> that imposes the following:
 * 
 * Exactly one of these {new BitFixer(x00,true), new BitFixer(x10,true), new BitFixer(x20,true)} is satisfied
 * and
 * exactly one of these {new BitFixer(x01,true), new BitFixer(x11,true), new BitFixer(x21,true)} is satisfied)
 * and
 * exactly one of these {new BitFixer(x02,true), new BitFixer(x12,true), new BitFixer(x22,true)} is satisfied)
 * and
 * exactly one of these {new BitFixer(x03,true), new BitFixer(x13,true), new BitFixer(x23,true)} is satisfied)
 * and
 * exactly one of these {new BitFixer(x04,true), new BitFixer(x14,true), new BitFixer(x24,true)} is satisfied)
 * 
 * This class is useful for partitioning a set into several disjoint subsets.
 * 
 * @author Kerry Michael Soileau
 * @version 1.0, 2018/11/05
 * 
 */
public class BitArrayPartition extends Problem implements IProblem
{
	private static final long serialVersionUID = 4725109314250454123L;

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