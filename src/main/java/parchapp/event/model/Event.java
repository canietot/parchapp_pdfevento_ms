package parchapp.event.model;

import javax.persistence.*;

@Entity
@Table(name = "events")
@NamedQueries({@NamedQuery(name = Event.FIND_ALL, query = "SELECT e FROM Event e"), 
			   @NamedQuery(name = Event.FIND_ID, query = "SELECT e FROM Event e WHERE e._id = :_id")})
public class Event {

    public static final String FIND_ALL = "Event.findAll";
    public static final String FIND_ID = "Event.getEventById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String _id;
    private String name;
    private String date;
    private String description;
    private String location;
    private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
