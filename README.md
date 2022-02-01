# AutomationApiProject
Base example of an automation project to test APIs (BE)

Java + RestAssured + TestNG + mvn

**Installation:**

- Make sure you have configured and installed java on your computer. JDK 1.8 should be okay. Resource: https://java.com/en/download/help/download_options.html
- You will need maven as well. mvn guide: https://maven.apache.org/install.html

_Desirable_: 
- An IDE, like IntelliJ. If you are using Eclipse should be fine as well. https://www.jetbrains.com/es-es/idea/download/ (Make sure to download the Community version, no the Ultimate)

**How to Run the tests:**
- Make sure to import this project as a mvn project instead of just opening the main folder
- If you are using IntelliJ you may go to the Maven tab and update the project (Reload All Maven Projects - option), if you are using other IDE or no IDE at all go to the terminal/console, your project location and run: 
- - > mvn clean install
- Go to your project folder > AutomationApiProject > src/test/testngRunners/ > SingleTest.xml  file > right click & run 
- A new window will be opened and after a couple of ms you will get something like this:

```
===============================================
 AutomationApiSuite
 Total tests run: 1, Failures: 0, Skips: 0
===============================================

Process finished with exit code 0
```

If you get a failure could be a simple endpoint error, 

if you get an error make sure all the mvn dependencies/packages are fully installed before running (it could take some time)

**To dig in a bit more - Main Library documentation:** 
- https://rest-assured.io/
- - Github / Get Started: https://github.com/rest-assured/rest-assured/wiki/GettingStarted