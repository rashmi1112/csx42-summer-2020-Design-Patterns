package wordPlay.driver;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.HelperClass;
import wordPlay.util.Results;

import java.io.IOException;

/**
 * @author Rashmi Badadale
 *
 */
public class Driver {
	public static void main(String[] args) throws IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		Results resultsObject1 = new Results(args[1]);
		Results resultObject2 = new Results(args[2]);
		WordRotator wordRotator = new WordRotator(resultsObject1);
		MetricsCalculator metricsCalculator = new MetricsCalculator(args[0],resultObject2);
		HelperClass helperClassObject = new HelperClass(args[0], wordRotator,metricsCalculator );
		helperClassObject.retrieveInput();
		resultsObject1.printResult();
		metricsCalculator.storeMetricResults();
		resultObject2.printResult();

	}
}
