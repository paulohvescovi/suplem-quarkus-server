package br.com.takuara.enumeration;

import javax.persistence.Column;
import java.util.Arrays;

public enum CircumferenceFields implements EnumLabel {

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

    public static CircumferenceFields fromColumn(Column columnDataBase){
        return Arrays.stream(CircumferenceFields.values())
                .filter(f -> f.getColumnName().equals(columnDataBase.name()))
                .findFirst()
                .get();
    }

    @Override
    public String getLabel() {
        return this.columnName;
    }

    @Override
    public String getName() {
        return name();
    }
}
