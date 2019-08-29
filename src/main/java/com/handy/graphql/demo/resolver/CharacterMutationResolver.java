package com.handy.graphql.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.handy.graphql.demo.dao.Character;
import com.handy.graphql.demo.service.CharacterService;
import java.util.ArrayList;
import java.util.List;

public class CharacterMutationResolver implements GraphQLMutationResolver {
  private CharacterService _characterService;

  public CharacterMutationResolver(CharacterService characterService) {
    this._characterService = characterService;
  }

  public List<Character> addTwoCharacters(String name1, String name2) {
    _characterService.addCharacter(name1);
    _characterService.addCharacter(name2);

    List<Character> result = new ArrayList<>();
    result.add(_characterService.getCharacter(name1));
    result.add(_characterService.getCharacter(name2));
    return result;
  }
}
