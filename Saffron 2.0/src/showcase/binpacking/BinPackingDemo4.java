package showcase.binpacking;

import java.util.ArrayList;

public class BinPackingDemo4
{
	public static void main(String[] args) throws Exception
	{
		long[] weights = new long[]
		{ 442L, 252L, 252L, 252L, 252L, 252L };

		Item[] items = new Item[weights.length];
		for (int i = 0; i < items.length; i++)
			items[i] = new Item("Item-" + i, weights[i]);

		Bin[] bins = new Bin[3];
		for (int i = 0; i < bins.length; i++)
			bins[i] = new Bin("Bin-0" + i, 694L);

		ArrayList<ArrayList<Item>> solution = BinPacker.pack(items, bins);

		System.out.println(BinPacker.getProblem());
		System.out.println(solution);
	}
}
