package br.com.takuara.utils;

import br.com.takuara.autocomplete.EnumComplete;
import br.com.takuara.enumeration.EnumLabel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumUtils {

    public static List<EnumComplete> toComplete(Object[] values) {
        return Arrays.asList(values)
                .stream()
                .map(value ->
                        EnumComplete.builder()
                                .key((((EnumLabel) value)).getName())
                                .label((((EnumLabel) value)).getLabel())
                                .build())
                .collect(Collectors.toList());
    }

}
