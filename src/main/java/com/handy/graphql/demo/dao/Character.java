package com.handy.graphql.demo.dao;

import java.util.ArrayList;
import java.util.List;

public class Character {
  private String name;
  private int age;
  private List<String> friend = new ArrayList<>();

  public String getName() {
    return name;
  }

  public Character setName(String name) {
    this.name = name;
    return this;
  }

  public int getAge() {
    return age;
  }

  public Character setAge(int age) {
    this.age = age;
    return this;
  }

  public List<String> getFriend() {
    return friend;
  }

  public Character setFriend(List<String> friend) {
    this.friend = friend;
    return this;
  }

  @Override public String toString() {
    return "Character{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", friend=" + friend +
        '}';
  }
}
