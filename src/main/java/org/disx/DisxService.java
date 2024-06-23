package org.disx;

import java.util.List;

import java.time.LocalDateTime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class DisxService {

    @Inject
    DisxRepository repository;

    public void save(Disx disx) {
        disx.setCreatedAt(LocalDateTime.now());
        disx.setCommentCount(0);
        repository.persist(disx);
    }

    public List<Disx> findAllDisxs() {
        return repository.listAllSorted();
    }

    public Disx findDisxById(Long id) {
        return repository.findById(id);
    }

    public List<Disx> findDisxsByTitle(String title) {
        return repository.listAllSorted().stream()
                .filter(Disx -> Disx.getTitle().toLowerCase().contains(title.toLowerCase())).toList();
    }

    public void deleteDisx(Long id) {
        repository.deleteById(id);
    }

    public List<Disx> findDisxsByUsername(String username) {
        return repository.findByUsername(username);
    }
}
