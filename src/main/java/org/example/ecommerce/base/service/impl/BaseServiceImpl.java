package org.example.ecommerce.base.service.impl;

import lombok.AllArgsConstructor;
import org.example.ecommerce.base.entity.BaseEntity;
import org.example.ecommerce.base.repository.BaseRepository;
import org.example.ecommerce.base.service.BaseService;
import org.hibernate.Session;
import org.hibernate.TransactionException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class BaseServiceImpl<ID extends Serializable, Entity extends BaseEntity<ID>,
        Repository extends BaseRepository<ID, Entity>> implements BaseService<ID, Entity> {

    protected final Session session;
    protected final Repository repository;

    @Override
    public void save(Entity entity) {
        try {
            session.getTransaction().begin();
            repository.save(entity);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void update(Entity entity) {
        try {
            session.getTransaction().begin();
            repository.update(entity);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void remove(Entity entity) {
        try {
            session.getTransaction().begin();
            repository.remove(entity);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            e.getStackTrace();
        }
    }

    @Override
    public Optional<Entity> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Entity> findAll() {
        session.getTransaction().begin();
        Collection<Entity> entities = repository.findAll();
        session.getTransaction().commit();
        return entities;
    }
}
