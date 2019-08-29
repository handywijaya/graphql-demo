package com.handy.graphql.demo.app.dw;

import com.handy.graphql.demo.app.dw.data.GraphQLSpec;
import com.handy.graphql.demo.dao.Character;
import com.handy.graphql.demo.service.CharacterService;
import com.handy.graphql.demo.service.CharacterServiceGraphQL;
import com.handy.graphql.demo.service.CharacterServiceGraphQLImpl;
import com.handy.graphql.demo.service.CharacterServiceGraphQLImplWithTools;
import com.handy.graphql.demo.service.CharacterServiceGraphQLImplWithToolsAndMutationSchema;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/character")
@Produces(MediaType.APPLICATION_JSON)
public class CharacterResource {

  private final CharacterServiceGraphQL _characterService;

  public CharacterResource() {
    _characterService = new CharacterServiceGraphQLImpl();
    //_characterService = new CharacterServiceGraphQLImplWithTools();
    //_characterService = new CharacterServiceGraphQLImplWithToolsAndMutationSchema();
  }

  @Path("/get")
  @GET
  public Character getCharacter(@QueryParam("name") String name) {
    return _characterService.getCharacter(name);
  }

  /**
   * Can be improved by separating CRUD (?)
   * @param spec
   * @return
   */
  @Path("/graphql")
  @POST
  public Object graphQL(GraphQLSpec spec) {
    return _characterService.handleGraphQL(spec.getQuery());
  }
}
