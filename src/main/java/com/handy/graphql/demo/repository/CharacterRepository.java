package com.handy.graphql.demo.repository;

import com.handy.graphql.demo.dao.Character;

/**
 * Combination of accessor and DB
 */
public interface CharacterRepository {
  Character get(String name);
  void add(Character character);
}
