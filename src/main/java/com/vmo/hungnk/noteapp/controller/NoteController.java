package com.vmo.hungnk.noteapp.controller;

import com.vmo.hungnk.noteapp.constant.NoteType;
import com.vmo.hungnk.noteapp.model.request.NoteRequestDTO;
import com.vmo.hungnk.noteapp.model.response.NoteResponseDTO;
import com.vmo.hungnk.noteapp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.vmo.hungnk.noteapp.constant.Constant.ModelAttributeConstant.*;
import static com.vmo.hungnk.noteapp.constant.Constant.TemplateConstant.*;
import static com.vmo.hungnk.noteapp.util.MvcUtil.loggedIn;
import static com.vmo.hungnk.noteapp.util.MvcUtil.loggedInUserID;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    //  Create Text Note
    @GetMapping(path = "/text")
    public String getCreateText() {
        if (!loggedIn())
            return USER_LOGIN;
        return NOTE_CREATE_TEXT;
    }

    @PostMapping("/text")
    public String createTextNote(Model model,
                                 @ModelAttribute("noteRequest") NoteRequestDTO noteRequestDTO) {
        Long userID = loggedInUserID();
        noteRequestDTO.setNoteType(NoteType.TEXT.getValue());
        NoteResponseDTO createdNote = noteService.saveNote(userID, noteRequestDTO);

        Long noteID = createdNote.getNoteID();
        return REDIRECT + NOTE + noteID;
    }
    //  Create Text Note

    //  Create Text Image Note
    @GetMapping(path = "/text-image")
    public String getCreateTextImage() {
        return NOTE_CREATE_IMAGE;
    }

    @PostMapping("text-image")
    public String createTextImageNote(Model model,
                                      @ModelAttribute("noteRequest") NoteRequestDTO noteRequestDTO) {
        //  TODO
        return NOTE_CREATE_IMAGE;
    }
    //  Create Text Image Note

    //  Create Multi-Checkboxes Note
    @GetMapping(path = "/multi-checkboxes")
    public String getCreateMultiCheckboxes() {
        return NOTE_CREATE_MULTI_CHECKBOXES;
    }

    @PostMapping("multi-checkboxes")
    public String createMultiCheckboxes(Model model,
                                        @ModelAttribute("noteRequest") NoteRequestDTO noteRequestDTO) {
        //  TODO
        return NOTE_CREATE_MULTI_CHECKBOXES;
    }
    //  Create Multi-Checkboxes Note

    @GetMapping(path = "/{noteID}")
    public String getNoteDetail(@PathVariable("noteID") Long noteID, Model model) {
        if (!loggedIn())
            return USER_LOGIN;

        NoteResponseDTO noteDetail = noteService.getNoteDetail(loggedInUserID(), noteID);
        model.addAttribute("noteDetail", noteDetail);
        return NOTE_DETAIL;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute(NOTE_REQUEST, new NoteRequestDTO());
        model.addAttribute(ALL_NOTES, null);
        model.addAttribute(UNCOMPLETED_NOTES, 0);
    }

}
