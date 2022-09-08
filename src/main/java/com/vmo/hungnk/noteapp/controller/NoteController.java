package com.vmo.hungnk.noteapp.controller;

import com.vmo.hungnk.noteapp.model.request.NoteRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.vmo.hungnk.noteapp.constant.Constant.ModelAttributeConstant.*;
import static com.vmo.hungnk.noteapp.constant.Constant.TemplateConstant.*;
import static com.vmo.hungnk.noteapp.util.MvcUtil.*;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {



    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute(NOTE_REQUEST, new NoteRequestDTO());
        model.addAttribute(ALL_NOTES, null);
        model.addAttribute(UNCOMPLETED_NOTES, 0);
    }

}
