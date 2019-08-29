package com.handy.graphql.demo.fetcher;

import com.handy.graphql.demo.dao.Character;
import graphql.schema.DataFetcher;
import java.util.ArrayList;
import java.util.List;

public class CharacterDataFetcher {
  private List<Character> CHARACTERS = new ArrayList<>();

  public CharacterDataFetcher() {
    this.CHARACTERS.add(new Character().setName("Character_1").setAge(21));
    this.CHARACTERS.add(new Character().setName("Character_2").setAge(22));
    this.CHARACTERS.add(new Character().setName("Character_3").setAge(23));
  }

  public DataFetcher getCharacterFetcher() {
    return dataFetchingEnvironment -> {
      String name = dataFetchingEnvironment.getArgument("name");
      return CHARACTERS.stream()
          .filter(c -> name.equalsIgnoreCase(c.getName()))
          .findFirst()
          .orElse(null);
    };
  }

  public DataFetcher addCharacterFetcher() {
    return dataFetchingEnvironment -> {
      String name = dataFetchingEnvironment.getArgument("name");
      CHARACTERS.add(new Character().setName(name).setAge(24));
      return "success";
    };
  }
}
