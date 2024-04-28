package org.disx;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisxRepository implements PanacheRepository<Disx> {
    Sort sortNewest = Sort.descending("createdAt");

    public List<Disx> findByUsername(String username) {
        return list("username", sortNewest, username);
    }

    public List<Disx> listAllSorted() {
        return listAll(sortNewest);
    }
}
