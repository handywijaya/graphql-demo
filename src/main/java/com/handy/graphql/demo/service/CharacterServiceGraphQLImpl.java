package com.handy.graphql.demo.service;

import com.handy.graphql.demo.dao.Character;
import com.handy.graphql.demo.fetcher.ComplexCharacterDataFetcher;
import com.handy.graphql.demo.repository.CharacterRepository;
import com.handy.graphql.demo.repository.CharacterRepositoryImpl;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;

public class CharacterServiceGraphQLImpl implements CharacterServiceGraphQL {
  private CharacterRepository _characterRepository;
  private final GraphQL _graphQL;

  public CharacterServiceGraphQLImpl() {
    _characterRepository = new CharacterRepositoryImpl();

    File schemaFile = loadFromFile("schema/schema_character.graphqls");
    TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);

    ComplexCharacterDataFetcher characterDataFetcher = new ComplexCharacterDataFetcher();
    RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
        .type("Query", builder -> builder.dataFetcher("getCharacter", characterDataFetcher.getCharacterFetcher()))
        .type("Query", builder -> builder.dataFetcher("addCharacter", characterDataFetcher.addCharacterFetcher()))
        .build();

    GraphQLSchema
        schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
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
    ExecutionResult result = _graphQL.execute(query);
    return result.getData();
  }

  private static File loadFromFile(String filePath) {
    return new File(filePath);
  }
}
