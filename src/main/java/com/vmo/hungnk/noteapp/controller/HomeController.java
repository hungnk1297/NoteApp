package com.vmo.hungnk.noteapp.controller;

import com.vmo.hungnk.noteapp.model.response.NoteResponseDTO;
import com.vmo.hungnk.noteapp.service.NoteService;

import java.util.List;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.vmo.hungnk.noteapp.constant.Constant.ModelAttributeConstant.*;
import static com.vmo.hungnk.noteapp.constant.Constant.TemplateConstant.*;
import static com.vmo.hungnk.noteapp.util.MvcUtil.*;

@RequestMapping(path = "/")
@Controller
@Slf4j
@AllArgsConstructor
public class HomeController {

    private final NoteService noteService;

    @GetMapping("/")
    public String index() {
        if (loggedIn()) {
            return REDIRECT + HOME;
        } else
            return INDEX;
    }

    @GetMapping("/home")
    public String home() {
        if (loggedIn()) {
            HttpSession session = getSession();
            if (session.getAttribute(LOGGED_IN_USER_ID) != null) {
                Long sessionUserID = (Long) session.getAttribute(LOGGED_IN_USER_ID);
                //  Get all Notes
                session.setAttribute(ALL_NOTES, noteService.findAllNotesByUser(sessionUserID));

                //  Get UnCompleted Notes
                session.setAttribute(UNCOMPLETED_NOTES, noteService.countUncompletedNotes(sessionUserID));
            }
            return HOME;
        } else
            return INDEX;
    }
}
