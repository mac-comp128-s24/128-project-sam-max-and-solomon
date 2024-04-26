import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PexelsAPI {

    private static final String API_KEY = "J3iQclveE81bCMbev3fiROZyjdqcoifNoa7zAHlXuSfReUYSV01sBx08";

    public static void main(String[] args) {
        String theme = "YOUR_THEME"; // Replace with the user's theme input
        try {
            String url = "https://api.pexels.com/v1/search?query=" + theme + "&per_page=10"; // Adjust per_page as needed
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", API_KEY)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the request was successful (status code 200)
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                System.out.println("Response: " + responseBody);
                // Process the response body here
            } else {
                System.out.println("HTTP Request Failed with Status Code: " + response.statusCode());
                // Handle other status codes if needed
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
