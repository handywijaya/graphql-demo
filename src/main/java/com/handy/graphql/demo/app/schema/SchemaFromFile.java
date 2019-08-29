package com.handy.graphql.demo.app.schema;

import com.handy.graphql.demo.fetcher.CharacterDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;

public class SchemaFromFile {

  public static void main(String[] args) {
    File schemaFile = loadFromFile("schema/schema_character.graphqls");
    TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);

    CharacterDataFetcher characterDataFetcher = new CharacterDataFetcher();
    //ComplexCharacterDataFetcher characterDataFetcher = new ComplexCharacterDataFetcher();
    RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
        .type("Query", builder -> builder.dataFetcher("getCharacter", characterDataFetcher.getCharacterFetcher()))
        .type("Query", builder -> builder.dataFetcher("addCharacter", characterDataFetcher.addCharacterFetcher()))
        .build();

    GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    GraphQL graphQL = GraphQL.newGraphQL(schema).build();

    ExecutionResult result = graphQL.execute("{ getCharacter(name: \"Character_1\") { name }}");
    System.out.println(result.getData().toString());
  }

  private static File loadFromFile(String filePath) {
    return new File(filePath);
  }
}
