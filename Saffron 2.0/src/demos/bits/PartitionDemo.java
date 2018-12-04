package demos.bits;

import java.util.List;

import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.Partition;
import bits.Problem;

public class PartitionDemo
{

	public static void main(String[] args) throws Exception
	{
		int partitions = 3;
		int bits = 4;

		Partition partition = new Partition(partitions, bits);

		List<IBooleanLiteral> s = new BitArrayPartition(partition)
				.findModel(Problem.defaultSolver());
		
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < partitions; i++)
			{
				String str = "";
				for (int j = 0; j < bits; j++)
					str += partition.getBV(i, j).getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}
