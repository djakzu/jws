package fr.epita.assistants.yakamon.presentation.rest;

import fr.epita.assistants.yakamon.converter.YakadexEntryConverter;
import fr.epita.assistants.yakamon.data.model.YakadexEntryModel;
import fr.epita.assistants.yakamon.domain.service.YakadexService;
import fr.epita.assistants.yakamon.presentation.api.response.YakadexResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class YakadexResource {
    @Inject
    YakadexService yakadexService;
    @Inject
    YakadexEntryConverter yakadexEntryConverter;

    @Path("/yakadex")
    @GET
    public Response yakadex() {
        return Response.ok(new YakadexResponse(yakadexService.getYakadex())).build();
    }

    @Path("/yakadex/{id}")
    @GET
    public Response yakadex_id(@PathParam("id") Integer id) {
        try {
            YakadexEntryModel model = yakadexService.getYakamon(id);
            if (model == null) {
                return Response.status(404).entity("Yakamon not found").build();
            }
            return Response.ok(yakadexEntryConverter.toResponse(model)).build();
        } catch (WebApplicationException e) {
            return e.getResponse();
        } catch (Exception e) {
            return Response.status(400).entity("").build();
        }

    }
}
