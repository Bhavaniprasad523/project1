import java.lang.*;
import java.util.*;
public class two
{
	public int bal=500;
	public void Deposit(int d)
	{
		//System.out.println("Enter amount to be deposited");
		bal=bal+d;
		System.out.println("bal is"+bal);
	}
	public void Withdraw(int n)
	{
		System.out.println("Enter amount to withdrawl");
		if(n<=bal)
		{
			bal=bal-n;
			System.out.println("bal is" + bal);
			System.out.println("take money");
		}
		else
		{
			System.out.println("insufficient funds");
		}
	}
	public void check()
	{
		System.out.println("balance is:"+bal);
	}
	public void Transfer(int amt)
	{
		bal=bal-amt;
		System.out.println("balance is: " + bal);
	}
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		two b=new two();
		int i,ch;
		String s="3000123456";
		System.out.println("Enter accno name:");
		String un=sc.next();
		System.out.println("Enter user name");
		String name=sc.next();
		String s2=name.substring(0,3)+un.substring(6,10);
		//System.out.println(s2);
		System.out.println("Enter password:");
		String p=sc.next();
		if(un.equals(s) && p.equals(s2))
		{
			System.out.println("1.Deposit 2.Withdraw 3.check 4.Transfer 5.exit");
			//ch=sc.nextInt();
			while(true)
			{
				ch=sc.nextInt();
				switch(ch)
				{
					
			case 1:System.out.println("Enter amount to be deposited");
			       int d=sc.nextInt();
			       b.Deposit(d);
			       break;
			case 2:System.out.println("Enter amount to withdrawl");
			       int n=sc.nextInt();
			       b.Withdraw(n);
			       break;
			case 3:b.check();
			       break;
			case 4:System.out.println("Enter amount to be transfered");
			       int amt=sc.nextInt();
			       b.Transfer(amt);
			       break;
			case 5:System.exit(0);					
	}
}
}
}
}
