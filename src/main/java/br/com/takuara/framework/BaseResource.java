package br.com.takuara.framework;

import br.com.takuara.framework.annotations.JsonResource;
import br.com.takuara.user.User;
import br.com.takuara.user.UserService;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Optional;

@JsonResource
@RolesAllowed({"USER", "ADMIN"})
public abstract class BaseResource<T extends PanacheEntity>{

    @Context protected SecurityContext securityContext;

    @Inject protected JsonWebToken jwt;
    @Inject protected UserService userService;

    protected abstract BaseService<T> getService();

    protected User getAuthenticatedUser(){
        return userService.findByEmail(securityContext.getUserPrincipal().getName());
    }

    @GET
    public Response findAll(){
        return Response.ok(getService().findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Optional.ofNullable(getService().findById(id))
                .map(entity -> Response.ok(entity).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, T entity){
        return Optional.ofNullable(getService().findById(id))
                    .map(entityPersisted -> {
                        entity.id = entityPersisted.id;
                        return save(entity);
                    })
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response save(@Valid T entity) {
        boolean isNewEntity = entity.id == null;
        return Response
                .status(isNewEntity ? Response.Status.CREATED : Response.Status.ACCEPTED)
                .entity(getService().save(entity))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        return Optional.ofNullable(getService().findById(id))
                .map(entity -> {
                    getService().delete(entity);
                    return Response.noContent().build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

}
