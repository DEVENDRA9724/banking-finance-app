import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeleniumTest {
    
    @Test
    public void testViewAccountAPI() {
        try {
            URL url = new URL("http://localhost:8081/viewPolicy/1001");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            
            Assertions.assertEquals(200, responseCode);
            System.out.println("✓ API test passed! Response code: " + responseCode);
        } catch (Exception e) {
            Assertions.fail("API test failed: " + e.getMessage());
        }
    }
    
    @Test
    public void testSayHelloAPI() {
        try {
            URL url = new URL("http://localhost:8081/sayHello");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            
            Assertions.assertEquals(200, responseCode);
            System.out.println("✓ SayHello API test passed!");
        } catch (Exception e) {
            Assertions.fail("API test failed: " + e.getMessage());
        }
    }
    
    @Test
    public void testCreateAccountAPI() {
        try {
            URL url = new URL("http://localhost:8081/createAccount");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            
            String jsonBody = "{\"accountNumber\":9001,\"accountName\":\"Selenium Test\",\"accountType\":\"Savings\",\"accountBalance\":50000}";
            connection.getOutputStream().write(jsonBody.getBytes());
            
            int responseCode = connection.getResponseCode();
            Assertions.assertEquals(200, responseCode);
            System.out.println("✓ Create Account API test passed!");
        } catch (Exception e) {
            Assertions.fail("API test failed: " + e.getMessage());
        }
    }
}