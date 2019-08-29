package com.handy.graphql.demo.service;

public interface CharacterServiceGraphQL extends CharacterService {
  // field name is query, but not limited to type name Query, i.e. Mutation
  Object handleGraphQL(String query);
}
