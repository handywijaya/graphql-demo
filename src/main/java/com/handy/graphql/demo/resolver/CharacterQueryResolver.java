package com.handy.graphql.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.handy.graphql.demo.dao.Character;
import com.handy.graphql.demo.service.CharacterService;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;

public class CharacterQueryResolver implements GraphQLQueryResolver {
  private CharacterService _characterService;

  public CharacterQueryResolver(CharacterService characterService) {
    this._characterService = characterService;
  }

  public Character getCharacter(String name) {
    return _characterService.getCharacter(name);
  }

  public Character addCharacter(String name) {
    _characterService.addCharacter(name);
    return _characterService.getCharacter(name);
  }

  public List<String> friend(Character character) {
    return _characterService.getCharacter(character.getName()).getFriend();
  }
}
