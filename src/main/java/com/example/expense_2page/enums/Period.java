package com.example.expense_2page.enums;

public enum Period {
    TODAY("今日分"),
    WEEK("1週間"),
    MONTH("1カ月"),
    YEAR("1年");

    private final String label;

    Period(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}