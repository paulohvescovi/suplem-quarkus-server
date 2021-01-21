package br.com.takuara.enumeration;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Genre implements EnumLabel {

    MALE("Masculine"),
    FEMALE("Feminine"),
    NEUTER("Neuter");

    private String label;

    Genre(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getName() {
        return name();
    }


}
