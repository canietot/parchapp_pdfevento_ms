package parchapp.event.service;

import parchapp.event.model.Event;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class EventService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Event> getAllEvents(int first, int maxResult) {
        return entityManager.createNamedQuery(Event.FIND_ALL)
                .setFirstResult(first).setMaxResults(maxResult).getResultList();
    }

    public Event getEventById(String _id){
        return entityManager.createNamedQuery("Event.getEventById", Event.class)
                .setParameter("_id", _id).getSingleResult();
    }

    public Event createEvent(Event event) {
        entityManager.persist(event);
        entityManager.flush();
        return event;
    }

    public Event updateEvent(String _id, Event event) {
    	Event userToUpdate = entityManager.find(Event.class, _id);
        userToUpdate.setName(event.getName());
        userToUpdate.setDate(event.getDate());
        userToUpdate.setDescription(event.getDescription());
        userToUpdate.setLocation(event.getLocation());
        userToUpdate.setType(event.getType());
        entityManager.merge(userToUpdate);
        return entityManager.find(Event.class, _id);
    }

    public String deleteEvent(String _id) {
    	Event eventToDelete = entityManager.find(Event.class, _id);
        entityManager.remove(eventToDelete);
        return _id;
    }
}
