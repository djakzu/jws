package fr.epita.assistants.yakamon.presentation.rest;

import fr.epita.assistants.yakamon.converter.PlayerConverter;
import fr.epita.assistants.yakamon.domain.entity.PlayerEntity;
import fr.epita.assistants.yakamon.domain.service.PlayerService;
import fr.epita.assistants.yakamon.presentation.api.request.MoveRequest;
import fr.epita.assistants.yakamon.presentation.api.response.MoveResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/move")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @Inject PlayerService playerService;
    @Inject PlayerConverter playerConverter;

    @POST
    public Response move(MoveRequest request) {
        if (request == null || request.direction == null) {
            return Response.status(400).entity("Missing direction").build();
        }

        try {
            PlayerEntity updatedPlayer = playerService.movePlayer(request.direction);
            MoveResponse responseDTO = playerConverter.toResponse(updatedPlayer);
            return Response.ok(responseDTO).build();

        } catch (WebApplicationException e) {
            return e.getResponse();
        } catch (Exception e) {
            return Response.status(400).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }
}