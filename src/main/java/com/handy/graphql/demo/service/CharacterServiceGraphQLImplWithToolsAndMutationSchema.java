package com.handy.graphql.demo.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.handy.graphql.demo.dao.Character;
import com.handy.graphql.demo.repository.CharacterRepository;
import com.handy.graphql.demo.repository.CharacterRepositoryImpl;
import com.handy.graphql.demo.resolver.CharacterMutationResolver;
import com.handy.graphql.demo.resolver.CharacterQueryResolver;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import java.util.ArrayList;
import java.util.List;

public class CharacterServiceGraphQLImplWithToolsAndMutationSchema implements CharacterServiceGraphQL {
  private final CharacterRepository _characterRepository;
  private final GraphQL _graphQL;

  public CharacterServiceGraphQLImplWithToolsAndMutationSchema() {
    _characterRepository = new CharacterRepositoryImpl();

    CharacterQueryResolver characterQueryResolver = new CharacterQueryResolver(this);
    List<GraphQLQueryResolver> queryResolvers = new ArrayList<>();
    queryResolvers.add(characterQueryResolver);

    CharacterMutationResolver characterMutationResolver = new CharacterMutationResolver(this);
    List<GraphQLMutationResolver> mutationResolvers = new ArrayList<>();
    mutationResolvers.add(characterMutationResolver);

    GraphQLSchema schema = com.coxautodev.graphql.tools.SchemaParser.newParser()
        .file("schema_character_with_mutation.graphqls") // resources file
        .resolvers(queryResolvers)
        .resolvers(mutationResolvers)
        .build()
        .makeExecutableSchema();
    _graphQL = GraphQL.newGraphQL(schema).build();
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

  @Override
  public Object handleGraphQL(String query) {
    // from resolver, not data fetcher
    ExecutionResult result = _graphQL.execute(query);
    return result.getData();
  }
}
