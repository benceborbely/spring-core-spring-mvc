package guru.springframework.services;

import guru.springframework.domain.DomainObject;

import java.util.*;

public abstract class AbstractMapService {

    protected Map<Integer, DomainObject> domainObjects = new HashMap<>();

    public AbstractMapService() {
        this.domainObjects = domainObjects;
        loadDomainObjects();
    }

    public List<DomainObject> getAll() {
        return new ArrayList<>(domainObjects.values());
    }

    public DomainObject getById(Integer id) {
        return domainObjects.get(id);
    }

    public DomainObject saveOrUpdate(DomainObject domainObject) {
        if (domainObject == null) {
            throw new RuntimeException("Product cannot be null.");
        }

        if (domainObject.getId() == null) {
            domainObject.setId(getNextKey());
        }

        this.domainObjects.put(domainObject.getId(), domainObject);

        return domainObject;
    }

    public void delete(Integer id) {
        this.domainObjects.remove(id);
    }

    protected abstract void loadDomainObjects();

    private Integer getNextKey() {
        return Collections.max(domainObjects.keySet()) + 1;
    }

}
