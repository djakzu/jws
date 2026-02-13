package fr.epita.assistants.yakamon.presentation.rest;

import fr.epita.assistants.yakamon.converter.PlayerConverter;
import fr.epita.assistants.yakamon.converter.YakamonConverter;
import fr.epita.assistants.yakamon.data.model.PlayerModel;
import fr.epita.assistants.yakamon.data.model.YakamonModel;
import fr.epita.assistants.yakamon.domain.service.MoveService;
import fr.epita.assistants.yakamon.domain.service.PlayerService;
import fr.epita.assistants.yakamon.domain.service.YakadexService;
import fr.epita.assistants.yakamon.domain.service.YakamonService;
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
    @Inject
    YakamonService yakamonService;
    @Inject
    YakamonConverter yakamonConverter;

    @Path("/move")
    @POST
    public Response move(MoveRequest request) {
        if (request == null || request.direction == null) {
            return Response.status(400).entity("{\"message\": \"Missing direction\"}").build();
        }

        try {
            PlayerModel updatedPlayer = moveService.movePlayer(request.direction);
            if (updatedPlayer == null) {
                return Response.status(400).entity("{\"message\": \"Out of map\"}").build();
            }
            MoveResponse responseDTO = playerConverter.toResponse(updatedPlayer);
            return Response.ok(responseDTO).build();

        } catch (Exception e) {
            return Response.status(400).entity("{\"message\": \"\"}").build();
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
            return Response.status(400).entity("{\"message\": \"\"}").build();
        }
    }

/*
    @Path("/catch")
    @POST
    public Response catchYakamon() {
        try {
            YakamonModel caughtYakamonModel = yakamonService.catchYakamon();

            YakamonResponse response = yakamonConverter.toResponse(caughtYakamonModel);

            return Response.ok(response).build();

        } catch (WebApplicationException e) {
            return e.getResponse();
        } catch (Exception e) {
            return Response.status(400).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }*/
}