package com.example.moneytracker50;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Formatter {

    final static SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
    final static SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMM yyyy");
    final static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    final static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    final static Currency currency = Currency.getInstance(Locale.getDefault());

    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    public static String formatDate(Date date){
        return dateFormat.format(date);
    }

    public static String formatMonthName(Date date){
        return monthFormat.format(date);
    }

    public static String formatMonthYear(Date date){
        return monthYearFormat.format(date);
    }

    public static String formatYear(Date date){
        return yearFormat.format(date);
    }

    public static String formatMoney(BigDecimal money){
        return currency.getSymbol() + " " + decimalFormat.format(money);
    }
}
