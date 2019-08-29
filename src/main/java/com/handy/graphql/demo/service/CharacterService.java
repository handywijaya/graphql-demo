package com.handy.graphql.demo.service;

import com.handy.graphql.demo.dao.Character;

public interface CharacterService {
  Character getCharacter(String name);
  boolean addCharacter(String name);
}
