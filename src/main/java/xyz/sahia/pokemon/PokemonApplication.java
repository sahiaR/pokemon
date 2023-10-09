package xyz.sahia.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

//@SpringBootApplication
public class PokemonApplication {
    public static void main(String[] args) throws Exception {

        var pokemonApiService = new PokemonApiService();
        var url = "https://pokeapi.co/api/v2/pokemon/";
        var apiReponse = pokemonApiService.getApiResponse(url);
        var mapper = new ObjectMapper();

        var toutLesPokemon = mapper.readValue(apiReponse, PokemonApiReponse.class);

        List<Pokemon> pokemonList = toutLesPokemon.results();

        System.out.println(pokemonList.get(0).nom());
        System.out.println(pokemonList.get(0).url());

        var pokemonNumero1= pokemonApiService.getApiResponse(pokemonList.get(0).url());
        var pokemonNumero1Record = mapper.readValue(pokemonNumero1, PokemonsDetails.class);

        System.out.println(pokemonNumero1Record.abilities().get(0).ability().id());
        System.out.println(pokemonNumero1Record.abilities().get(0).ability().name());

        var capacite = pokemonApiService.
                getApiResponse(pokemonNumero1Record.abilities().get(0).ability().url());

        var capaciteRecord = mapper.readValue(capacite, AbilityDetails.class);
        System.out.println(capaciteRecord.name());
        System.out.println(capaciteRecord.effets().get(0).effect());
        }

    }

@JsonIgnoreProperties(ignoreUnknown = true)
record PokemonApiReponse(List<Pokemon>results){
}
record Pokemon(@JsonProperty("name") String nom ,
               @JsonProperty("url") String url){}
@JsonIgnoreProperties(ignoreUnknown = true)
record PokemonsDetails(@JsonProperty("id") int id ,
                       @JsonProperty("name") String name,
                       @JsonProperty("height") int height,
                       @JsonProperty("weight") int weight,
                       @JsonProperty("abilities") List< Ability> abilities){}
@JsonIgnoreProperties(ignoreUnknown = true)
record Ability(@JsonProperty ("ability")AbilityDetails ability ){}
@JsonIgnoreProperties(ignoreUnknown = true)
record AbilityDetails( @JsonProperty("id") int id,
                       @JsonProperty("name") String name,
                       @JsonProperty("url") String url,
                       @JsonProperty("effect_entries") List<Effet>effets){}
@JsonIgnoreProperties(ignoreUnknown = true)
record Effet(@JsonProperty("effect") String effect){}