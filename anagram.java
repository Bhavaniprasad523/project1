import java.util.*;
import java.lang.*;
public class anagram
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter strings:");
		String s=sc.nextLine();
		String l=sc.nextLine();
		//char[] c=s.toCharArray();
		//char[] d=l.toCharArray();
		int found,not_found=0;
		int s1=s.length();
		int s2=l.length();
		if(s1==s2)
		{
			for(int i=0;i<s1;i++)
			{
				found=0;
				for(int j=0;j<s2;j++)
				{
					if(s.charAt(i)==l.charAt(j))
					{
						found=1;
						break;
					}
				}
				if(found==0)
				{
					not_found=1;
					break;
				}
			}
			if(not_found==1)
			{
				System.out.println("Strings are not anagrams");
			}
			else
			{
				System.out.println("strings are anagrams");
			}
		}
		else
		{
			System.out.println("Strings are not matched");
		}
	}
}

		
		
					
	

