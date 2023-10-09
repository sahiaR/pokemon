package xyz.sahia.pokemon;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class PokemonApiService {
    private final HttpClient httpClient ;
    public PokemonApiService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getApiResponse(String apiUrl) throws Exception {
        var request = HttpRequest.newBuilder(URI.create(apiUrl))
                .GET()
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
