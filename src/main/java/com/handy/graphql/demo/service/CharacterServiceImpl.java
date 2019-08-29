package com.handy.graphql.demo.service;

import com.handy.graphql.demo.dao.Character;
import com.handy.graphql.demo.repository.CharacterRepository;
import com.handy.graphql.demo.repository.CharacterRepositoryImpl;

public class CharacterServiceImpl implements CharacterService {

  private final CharacterRepository _characterRepository;

  public CharacterServiceImpl() {
    _characterRepository = new CharacterRepositoryImpl();
  }

  @Override
  public Character getCharacter(String name) {
    return _characterRepository.get(name);
  }

  @Override
  public boolean addCharacter(String name) {
    _characterRepository.add(new Character().setName(name).setAge(24));
    return true;
  }
}
