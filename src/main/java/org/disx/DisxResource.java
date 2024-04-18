package org.disx;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/disxs")
@Transactional
public class DisxResource {

    @Inject
    DisxService DisxService;

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello";
    }

    @POST
    public Response createDisx(Disx Disx) {
        DisxService.save(Disx);
        return Response.ok(Disx.id).build();
    }

    @GET
    public List<Disx> getDisxs() {
        return DisxService.findAllDisxs();
    }

    @GET
    @Path("/search")
    public List<Disx> findDisxsByTitle(@QueryParam("title") String title) {
        return DisxService.findDisxsByTitle(title).toList();
    }

    @GET
    @Path("/{id}")
    public Disx getDisxById(@PathParam("id") Long id) {
        return DisxService.findDisxById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDisxById(@PathParam("id") Long id) {
        DisxService.deleteDisx(id);
        return Response.ok(id).build();
    }
}
