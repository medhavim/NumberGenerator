import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;

public class NumberGenerator {

	////////////////////////////////////////////////////////////////////////////////////////
	// Input: HashMap<Ineger, Integer>
	// Output: HashMap<Ineger, Integer>
	// Description: This function gets the distribution of the of the how many times a key 
	//	appears in the dataset. The Key is the number to be preset in the dataset 
	//	and the Value is the number of times the key should appear in the dataset
	///////////////////////////////////////////////////////////////////////////////////////
	public static HashMap<Integer, Integer> getDistribution (HashMap<Integer, Integer> map)
	{
		map.put(1,83000);
		map.put(2,83000);
		map.put(3,83000);
		map.put(4,83000);
		map.put(5,83000);
		map.put(6,83000);
		map.put(7,83000);
		map.put(8,83000);
		map.put(9,83000);
		map.put(10,83000);
		map.put(11,83000);
		map.put(12,83000);
		map.put(13,1000);
		map.put(14,500);
		map.put(15,250);
		map.put(16,100);
		map.put(17,50);
		map.put(18,25);
		map.put(19,10);
		map.put(20,5);

		return map;
	}


	////////////////////////////////////////////////////////////////////////////////////////
	// Input: HashMap<Ineger, Integer>
	// Output: String
	// Description: This function generates the dataset and stores it as a string
	// 	The dataset is generated based on the key and value pair distribution
	///////////////////////////////////////////////////////////////////////////////////////
	public static String generateDataSet(HashMap<Integer, Integer> map)
	{
		long datasetSize = 0;
		int maxKeyValue = 20;
		
		// To store the dataset generate before writing it to a file
		StringBuilder sb = new StringBuilder();

		// value to be printed on the console
		// NOTE: I could have used the variable maxKeyValue, but this way we can 
		// print different values on the console and not just the max key value.
		int printValue = 20;
		
		// keeps track of line number.
		// NOTE: Using long since each value in the dataset is printed in a new line and can exceed the int range
		long lineNumber = 0;

		// To Calculate the dataset size.
		// NOTE: This for loop could have been avoided and I could have set the value directly to 997940, 
		// but I decided to calculate the dataset as the distribution may change which will affect the final size of the dataset
		for (int j = 1; j <= maxKeyValue; j++)
		{
			datasetSize = datasetSize + map.get(j);
		}

		// Dataset generation begins
		while (datasetSize != 0)
		{
			// NOTE: Decided not to use Random() or shuffle() due to performance considerations
			for (int i = 1; i <= maxKeyValue; i++)
			{
				int frequency = map.get(i);
				if (frequency != 0)
				{
					sb.append(i + "\n");
					lineNumber++;
					
					if (i == printValue)
						System.out.println(i + " at line number: " + lineNumber);
					
					map.put(i, frequency - 1);
					datasetSize = datasetSize - 1;
				}
			}
		}
		return sb.toString();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	// Input: String, String
	// Output: void
	// Description: This function writes the dataset generated to a file
	//	the dataset generated is in a String and hence the file need not be open 
	//	throughout the process of the dataset generation
	///////////////////////////////////////////////////////////////////////////////////////
	public static void writeDataToFile(String fileName, String data)
	{
		FileWriter fileWriter = null;
		try
		{
			fileWriter = new FileWriter(fileName);
			fileWriter.write(data);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			try
			{
				if (fileWriter != null)
				{
					fileWriter.flush();
					fileWriter.close();
				}
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	////////////////////////////////////////////////////////////
	// Main Function
	///////////////////////////////////////////////////////////
	public static void main(String[] args) {
		HashMap<Integer, Integer>  map = new HashMap<>();
		String fileName = "/Users/Medhavi/test.output";

		// Retrieve the dataset distribution
		map = getDistribution(map);

		// Generate the dataset based on the distribution
		String data = generateDataSet(map);

		// Write the dataset to a file
		writeDataToFile(fileName, data);
	}
}