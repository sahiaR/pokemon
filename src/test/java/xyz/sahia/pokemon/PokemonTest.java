package xyz.sahia.pokemon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;


class PokemonTest {

    @Test
    public void testPokemon()  throws Exception{

        //given
        var pokemon = new Pokemon(
                "bulbizarre",
                "pokemon.com");
        // to from json
        var json = """
        {
          "name" : "bulbizarre",
          "url" : "pokemon.com"
        }""";

        //when
        final ObjectMapper mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
        var serialized = mapper.writeValueAsString(pokemon);
        //then
        JSONAssert.assertEquals(json, serialized,true);

    }

}