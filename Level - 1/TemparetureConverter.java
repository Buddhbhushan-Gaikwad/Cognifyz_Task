import java.util.Scanner;


public class TemparetureConverter{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Ask user for temperature input
		System.out.print("Enter the temperature value: ");
		double temperature = scanner.nextDouble();

		// Ask user for unit of temperature
		System.out.print("Enter the unit (C for Celsius, F for Fahrenheit): ");
		char unit = scanner.next().toUpperCase().charAt(0);

		double convertedTemp;
		if (unit == 'C') {
			// Convert Celsius to Fahrenheit
			convertedTemp = (temperature * 9 / 5) + 32;
			System.out.printf("Temperature in Fahrenheit: %.2f°F\n", convertedTemp);
		} else if (unit == 'F') {
			// Convert Fahrenheit to Celsius
			convertedTemp = (temperature - 32) * 5 / 9;
			System.out.printf("Temperature in Celsius: %.2f°C\n", convertedTemp);
		} else {
			System.out.println("Invalid unit! Please enter C for Celsius or F for Fahrenheit.");
		}

		scanner.close();
	}
}