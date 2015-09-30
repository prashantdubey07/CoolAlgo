
import java.math.BigInteger;
import java.util.Scanner;
/**
 * 
 * @author Prashant Kumar Dubey
 * @since Sept 2015
 * @version 1.0
 *
 */
/*
*
find total count of possible number for given digit (as input), whether condition is next and previous digit of current digit
should be +-1.
number will not have leading zeroes.

example
if input is "1" then output should be "10" 
as: 0,1,2,3,4,5,6,7,8,9
if input is "2" then output should be "17" 
as: 10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98


*/
public class NextDigit {
	
	//it will count total number of possible combinations
	static long totalCount;
	
	// big integer constant
	public static final BigInteger TEN = new BigInteger("10");
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int digits=1;
		for(int i=0;i<t;i++)
		{
			int N = sc.nextInt();
			
			//if user inputs 1 digit
			if(N==1)
				System.out.println("10");
			//if user inputs more than 1 digit
			else
			{
				totalCount = countMaxNumber(N,0,null,digits);
				System.out.println(totalCount);
			}
			
		}
	}
	public static long countMaxNumber(int totaldigit,int lastDigit,BigInteger Number,int currentDigit)
	{
		//if currently iteration is at last digit of a number
		if(currentDigit == totaldigit)
		{
			//check whether number.length and current digit are same or not
			//if yes then check whether next digit is +1/-1 of last digit  is possible or not
			//if possible then generate new number and increase count as possible combination
			if(Number.toString().length() < currentDigit)
			{
				int l =0;
				BigInteger Number1 = Number; 
				if(lastDigit+1 <10)
				{
					Number = Number.multiply(TEN);
					Number = Number.add(new BigInteger(lastDigit+1+""));
					l++;
				}
				if(lastDigit-1 >-1)
				{
					Number1 = Number1.multiply(TEN);
					Number1 = Number1.add(new BigInteger(lastDigit-1+""));
					l++;
				}
				return l;
			}
		}
		//check whether current digit is 1
		//if yes then iterate for first digit of a number
		// recursive call to same function by increasing current digit
		if(currentDigit == 1)
		{
			for(int i=1;i<=9;i++)
			{
				BigInteger b = new BigInteger(i+"");
				totalCount +=countMaxNumber(totaldigit,i,b,currentDigit+1);
			}
		}
		//check whether current digit is greater than 1
		//if yes then find next possible digit using +1/-1
		// recursive call to same function by increasing current digit
		//if possible then generate new number and increase count as possible combination
		else
		{
			long l =0;
			BigInteger Number1 = Number;
			if(lastDigit+1 <10)
				{
					Number = Number.multiply(TEN);
					Number = Number.add(new BigInteger(lastDigit+1+""));
					l += countMaxNumber(totaldigit,lastDigit+1,Number,currentDigit+1);
				}
			if(lastDigit-1 >-1)
				{
				Number1 = Number1.multiply(TEN);
				Number1 = Number1.add(new BigInteger(lastDigit-1+""));
					l += countMaxNumber(totaldigit,lastDigit-1,Number1,currentDigit+1);
				}
			return l;
		}
		return totalCount;
	}

}
