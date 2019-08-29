package com.handy.graphql.demo.app.schema;

import com.handy.graphql.demo.fetcher.CharacterDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class CustomSchema {
  public static void main(String[] args) {
    String schema = "type Query { getCharacter(name: String): Character, addCharacter(name: String): String } type Character { name: String, age: Int }";

    SchemaParser schemaParser = new SchemaParser();
    TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

    CharacterDataFetcher characterDataFetcher = new CharacterDataFetcher();
    RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
        .type("Query", builder -> builder.dataFetcher("getCharacter", characterDataFetcher.getCharacterFetcher()))
        .type("Query", builder -> builder.dataFetcher("addCharacter", characterDataFetcher.addCharacterFetcher()))
        .build();

    SchemaGenerator schemaGenerator = new SchemaGenerator();
    GraphQLSchema
        graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

    GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();

    ExecutionResult executionResult = build.execute("{ getCharacter(name: \"Character_1\") { name }}");
    System.out.println(executionResult.getData().toString());

    ExecutionResult executionResult2 = build.execute("{ addCharacter(name: \"New Character\") }");
    System.out.println(executionResult2.getData().toString());

    ExecutionResult executionResult3 = build.execute("{ getCharacter(name: \"New character\") { age }}");
    System.out.println(executionResult3.getData().toString());
  }
}
