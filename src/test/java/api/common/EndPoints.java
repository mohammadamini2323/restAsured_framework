package api.common;

import groovy.transform.PackageScope;

public class EndPoints {
    public static final String ACCOUNT_AUTHORIZED="/Account/v1/Authorized";
    public static final String ACCOUNT_GENERATE_TOKEN="/Account/v1/GenerateToken";
    public static final String GET_USER_BY_ID="/Account/v1/User/";
    public static final String CREATE_USER="/Account/v1/User";
    public static final String DELETE_USER_BY_ID="/Account/v1/User/";



    public static final String GET_ALL_BOOKS="/BookStore/v1/Books";
    public static final String UPDATE_A_BOOK="/BookStore/v1/Books/{ISBN}";
    public static final String GET_BOOK_BY_ISBN="/BookStore/v1/Book?ISBN=";
    public static final String POST_BOOK_TO_USER="/BookStore/v1/Books";
    public static final String DELETE_BOOK_FROM_USER="/BookStore/v1/Book";
    public static final String UPDATE_BOOK_BY_ISBN = "/BookStore/v1/Books/{OLD_ISBN}";

}
