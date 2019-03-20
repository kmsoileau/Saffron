package demos.bits;

import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitArrayPartitionDemo
{
	public static void main(String[] args) throws Exception
	{
		int partitions;
		int bits;
		IProblem problem;
		IProblemMessage s;

		// IProblem-0
		System.out.println("IProblem-0");
		IBooleanVariable[][] partition = new IBooleanVariable[3][4];
		partitions = partition.length;
		bits = partition[0].length;
		for (int i = 0; i < partitions; i++)
		{
			partition[i] = new IBooleanVariable[bits];
			for (int j = 0; j < bits; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		// System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < partitions; i++)
			{
				String str = "";
				for (int j = 0; j < bits; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");

		// IProblem-1
		System.out.println("IProblem-1");
		partition = new IBooleanVariable[3][1];
		partitions = partition.length;
		bits = partition[0].length;
		for (int i = 0; i < partitions; i++)
		{
			partition[i] = new IBooleanVariable[bits];
			for (int j = 0; j < bits; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		// System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < partitions; i++)
			{
				String str = "";
				for (int j = 0; j < bits; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");

		// IProblem-2
		System.out.println("IProblem-2");
		partition = new IBooleanVariable[1][4];
		partitions = partition.length;
		bits = partition[0].length;
		for (int i = 0; i < partitions; i++)
		{
			partition[i] = new IBooleanVariable[bits];
			for (int j = 0; j < bits; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		// System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < partitions; i++)
			{
				String str = "";
				for (int j = 0; j < bits; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");

		// IProblem-3
		System.out.println("IProblem-3");
		partition = new IBooleanVariable[1][0];
		partitions = partition.length;
		bits = partition[0].length;
		for (int i = 0; i < partitions; i++)
		{
			partition[i] = new IBooleanVariable[bits];
			for (int j = 0; j < bits; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		// System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < partitions; i++)
			{
				String str = "";
				for (int j = 0; j < bits; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");

		// IProblem-4
		System.out.println("IProblem-4");
		partition = new IBooleanVariable[15][16];
		partitions = partition.length;
		bits = partition[0].length;
		for (int i = 0; i < partitions; i++)
		{
			partition[i] = new IBooleanVariable[bits];
			for (int j = 0; j < bits; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		// System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < partitions; i++)
			{
				String str = "";
				for (int j = 0; j < bits; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");

		System.out.println("Finis.");
	}
}
