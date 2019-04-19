import java.lang.*;
import java.util.*;
public class join
{
  public static void main(String args[])
  {
	  Scanner sc=new Scanner(System.in);
	  int[] a={1,2,3};
	  int[] b={4,5,6};
	  int[] c=new int[a.length+b.length];
	  int count=0;
	  for(int i=0;i<a.length;i++)
	  {
	   c[i]=a[i];
	   count++;
	   }
	   for(int i=0;i<b.length;i++)
	   {
		   c[count++]=b[i];
	   }
	  /* for(int i=0;i<c.length;i++)
	   {
		   System.out.println(Arrays.toString(c));
		   //System.out.println(c[i]);
	   }*/
	   System.out.println(Arrays.toString(c));
	   
   }
}

	   
