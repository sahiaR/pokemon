package xyz.sahia.pokemon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpTest {

    @Test
    void http () throws Exception {
        // <1>
        try (var http = HttpClient
                .newHttpClient()){
            var request = HttpRequest.newBuilder(URI.create("https://pokeapi.co/api/v2/pokemon/"))
                    .GET()
                    .build() ;
            var response = http.send( request, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals( response.statusCode() , 200);
            System.out.println(response.body());
        }
    }
}
