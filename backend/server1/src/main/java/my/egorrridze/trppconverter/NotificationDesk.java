package my.egorrridze.trppconverter;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/NotificationDesk")
public class NotificationDesk {
    List<Note> Notes;

    NotificationDesk(){
        Notes = new ArrayList<Note>();
    }

    @RequestMapping(path = "/Notes", method = RequestMethod.GET)
    public List<Note> getNotes() {
        return this.Notes;
    }

    @RequestMapping(path = "/Notes", method = RequestMethod.POST)
    public String addNotes(@RequestBody Note note) {
        Notes.add(note);
        return String.format("Сообщение \"%s\" добавлено в %s", note.message, note.time.toString());
    }

    @RequestMapping(path = "/Notes", method = RequestMethod.DELETE)
    public String deleteNotes(@RequestBody Note note){
        for (Note n: Notes)
            if (n.message.equalsIgnoreCase(note.message))
                if (Notes.remove(n)){
                    return String.format("Сообщение \"%s\" удалено.", note.message);}
        return String.format("Сообщение не найдено.", note.message);
    }

    @RequestMapping(path = "/Notes/F", method = RequestMethod.GET)
    public String clearNotes(){
        Notes.clear();
        return String.format("Очистка...");
    }
}
