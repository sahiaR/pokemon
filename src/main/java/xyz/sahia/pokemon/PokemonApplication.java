package xyz.sahia.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

//@SpringBootApplication
public class PokemonApplication {

    public static void main(String[] args) throws JsonProcessingException {

       // SpringApplication.run(PokemonApplication.class, args);
        var pokemon = new Pokemon(
                "bulbizarre",
                "pokemon.com");
        // to from json
        var json = """
            {
              "name" : "bulbizarre",
              "url" : "pokemon.com"
            }""";
        final ObjectMapper mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
        var serialized = mapper.writeValueAsString(pokemon);


        System.out.println(serialized);
        System.out.println(json);
    }

}
record Pokemon(@JsonProperty("name") String nom ,
               @JsonProperty("url") String url){}
record PokemonsDetails(@JsonProperty int id ,
                       @JsonProperty String name, int height,
                       @JsonProperty int weight,
                       @JsonProperty List<Ability> abilities){}
record Ability(@JsonProperty String name,
               @JsonProperty String effect){}