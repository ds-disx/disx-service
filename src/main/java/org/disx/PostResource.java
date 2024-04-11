package org.disx;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/posts")
@Transactional
public class PostResource {

    @Inject
    PostService postService;

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello";
    }

    @POST
    public Response createPost(Post post) {
        postService.save(post);
        return Response.ok(post.id).build();
    }

    @GET
    @Path("/test")
    public Post getHe() {
        Post post = new Post();
        post.title = "Hello";
        post.content = "Hello World!";
        return post;
    }

    @GET
    public List<Post> getPosts() {
        return postService.findAllPosts();
    }

    @GET
    @Path("/posts/search")
    public List<Post> findPostsByTitle(@QueryParam("title") String title) {
        return postService.findPostsByTitle(title).toList();
    }

    @DELETE
    @Path("/posts/{id}")
    public Response deletePostById(@PathParam("id") UUID id) {
        return postService.deletePost(id);
    }
}
