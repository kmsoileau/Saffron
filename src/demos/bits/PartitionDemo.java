package demos.bits;

import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.IProblemMessage;
import bits.Partition;
import bits.Problem;

public class PartitionDemo
{
	public static void main(String[] args) throws Exception
	{
		int partitions = 2;
		int bits = 3;

		Partition partition = new Partition(partitions, bits);

		IProblemMessage s = new BitArrayPartition(partition).findModel(Problem.defaultSolver());

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
