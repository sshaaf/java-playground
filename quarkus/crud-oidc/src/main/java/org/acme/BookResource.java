package org.acme;

import org.jboss.resteasy.reactive.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/books")
public class BookResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    @NoCache
    public List<Book> getAll() {
        return Book.listAll();

    }

    @GET
    @Path("/{isbn}")
    @RolesAllowed("user")
    @NoCache
    public Book getOne(@PathParam("isbn") String isbn) {
        Book entity = Book.findById(isbn);
        if (entity == null) {
            throw new WebApplicationException("Book with id of " + isbn + " does not exist.", Response.Status.NOT_FOUND);
        }
        return entity;
    }

    @POST
    @RolesAllowed("admin")
    @NoCache
    @Transactional
    public Response create(@Valid Book item) {
        item.persist();
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    @NoCache
    @Transactional
    public Response update(@Valid Book book, @PathParam("id") String isbn) {
        Book entity = Book.findById(isbn);
        entity.title = book.title;
        entity.genre = book.genre;
        entity.summary = book.summary;
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{isbn}")
    @RolesAllowed("admin")
    @NoCache
    @Transactional
    public Response deleteOne(@PathParam("isbn") String isbn) {
        Book entity = Book.findById(isbn);
        if (entity == null) {
            throw new WebApplicationException("Book with isbn of " + isbn + " does not exist.", Response.Status.NOT_FOUND);
        }
        entity.delete();
        return Response.noContent().build();
    }
}
