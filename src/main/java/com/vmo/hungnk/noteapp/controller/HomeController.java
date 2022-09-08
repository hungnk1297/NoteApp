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
    public String home(Model model) {
        if (loggedIn()) {
            HttpSession session = getSession();
            Long sessionUserID = (Long) session.getAttribute(LOGGED_IN_USER_ID);

            //  Get all Notes
            List<NoteResponseDTO> sessionUserNotes;
            if (session.getAttribute(ALL_NOTES) != null)
                sessionUserNotes = (List<NoteResponseDTO>) session.getAttribute(ALL_NOTES);
            else
                sessionUserNotes = noteService.findAllNotesByUser(sessionUserID);

            model.addAttribute(ALL_NOTES, sessionUserNotes);
//            session.removeAttribute(ALL_NOTES);

            return HOME;
        } else
            return INDEX;
    }
}
