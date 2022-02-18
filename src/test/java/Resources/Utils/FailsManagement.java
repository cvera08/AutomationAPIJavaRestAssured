package Resources.Utils;

public class FailsManagement {

    /**
     * Call it in your java tests in the catch of errors
     *
     * @param failure : you can print the try/catch error. e.g, the AssertionError related
     * @param testName : the one that is being implemented by BaseApiTest
     */
    public static void testCaseFailLogs(String failure, String testName){
        System.out.println(failure);
        org.testng.Assert.fail("Failed TC: " + testName + ". Please see previous logs to see the on detail reason"); //Still failing test (on purpose)
    }
}
