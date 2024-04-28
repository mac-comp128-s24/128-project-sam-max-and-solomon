import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class PexelsAPI {

    private static final String API_KEY = "J3iQclveE81bCMbev3fiROZyjdqcoifNoa7zAHlXuSfReUYSV01sBx08";

    public static void main(String[] args) {
        String theme = "YOUR_THEME"; // Replace with the player's theme input 
        try {
            List<String> imageURLs = getImageURLs(theme);
            System.out.println("Image URLs: " + imageURLs);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getImageURLs(String theme){
        String url = "https://api.pexels.com/v1/search?query=" + theme + "&per_page=10";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", API_KEY)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            return parseImageUrlsFromJson(responseBody);
        } else {
            System.out.println("HTTP Request Failed with Status Code: " + response.statusCode());
            // Handle other status codes if needed
            return new ArrayList<>(); // Return empty list on failure
        }
    }

    private static List<String> parseImageUrlsFromJson(String jsonResponse) {
        List<String> imageUrls = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray photos = jsonObject.getJSONArray("photos");

        for (int i = 0; i < photos.length(); i++) {
            JSONObject photoObject = photos.getJSONObject(i);
            JSONObject src = photoObject.getJSONObject("src");
            String imageUrl = src.getString("medium");
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }
    
}
