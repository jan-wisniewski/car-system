package com.app.ui.user_data;

import com.app.persistence.enums.CarBodyType;
import com.app.persistence.enums.EngineType;
import com.app.service.enums.SortCriterion;
import com.app.service.enums.StatsCriterion;
import com.app.ui.exception.UserDataException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class UserDataService {

    private UserDataService() {
    }

    private final static Scanner sc = new Scanner(System.in);

    public static String getString(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public static int getInteger(String message) {
        System.out.println(message);
        String value = sc.nextLine();
        if (!value.matches("\\d+")) {
            throw new UserDataException("Int value is not correct");
        }
        return Integer.parseInt(value);
    }

    public static BigDecimal getBigDecimal(String message) {
        System.out.println(message);
        String value = sc.nextLine();
        if (!value.matches("(\\d+\\.)?\\d+")) {
            throw new UserDataException("decimal value is not correct");
        }
        return new BigDecimal(value);
    }

    public static Boolean getBoolean(String message) {
        System.out.println(message);
        String value = sc.nextLine();
        return Character.toLowerCase(value.charAt(0)) == 'y';
    }

    public static SortCriterion getSortCriterion() {
        AtomicInteger counter = new AtomicInteger();
        String sortCriterionList = Arrays
                .asList(SortCriterion.values())
                .stream()
                .map(criterion -> counter.incrementAndGet() + ". " + criterion.toString())
                .collect(Collectors.joining("\n"));
        System.out.println(sortCriterionList);
        int decision;
        do {
            decision = getInteger("Choose correct sort criterion number:");
        }
        while (decision < 1 || decision > SortCriterion.values().length);
        return SortCriterion.values()[decision - 1];
    }

    public static CarBodyType getCarBodyType() {
        AtomicInteger counter = new AtomicInteger();
        String carBodyTypeList = Arrays
                .asList(CarBodyType.values())
                .stream()
                .map(carBody -> counter.incrementAndGet() + ". " + carBody.toString())
                .collect(Collectors.joining("\n"));
        System.out.println(carBodyTypeList);
        int decision;
        do {
            decision = getInteger("Choose correct car body type");
        }
        while (decision < 1 || decision > CarBodyType.values().length);
        return CarBodyType.values()[decision - 1];
    }

    public static void close() {
        if (sc != null) {
            sc.close();
        }
    }

    public static EngineType getEngineType() {
        AtomicInteger counter = new AtomicInteger();
        String engineTypes = Arrays
                .asList(EngineType.values())
                .stream()
                .map(engineType -> counter.incrementAndGet() + ". " + engineType.toString())
                .collect(Collectors.joining("\n"));
        System.out.println(engineTypes);
        int decision;
        do {
            decision = getInteger("Choose correct engine type");
        }
        while (decision < 1 || decision > EngineType.values().length);
        return EngineType.values()[decision - 1];
    }

    public static StatsCriterion getStatsCriterion() {
        AtomicInteger counter = new AtomicInteger();
        String statsCriterion = Arrays
                .asList(StatsCriterion.values())
                .stream()
                .map(c -> counter.incrementAndGet() + ". " + c.toString())
                .collect(Collectors.joining("\n"));
        System.out.println(statsCriterion);
        int decision;
        do {
            decision = getInteger("Choose correct stats criterion");
        }
        while (decision < 1 || decision > StatsCriterion.values().length);
        return StatsCriterion.values()[decision - 1];
    }

    public static List<String> getList (String message){
        System.out.println(message);
        String values = sc.nextLine();
        String[] components =  values.toUpperCase().replaceAll(" ","").split(",");
        return Arrays.asList(components);
    }
}
