package com.mydesign.utils;

public class Constants {

    public final static String NO_NAME_SPACE = "";
    public final static String PREFIX_URL = "http://bo.fks.com";
    public final static String NO_ACTION = "";
    public final static String ARTICLE_BY_ARTICLE_CODE = "bo:searchArticleByEAN";


    public static final String[] TITLES = {"Approved", "Pending", "Rejected"};
    public static final String[] REQUEST_TYPES = {"Approved", "Open", "Rejected"};

    public static final String USER_RESPONSE = "user_response";
    public static final String DESIRED_PRICE = "desired_price";
    public static final String REQUEST_TYPE = "request_type";
    public static final String REQUEST_DETAILS = "request_details";

    public static final String NO_RECORDS = "No Record Found.";


    public interface Status {
        String APPROVE = "Approved";
        String REJECT = "Rejected";
        String PENDING = "Open";
    }

    public interface ArticleDetails {
        String ARTICLE_CODE = "articleCode";
        String ARTICLE_DESC = "articleDesc";
        String ARTICLE_TYPE = "articletype";
        String BRAND = "brand";
        String BRAND_DESC = "brandDesc";
        String LOB_CODE = "lobCode";
        String LOB_NAME = "lobName";
        String MC_CODE = "mcCode";
        String MC_DESC = "mcDesc";
        String MRP = "mrp";
        String MSG = "msg";
        String SEASON_CODE = "seasonCode";
        String UOM = "uom";
        String WARRANTY_FLAG = "warrntyFlag";
    }
}
