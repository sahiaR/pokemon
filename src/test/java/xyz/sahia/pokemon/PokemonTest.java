package xyz.sahia.pokemon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.assertj.core.api.Assertions.assertThat;


class PokemonTest {

    final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    @Test
    public void serializeRecord()  throws Exception{
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
        var serialized = mapper.writeValueAsString(pokemon);
        //then
        JSONAssert.assertEquals(json, serialized,true);
        // assertThat(serialized).isEqualTo(json);
    }
    @Test
    public void deserializeRecord() throws JsonProcessingException {
        // given
        var pokemon = new Pokemon(
                "bulbizarre",
                "pokemon.com");
        var json = """
        {
          "name" : "bulbizarre",
          "url" : "pokemon.com"
        }""";
        //when
        var deserialized = mapper.readValue(json, Pokemon.class);
        //then
        assertThat(deserialized).isEqualTo(pokemon);
    }

}