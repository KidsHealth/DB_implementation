package es.uma.health.kids.domain.model.event;

import java.util.Objects;


public class DocumentPath {

    private String pathString;

    public DocumentPath(String pathString) {
        this.pathString = pathString;
    }
    
    public String value() {
    	return pathString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocumentPath that = (DocumentPath) o;
        return Objects.equals(pathString, that.pathString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathString);
    }

}
