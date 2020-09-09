package demos.bits.strings;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 *
 *
 * IBitString bt1=new BitString();
 * bt1.getName() = BitString-0
 * bt1.getDataList() = []
 *
 * BitString bt2=new BitString("xyz",new boolean[0]);
 * bt2.getName() = xyz
 * bt2.getDataList() = []
 *
 * BitString bt3=new BitString(new boolean[3]);
 * bt3.getName() = BitString-1
 * bt3.getDataList() = [<BitString-1_0=false>, <BitString-1_1=false>, <BitString-1_2=false>]
 * bt3.data[0] = <BitString-1_0=false>
 * bt3.data[1] = <BitString-1_1=false>
 * bt3.data[2] = <BitString-1_2=false>
 *
 * BitString bt4=new BitString("xyz",new char[0]);
 * bt4.getName() = xyz
 * bt4.getDataList() = []
 *
 * BitString bt5=new BitString(new char[]{'0'});
 * bt5.getName() = BitString-2
 * bt5.getDataList() = [<BitString-2_0=false>]
 * bt5.getBooleanVariable(0) = <BitString-2_0=false>
 *
 * BitString bt6=new BitString(new BitString("xyz",new boolean[4]));
 * bt6.getName() = xyz
 * bt6.getDataList() = [<xyz_0=false>, <xyz_1=false>, <xyz_2=false>, <xyz_3=false>]
 * bt6.getBooleanVariable(0) = <xyz_0=false>
 * bt6.getBooleanVariable(1) = <xyz_1=false>
 * bt6.getBooleanVariable(2) = <xyz_2=false>
 * bt6.getBooleanVariable(3) = <xyz_3=false>
 *
 * BitString bt7=new BitString("xyz",new IBooleanVariable[0]);
 * bt7.getName() = xyz
 * bt7.getDataList() = []
 *
 * BitString bt8=new BitString(new IBooleanVariable[7]);
 * bt8.getName() = BitString-3
 * bt8.getDataList() = [<BitString-3_0=false>, <BitString-3_1=false>, <BitString-3_2=false>, <BitString-3_3=false>, <BitString-3_4=false>, <BitString-3_5=false>, <BitString-3_6=false>]
 * bt8.getBooleanVariable(0) = <BitString-3_0=false>
 * bt8.getBooleanVariable(1) = <BitString-3_1=false>
 * bt8.getBooleanVariable(2) = <BitString-3_2=false>
 * bt8.getBooleanVariable(3) = <BitString-3_3=false>
 * bt8.getBooleanVariable(4) = <BitString-3_4=false>
 * bt8.getBooleanVariable(5) = <BitString-3_5=false>
 * bt8.getBooleanVariable(6) = <BitString-3_6=false>
 *
 * BitString bt9=new BitString("xyz",6);
 * bt9.getName() = xyz
 * bt9.getDataList() = [<xyz_0=false>, <xyz_1=false>, <xyz_2=false>, <xyz_3=false>, <xyz_4=false>, <xyz_5=false>]
 * bt9.getBooleanVariable(0) = <xyz_0=false>
 * bt9.getBooleanVariable(1) = <xyz_1=false>
 * bt9.getBooleanVariable(2) = <xyz_2=false>
 * bt9.getBooleanVariable(3) = <xyz_3=false>
 * bt9.getBooleanVariable(4) = <xyz_4=false>
 * bt9.getBooleanVariable(5) = <xyz_5=false>
 *
 * BitString bt10=new BitString("01101101001");
 * bt10.getName() = BitString-4
 * bt10.getDataList() = [<BitString-4_0=false>, <BitString-4_1=true>, <BitString-4_2=true>, <BitString-4_3=false>, <BitString-4_4=true>, <BitString-4_5=true>, <BitString-4_6=false>, <BitString-4_7=true>, <BitString-4_8=false>, <BitString-4_9=false>, <BitString-4_10=true>]
 * bt10.getBooleanVariable(0) = <BitString-4_0=false>
 * bt10.getBooleanVariable(1) = <BitString-4_1=true>
 * bt10.getBooleanVariable(2) = <BitString-4_2=true>
 * bt10.getBooleanVariable(3) = <BitString-4_3=false>
 * bt10.getBooleanVariable(4) = <BitString-4_4=true>
 * bt10.getBooleanVariable(5) = <BitString-4_5=true>
 * bt10.getBooleanVariable(6) = <BitString-4_6=false>
 * bt10.getBooleanVariable(7) = <BitString-4_7=true>
 * bt10.getBooleanVariable(8) = <BitString-4_8=false>
 * bt10.getBooleanVariable(9) = <BitString-4_9=false>
 * bt10.getBooleanVariable(10) = <BitString-4_10=true>
 */
