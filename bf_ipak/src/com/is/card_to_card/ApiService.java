package com.is.card_to_card;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.zkoss.zhtml.Messagebox;

public class ApiService {

	public List<CardFromApi> fetchUsers() {
	    List<CardFromApi> userList = new ArrayList();

	    try {
	        String url = "https://jsonplaceholder.typicode.com/todos/21";
	        URL apiUrl = new URL(url);
	        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
	        connection.setRequestMethod("GET");

	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.setRequestProperty("Authorization", "Bearer your_access_token");

	        int responseCode = connection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            StringBuilder response = new StringBuilder();
	            String inputLine;

	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();

	            JSONObject jsonResponse = new JSONObject(response.toString());

	            int userId = jsonResponse.getInt("userId");
	            int id = jsonResponse.getInt("id");
	            String title = jsonResponse.getString("title");
	            boolean completed = jsonResponse.getBoolean("completed");

	            CardFromApi user = new CardFromApi(userId, id, title, completed);
	            userList.add(user);

	        } else {
	            throw new RuntimeException("GET request failed. Response Code: " + responseCode);
	        }

	        connection.disconnect();

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to fetch users from API: " + e.getMessage());
	    }

	    return userList;
	}

}