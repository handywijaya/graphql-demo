package com.handy.graphql.demo.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.handy.graphql.demo.repository.CharacterRepository;
import com.handy.graphql.demo.repository.CharacterRepositoryImpl;
import com.handy.graphql.demo.resolver.CharacterQueryResolver;
import graphql.schema.idl.SchemaParser;
import com.handy.graphql.demo.dao.Character;
import com.handy.graphql.demo.fetcher.ComplexCharacterDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CharacterServiceGraphQLImplWithTools implements CharacterServiceGraphQL {
  private final GraphQL _graphQL;
  private final CharacterRepository _characterRepository;

  public CharacterServiceGraphQLImplWithTools() {
    _characterRepository = new CharacterRepositoryImpl();

    //File schemaFile = loadFromFile("schema/schema_character.graphqls");
    //TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
    //
    //ComplexCharacterDataFetcher characterDataFetcher = new ComplexCharacterDataFetcher();
    //RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
    //    .type("Query", builder -> builder.dataFetcher("getCharacter", characterDataFetcher.getCharacterFetcher()))
    //    .type("Query", builder -> builder.dataFetcher("addCharacter", characterDataFetcher.addCharacterFetcher()))
    //    .build();
    //
    //GraphQLSchema
    //    schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

    CharacterQueryResolver characterQueryResolver = new CharacterQueryResolver(this);
    List<GraphQLQueryResolver> queryResolvers = new ArrayList<>();
    queryResolvers.add(characterQueryResolver);

    GraphQLSchema schema = com.coxautodev.graphql.tools.SchemaParser.newParser()
        .file("schema_character.graphqls") // resources file
        .resolvers(queryResolvers)
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

  //private static File loadFromFile(String filePath) {
  //  return new File(filePath);
  //}
}
