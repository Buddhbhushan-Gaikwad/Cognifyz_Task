import java.util.Random;
import java.util.Scanner;

public class RandomPasswardGenerator {

	public static void main( String [] args )
	{
		Scanner snr = new Scanner(System.in);
		Random r = new Random();

		//Step -- > : Get user I/P
		System.out.println("Enter the passward lenght : ");
		int length = snr.nextInt();
		snr.nextLine();
		System.out.println("It contains lowercase latter ? (y/n)");
		boolean containLower = snr.nextLine().equalsIgnoreCase("y");

		System.out.println("It contain Uppercase latter ? (y/n)");
		boolean containUpper = snr.nextLine().equalsIgnoreCase("y");

		System.out.println("It contains Numbers ? (y/n)");
		boolean containNumber = snr.nextLine().equalsIgnoreCase("y");

		System.out.println("It contains Special Symboles ? (y/n)");
		boolean containSymboles = snr.nextLine().equalsIgnoreCase("y");

		//Step 2  ----> Define charactor
		String lowerCase = "abcdefghijklmnopwrstuvwxyz";
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String number = "0123456789";
		String Symboles = "!@#$%^&*<>?()_+=-/";

		//Step 3 ----> Build Charecter pool
		StringBuilder charPool = new StringBuilder();

		if(containLower) charPool.append(lowerCase);
		if(containUpper) charPool.append(upperCase);
		if(containNumber) charPool.append(number);
		if(containSymboles) charPool.append(Symboles);

		if(charPool.length() == 0 ) {
			System.out.println("Error : No charactor type selected...");
			return;
		}
		//step 4 ----> Generate the passward
		StringBuilder passward = new StringBuilder();
		for(int i = 0; i <= length ; i++ )
		{
			int index = r.nextInt(charPool.length());
			passward.append(charPool.charAt(index));

		}
		System.out.println("Generated Passward : "+ passward.toString());

	}

}