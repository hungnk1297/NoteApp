package com.vmo.hungnk.noteapp.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String EMPTY = "";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ModelAttributeConstant {
        public static final String LOGGED_IN_USER_ID = "userID";
        public static final String LOGGED_IN_USER_NAME = "username";

        public static final String NOTE_REQUEST = "noteRequest";
        public static final String UNCOMPLETED_NOTES = "uncompletedNotes";
        public static final String ALL_NOTES = "allNotes";

        public static final String SUCCESS_REGISTER_USER = "success_RegisterUser";

        public static final List<String> CLEAR_SESSION_ATTRIBUTES =
                Stream.of(LOGGED_IN_USER_ID,
                        LOGGED_IN_USER_NAME,
                        ALL_NOTES,
                        UNCOMPLETED_NOTES,
                        SUCCESS_REGISTER_USER)
                        .collect(Collectors.toUnmodifiableList());
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TemplateConstant {
        public static final String REDIRECT = "redirect:/";
        public static final String INDEX = "index";
        public static final String HOME = "home";
        public static final String ERROR = "error";
        public static final String USER_LOGIN = "user/login";
        public static final String USER_REGISTER = "user/register";
    }
}
