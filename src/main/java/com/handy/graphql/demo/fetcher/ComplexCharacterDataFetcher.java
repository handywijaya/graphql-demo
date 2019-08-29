package com.handy.graphql.demo.fetcher;

import com.handy.graphql.demo.service.CharacterServiceImpl;
import graphql.schema.DataFetcher;

public class ComplexCharacterDataFetcher {
  private final CharacterServiceImpl _characterService;

  public ComplexCharacterDataFetcher() {
    _characterService = new CharacterServiceImpl();
  }

  public DataFetcher getCharacterFetcher() {
    return dataFetchingEnvironment -> {
      String name = dataFetchingEnvironment.getArgument("name");
      return _characterService.getCharacter(name);
    };
  }

  public DataFetcher addCharacterFetcher() {
    return dataFetchingEnvironment -> {
      String name = dataFetchingEnvironment.getArgument("name");
      return _characterService.addCharacter(name);
    };
  }
}
