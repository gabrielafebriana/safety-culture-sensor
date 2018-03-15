Running and testing instruction from command line:

	1. In command line, go inside the project directory (sensor), build using the gradle wrapper (you might need to use sudo):
		./gradlew clean build

	2. The unit test provided should also be run during the building process, you can see the test report in:
		/build/reports/tests/test/index.html

	3. Run the app from command line:
		java -jar ./build/libs/safety-culture-test-0.1.0.jar

	4. Send the JSON input as the body of a POST request (I used Postman for testing) to:
		localhost:8080/temperature/getAverages

Running and testing instruction from IntelliJ:

	1. Open the project as a gradle project.

	2. (Optional) Run the unit test cases in the test/src directory.

	3. Run Application.java.

	4. Send the JSON input as the body of a POST request (I used Postman for testing) to:
		localhost:8080/temperature/getAverages

I have also provided a build JAR that can just be run using:
	java -jar ./build/libs/safety-culture-test-0.1.0.jar