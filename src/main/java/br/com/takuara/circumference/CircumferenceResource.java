package br.com.takuara.circumference;

import br.com.takuara.exceptions.ResponseApiException;
import br.com.takuara.framework.BaseResource;
import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.annotations.JsonResource;
import br.com.takuara.user.User;
import br.com.takuara.user.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.Optional;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Path("circunferences")
@JsonResource
public class CircumferenceResource extends BaseResource<Circumference> {

    @Inject private CircumferenceService circumferenceService;
    @Inject private UserService userService;

    @Override
    protected BaseService<Circumference> getService() {
        return circumferenceService;
    }

    @Override
    @GET
    public Response findAll() {
        return Response.ok(circumferenceService.findAll(getAuthenticatedUser())).build();
    }

    @Override
    @POST
    public Response save(@Valid Circumference entity) {
        try {
            checkIfExistsUser(entity);
            checkIfExistsCircumferenceForThisUser(entity);
            return super.save(entity);
        } catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseApiException.notFound(e.getMessage()))
                    .build();
        } catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ResponseApiException.badRequest(e.getMessage()))
                    .build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseApiException.internalServer(e.getMessage()))
                    .build();
        }
    }

    private void checkIfExistsCircumferenceForThisUser(@Valid Circumference entity) throws BadRequestException{
        if (entity.id == null && isNotEmpty(circumferenceService.findAll(entity.getUser()))){
            throw new BadRequestException("use PUT method for this user, he already has circumference values");
        }
    }

    private User checkIfExistsUser(Circumference entity) throws NotFoundException {
        return Optional.ofNullable(userService.findById(entity.getUser().id))
                .map(user -> {
                    entity.setUser(user);
                    return user;
                })
                .orElseThrow(() -> new NotFoundException("not exists user with this id"));
    }
}

