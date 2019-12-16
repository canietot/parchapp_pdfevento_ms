package parchapp.event.resource;

import parchapp.event.model.Event;
import parchapp.event.service.EventService;
import parchapp.event.util.GeneratePdfReport;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.List;

@Path("/events")
public class EventResource {

    ResponseBuilder response;

    @Context
    UriInfo uriInfo;

    @EJB
    EventService eventService;

    @GET
    public List<Event> getAllEvents(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return eventService.getAllEvents(first, maxResult);
    }

    @GET
    @Path("/{_id}")
    public Response getEventById(@PathParam("_id") String _id) {
        Event event = eventService.getEventById(_id);
        
        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(event);
        
        response = javax.ws.rs.core.Response.ok((Object) bis);
        
        response.status(Response.Status.OK);
        response.type("multipart/form-data");
        response.header("Content-Disposition",  "filename=restfile.pdf");
        return response.build();
    }

    @POST
    public Response createEvent(Event event) {
        Event createdEvent = eventService.createEvent(event);
        response = Response.status(Response.Status.CREATED);
        response.entity(createdEvent);
        return response.build();
    }

    @PUT
    @Path("{id}")
    public Response updateEvent(@PathParam("_id") String _id, Event event) {
        Event updatedEvent = eventService.updateEvent(_id, event);
        response = Response.status(Response.Status.OK);
        response.entity(updatedEvent);
        return response.build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteEvent(@PathParam("_id") String _id) {
    	String deletedEventId = eventService.deleteEvent(_id);
        response = Response.status(Response.Status.OK);
        response.entity(deletedEventId);
        return response.build();
    }
}
