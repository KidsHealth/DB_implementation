package es.uma.health.kids.domain.model.medication;

import java.util.Objects;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class MedicineCommercialName {

    private String commercialNameString;

    public MedicineCommercialName(String commercialNameString) {
        this.commercialNameString = commercialNameString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MedicineCommercialName that = (MedicineCommercialName) o;
        return Objects.equals(commercialNameString, that.commercialNameString);
    }

    public String value() {
		return commercialNameString;
	}

	public void setCommercialNameString(String commercialNameString) {
		this.commercialNameString = commercialNameString;
	}

	@Override
    public int hashCode() {
        return Objects.hash(commercialNameString);
    }
}
