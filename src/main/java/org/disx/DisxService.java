package org.disx;

import java.util.List;
import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class DisxService {

    @Inject
    DisxRepository repository;

    public Disx save(Disx disx) {
        repository.persist(disx);
        return disx;
    }

    public Disx findDisxById(Long id) {
        return repository.findById(id);
    }

    public List<Disx> findAllDisxs() {
        return repository.listAll();
    }

    public Stream<Disx> findDisxsByTitle(String title) {
        return repository.listAll().stream()
                .filter(Disx -> Disx.getTitle().toLowerCase().contains(title.toLowerCase()));
    }

    public Long deleteDisx(Long id) {
        repository.deleteById(id);
        return id;
    }
}
