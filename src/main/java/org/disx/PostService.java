package org.disx;

import java.util.UUID;
import java.util.List;
import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@Transactional
@ApplicationScoped
public class PostService {

    @Inject
    PostRepository repository;

    public UUID save(Post post) {
        repository.persist(post);
        return post.id;
    }

    public List<Post> findAllPosts() {
        return repository.listAll();
    }

    public Stream<Post> findPostsByTitle(String title) {
        return repository.listAll().stream()
                .filter(post -> post.getTitle().toLowerCase().contains(title.toLowerCase()));
    }

    public Response deletePost(UUID id) {
        Post post = repository.findById(id);
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No post found with id: " + id).build();
        }
        repository.deleteById(id);
        return Response.ok("Post deleted successfully").build();
    }
}