import bits.IBitString;
import bits.IBooleanVariable;
import bits.strings.BitString;

public class BitStringDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString bt1 = new BitString();
		System.out.println("bt1.getName() = " + bt1.getName());
		System.out.println("bt1.getDataList() = " + bt1.asList());

		IBitString bt2 = new BitString("xyz", new boolean[0]);
		System.out.println("bt2.getName() = " + bt2.getName());
		System.out.println("bt2.getDataList() = " + bt2.asList());

		IBitString bt3 = new BitString(new boolean[3]);
		System.out.println("bt3.getName() = " + bt3.getName());
		System.out.println("bt3.getDataList() = " + bt3.asList());
		for (int i = 0; i < bt3.size(); i++)
			System.out.println("bt3.data$" + i + "] = " + bt3.getBooleanVariable(i));

		IBitString bt4 = new BitString("xyz", new char[0]);
		System.out.println("bt4.getName() = " + bt4.getName());
		System.out.println("bt4.getDataList() = " + bt4.asList());
		for (int i = 0; i < bt4.size(); i++)
			System.out.println("bt4.getBooleanVariable(" + i + ") = " + bt4.getBooleanVariable(i));

		IBitString bt5 = new BitString(new char[]
		{ '0' });
		System.out.println("bt5.getName() = " + bt5.getName());
		System.out.println("bt5.getDataList() = " + bt5.asList());
		for (int i = 0; i < bt5.size(); i++)
			System.out.println("bt5.getBooleanVariable(" + i + ") = " + bt5.getBooleanVariable(i));

		IBitString bt6 = new BitString(new BitString("xyz", new boolean[4]));
		System.out.println("bt6.getName() = " + bt6.getName());
		System.out.println("bt6.getDataList() = " + bt6.asList());
		for (int i = 0; i < bt6.size(); i++)
			System.out.println("bt6.getBooleanVariable(" + i + ") = " + bt6.getBooleanVariable(i));

		IBitString bt7 = new BitString("xyz", new IBooleanVariable[0]);
		System.out.println("bt7.getName() = " + bt7.getName());
		System.out.println("bt7.getDataList() = " + bt7.asList());
		for (int i = 0; i < bt7.size(); i++)
			System.out.println("bt7.getBooleanVariable(" + i + ") = " + bt7.getBooleanVariable(i));

		IBitString bt8 = new BitString(new IBooleanVariable[7]);
		System.out.println("bt8.getName() = " + bt8.getName());
		System.out.println("bt8.getDataList() = " + bt8.asList());
		for (int i = 0; i < bt8.size(); i++)
			System.out.println("bt8.getBooleanVariable(" + i + ") = " + bt8.getBooleanVariable(i));

		IBitString bt9 = new BitString("xyz", 6);
		System.out.println("bt9.getName() = " + bt9.getName());
		System.out.println("bt9.getDataList() = " + bt9.asList());
		for (int i = 0; i < bt9.size(); i++)
			System.out.println("bt9.getBooleanVariable(" + i + ") = " + bt9.getBooleanVariable(i));

		IBitString bt10 = new BitString("01101101001");
		System.out.println("bt10.getName() = " + bt10.getName());
		System.out.println("bt10.getDataList() = " + bt10.asList());
		for (int i = 0; i < bt10.size(); i++)
			System.out.println("bt10.getBooleanVariable(" + i + ") = " + bt10.getBooleanVariable(i));
	}
}