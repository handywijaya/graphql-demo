package com.handy.graphql.demo.app.dw.data;

public class GraphQLSpec {
  private String query;

  public String getQuery() {
    return query;
  }

  public GraphQLSpec setQuery(String query) {
    this.query = query;
    return this;
  }

  @Override public String toString() {
    return "GraphQLSpec{" +
        "query='" + query + '\'' +
        '}';
  }
}
