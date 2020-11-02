package showcase.binpacking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import naturalnumbers.binpacking.Bin;
import naturalnumbers.binpacking.BinPacker;
import naturalnumbers.binpacking.Item;

public class BinPackingDemo7
{
	public static void main(String[] args) throws Exception
	{
		long[] weights = new long[]
		{ 4L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L };

		Item[] items = new Item[weights.length];
		for (int i = 0; i < items.length; i++)
			items[i] = new Item("Item-" + i, weights[i]);

		Bin[] bins = new Bin[6];
		for (int i = 0; i < bins.length; i++)
			bins[i] = new Bin("Bin-0" + i, 5L);

		ArrayList<ArrayList<Item>> solution = BinPacker.pack(items, bins);

		System.out.println(BinPacker.getProblem());
		// System.out.println(solution);

		HashMap<Item, Integer> bin = new HashMap<Item, Integer>();
		for (int i = 0; i < bins.length; i++)
		{
			ArrayList<Item> q = solution.get(i);
			for (int it = 0; it < q.size(); it++)
			{
				Item item = q.get(it);
				bin.put(item, i);
			}
		}
		// System.out.println(bin);

		Set<Item> itemSet = bin.keySet();
		Item[] ary = itemSet.toArray(new Item[0]);
		System.out.println("Item" + "\t\t" + "Weight" + "\t" + "Bin");
		System.out.println("____" + "\t\t" + "______" + "\t" + "___");
		for (Item it : ary)
		{
			Integer curr = bin.get(it);
			System.out.println(it.getName() + "\t\t" + it.getSize() + "\t" + curr);
		}
	}
}
