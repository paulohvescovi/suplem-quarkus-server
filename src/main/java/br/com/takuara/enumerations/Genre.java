package br.com.takuara.enumerations;

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
}
