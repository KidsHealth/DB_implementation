package es.uma.health.kids.domain.model.event;

import java.util.Objects;


public class DocumentType {

    private String typeString;

    public DocumentType(String typeString) {
        this.typeString = typeString;
    }
    
    public String value() {
    	return typeString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocumentType that = (DocumentType) o;
        return Objects.equals(typeString, that.typeString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeString);
    }
}
