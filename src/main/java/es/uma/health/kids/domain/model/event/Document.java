package es.uma.health.kids.domain.model.event;

public class Document {

    private DocumentPath path;
    private DocumentType type;
    private EventId eventId;

    public Document(DocumentPath path, DocumentType type) {
        this.path = path;
        this.type = type;
    }

    public Document(DocumentPath path) {
        this.path = path;
    }

	public DocumentPath path() {
		return path;
	}

	public void path(DocumentPath path) {
		this.path = path;
	}

	public DocumentType type() {
		return type;
	}

	public void type(DocumentType type) {
		this.type = type;
	}

	public EventId eventId() {
		return eventId;
	}

	public void eventId(EventId eventId) {
		this.eventId = eventId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
    
}
