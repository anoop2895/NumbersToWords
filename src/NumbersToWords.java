import java.text.DecimalFormat;
import java.util.Scanner;

public class NumbersToWords 
{
	static String[] tensArray = {""," ten"," twenty"," thirty"," forty"," fifty"," sixty",
			                      " seventy"," eighty"," ninety"};

	static String[] onesArray = {""," one"," two"," three"," four"," five"," six"," seven",
		                      " eight"," nine"," ten"," eleven"," twelve"," thirteen",
                              " fourteen"," fifteen"," sixteen"," seventeen"," eighteen",
                              " nineteen"};
  
	public static String convertLessThanOneThousand(int inputNumber) 
	{
		String number;

		if (inputNumber % 100 < 20)
		{
			number = onesArray[inputNumber % 100];
			inputNumber /= 100;
		}
		else 
		{
			number = onesArray[inputNumber % 10];
			inputNumber /= 10;

			number = tensArray[inputNumber % 10] + number;
			inputNumber /= 10;
		}
		if (inputNumber == 0) 
			return number;
		return onesArray[inputNumber] + " hundred" + number;
	}


	public static String convertToWord(long number) 
	{
		if (number == 0) 
		{ 
			return "zero"; 
		}
		String snumber = Long.toString(number);
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);
		int billions = Integer.parseInt(snumber.substring(0,3));
		int millions  = Integer.parseInt(snumber.substring(3,6));
		int hundredThousands = Integer.parseInt(snumber.substring(6,9));
		int thousands = Integer.parseInt(snumber.substring(9,12));
		String tradBillions;
		switch (billions) 
		{
			case 0:
					tradBillions = "";
					break;
			case 1 :
					tradBillions = convertLessThanOneThousand(billions)
					+ " billion ";
					break;
			default :
					tradBillions = convertLessThanOneThousand(billions)+ " billion ";
		}
		String result =  tradBillions;
		String tradMillions;
		switch (millions) 
		{
			case 0:
					tradMillions = "";
					break;
			case 1 :
					tradMillions = convertLessThanOneThousand(millions)+ " million ";
					break;
			default :
					tradMillions = convertLessThanOneThousand(millions)+ " million ";
		}
		result =  result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) 
		{
			case 0:
					tradHundredThousands = "";
					break;
			case 1 :
					tradHundredThousands = "one thousand ";
					break;
			default :
					tradHundredThousands = convertLessThanOneThousand(hundredThousands)
					+ " thousand ";
		}
		result =  result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result =  result + tradThousand;

		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}
	

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System. in); 
		while(true)
		{
			int inputNumber = scanner.nextInt();
			if(inputNumber==0)
			{
				System.out.println("Exiting...");
				break;
			}
			if(inputNumber<0)
			{
				System.out.println("Invalid number");
			}
			else
			{
				String numberWord = convertToWord(inputNumber);
				System.out.println(numberWord);
			}
		}
	}
}