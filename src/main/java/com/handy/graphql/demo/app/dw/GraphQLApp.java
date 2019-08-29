package com.handy.graphql.demo.app.dw;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class GraphQLApp extends Application<GraphQLAppConfiguration> {
  public static void main(String[] args) throws Exception {
    new GraphQLApp().run(args);
  }

  @Override
  public void run(GraphQLAppConfiguration configuration, Environment environment) throws Exception {
    CharacterResource characterResource = new CharacterResource();
    environment.jersey().register(characterResource);
  }
}
