package com.handy.graphql.demo.repository;

import com.handy.graphql.demo.dao.Character;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterRepositoryImpl implements CharacterRepository {
  private List<Character> CHARACTERS = new ArrayList<>();

  public CharacterRepositoryImpl() {
    List<String> friends1 = new ArrayList<>();
    friends1.add("First friend of char 1");
    friends1.add("Second friend of char 1");
    friends1.add("Third friend of char 1");
    this.CHARACTERS.add(new Character().setName("Character_1").setAge(21).setFriend(friends1));
    this.CHARACTERS.add(new Character().setName("Character_2").setAge(22).setFriend(Collections.singletonList("Friend of char 2")));
    this.CHARACTERS.add(new Character().setName("Character_3").setAge(23).setFriend(Collections.emptyList()));
  }

  @Override public Character get(String name) {
    return CHARACTERS.stream()
        .filter(c -> name.equalsIgnoreCase(c.getName()))
        .findFirst()
        .orElse(null);
  }

  @Override public void add(Character character) {
    this.CHARACTERS.add(character);
  }
}
