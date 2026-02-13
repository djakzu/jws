package fr.epita.assistants.yakamon.presentation.rest;

import fr.epita.assistants.yakamon.converter.GameConverter;
import fr.epita.assistants.yakamon.domain.entity.GameEntity;
import fr.epita.assistants.yakamon.domain.service.GameService;
import fr.epita.assistants.yakamon.presentation.api.request.StartRequest;
import fr.epita.assistants.yakamon.presentation.api.response.StartResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/start")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    @Inject
    GameService gameService;
    @Inject
    GameConverter gameConverter;

    @POST
    public Response start(StartRequest request) {
        if (request == null || request.getMapPath() == null || request.getPlayerName() == null) {
            return Response.status(400).entity("Invalid path or name provided").build();
        }

        try {
            GameEntity newGame = gameService.start(request);
            StartResponse responseDTO = gameConverter.toResponse(newGame);
            return Response.ok(responseDTO).build();

        } catch (WebApplicationException e) {
            return e.getResponse();
        } catch (Exception e) {
            return Response.status(400).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
