package fr.epita.assistants.yakamon.presentation.rest;

import fr.epita.assistants.yakamon.converter.PlayerConverter;
import fr.epita.assistants.yakamon.data.model.PlayerModel;
import fr.epita.assistants.yakamon.domain.service.MoveService;
import fr.epita.assistants.yakamon.domain.service.PlayerService;
import fr.epita.assistants.yakamon.presentation.api.request.MoveRequest;
import fr.epita.assistants.yakamon.presentation.api.response.MoveResponse;
import fr.epita.assistants.yakamon.presentation.api.response.PlayerResponse;
import fr.epita.assistants.yakamon.presentation.api.response.YakamonResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @Inject PlayerService playerService;
    @Inject PlayerConverter playerConverter;
    @Inject
    MoveService moveService;

    @Path("/move")
    @POST
    public Response move(MoveRequest request) {
        if (request == null || request.direction == null) {
            return Response.status(400).entity("Missing direction").build();
        }

        try {
            PlayerModel updatedPlayer = moveService.movePlayer(request.direction);
            if (updatedPlayer == null) {
                return Response.status(400).entity("Not here").build();
            }
            MoveResponse responseDTO = playerConverter.toResponse(updatedPlayer);
            return Response.ok(responseDTO).build();

        } catch (Exception e) {
            return Response.status(400).entity("").build();
        }
    }

    @Path("/player")
    @GET
    public Response player() {
        try {
            PlayerResponse responseDTO = playerConverter.toPlayerResponse(playerService.getPlayerModel());
            return Response.ok(responseDTO).build();

        } catch (WebApplicationException e) {
            return e.getResponse();
        } catch (Exception e) {
            return Response.status(400).entity("").build();
        }
    }

/*
    @Path("/catch")
    @POST
    public Response catchYakamon() {
        try {
            PlayerModel updatedPlayer = playerService.movePlayer(request.direction);
            YakamonResponse responseDTO = playerConverter.toResponse(updatedPlayer);
            return Response.ok(responseDTO).build();

        } catch (WebApplicationException e) {
            return e.getResponse();
        } catch (Exception e) {
            return Response.status(400).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }*/
}