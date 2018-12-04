package demos.scheduling;

import java.util.ArrayList;

import naturalnumbers.NaturalNumber;

public class JobSchedulingProblem2
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(1000);

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

		ArrayList<ArrayList<Task>> solution = Scheduler.schedule(tasks, procs, 51);
		if (solution != null)
		{
			//System.out.println(Scheduler.getProblem().toDIMACS());
			for (int i = 0; i < procs.length; i++)
			{
				ArrayList<Task> qq = solution.get(i);
				System.out.println(procs[i].getName() + ": " + qq);
			}
		}
	}
}
