package showcase.scheduling;

import java.util.ArrayList;

import bits.BooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Partition;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.scheduling.Processor;
import naturalnumbers.scheduling.Scheduler;
import naturalnumbers.scheduling.Task;

public class JobSchedulingProblem2
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(60);

		Task FW = new Task("FW", 17);
		Task FP = new Task("FP", 27);
		Task FZ = new Task("FZ", 7);

		Task[] tasks = new Task[]
		{ FP, FW, FZ };
		FP.setPredecessors(new Task[]
		{ FW, FZ });
		FZ.setPredecessors(new Task[]
		{ FW });

		Processor A1 = new Processor("A1");
		Processor A2 = new Processor("A2");

		Processor[] procs = new Processor[]
		{ A1, A2 };

		int numberProcs = procs.length;
		int numberTasks = tasks.length;
		Partition partition = new Partition(numberProcs, numberTasks);

		IProblem jobSchedulingProblem = new Scheduler(tasks, procs, 51, partition);

		IProblemMessage blList = jobSchedulingProblem.findModel(Problem.defaultSolver());

		ArrayList<ArrayList<Task>> solution = null;
		if (blList.getLiterals() != null && blList.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(blList.getLiterals());
			solution = new ArrayList<ArrayList<Task>>();
			for (int i = 0; i < numberProcs; i++)
			{
				IBooleanVariable[] currentProc = partition.getSet(i);
				ArrayList<Task> currentProcAssignments = new ArrayList<Task>();
				for (int j = 0; j < numberTasks; j++)
					if (currentProc[j].getValue())
						currentProcAssignments.add(tasks[j]);
				solution.add(currentProcAssignments);
			}
		}

		if (solution != null)
		{
			// System.out.println(Scheduler.getProblem().toDIMACS());
			for (int i = 0; i < procs.length; i++)
			{
				ArrayList<Task> qq = solution.get(i);
				System.out.println(procs[i].getName() + ": " + qq);
			}
		}
	}
}
