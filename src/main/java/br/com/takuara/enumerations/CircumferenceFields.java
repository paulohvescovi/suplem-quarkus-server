package br.com.takuara.enumerations;

public enum CircumferenceFields {

    USER_ID("user_id"),
    NECK("neck"),
    SHOULDER("shoulder"),
    CHEST("chest"),
    WAIST("waist"),
    GLUTES("glutes"),
    LEFT_BICEPS("left_biceps"),
    RIGHT_BICEPS("right_biceps"),
    LEFT_FOREARM("left_forearm"),
    RIGHT_FOREARM("right_forearm"),
    LEFT_THIGH("left_thigh"),
    RIGHT_THIGH("right_thigh"),
    LEFT_CALF("left_calf"),
    RIGHT_CALF("right_Calf");

    public String columnName;

    CircumferenceFields(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
