package demos.bits;

import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Partition;
import bits.Problem;

public class PartitionDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		int partitions = 3;
		int bits = 31;
		
		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		Partition partition = new Partition(partitions, bits);

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem=new BitArrayPartition(partition);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < partitions; i++)
			{
				String str = "";
				for (int j = 0; j < bits; j++)
					str += partition.getBV(i, j).getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		
		
		

		

		

		
	}
}
