package br.com.takuara.autocomplete;

import br.com.takuara.enumeration.CircumferenceFields;
import br.com.takuara.enumeration.Genre;
import br.com.takuara.framework.annotations.JsonResource;
import br.com.takuara.framework.security.Role;
import br.com.takuara.utils.EnumUtils;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@JsonResource
@RolesAllowed({"USER", "ADMIN"})
@Path("/complete")
public class AutoCompleteResource {

    @GET
    @Path("roles")
    public Response completeRoles(){
        return Response.ok(EnumUtils.toComplete(Role.values())).build();
    }

    @GET
    @Path("genres")
    public Response completeGenre(){
        return Response.ok(EnumUtils.toComplete(Genre.values())).build();
    }

    @GET
    @Path("circumferences_fields")
    public Response completeCircumferenceFields(){
        return Response.ok(EnumUtils.toComplete(CircumferenceFields.values())).build();
    }

}
