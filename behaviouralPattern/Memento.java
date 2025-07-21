/*
    Components:
        - Originator(the object whose state to be captured)
        - Memento(inner class object that stores the state of the originator)
        - Caretaker(object responsible for saving or restoring the state)

 */

import java.util.*;

// originator class
class ResumeEditor{
    private String name;

    private String education;

    private String experience;

    private List<String> skills;

    public void setName(String name){
        this.name = name;
    }

    public void setEducation(String education){
        this.education = education;
    }

    public void setExperience(String experience){
        this.experience = experience;
    }

    public void setSkills(List<String> skills){
        this.skills = skills;
    }

    public void printResume(){
        System.out.println("-----Resume-----");

        System.out.println("Name:"+name);
        System.out.println("Education:"+education);
        System.out.println("Experience:"+experience);
        System.out.println("Skills:"+skills);

        System.out.println("-----------------");
    }

    public ResumeSnapshotMemento save(){
        return new ResumeSnapshotMemento(name, education, experience, List.copyOf(skills));
    }

    public void restore(ResumeSnapshotMemento memento){
        this.name = memento.getName();

        this.education = memento.getEducation();

        this.experience = memento.getExperience();

        this.skills = memento.getSkills();
    }

    // Inner memento class to implement encapsulation
    static class ResumeSnapshotMemento{
        private final String name;

        private final String education;

        private final String experience;

        private final List<String> skills;

        private ResumeSnapshotMemento(String name, String education, String experience, List<String> skills){
            this.name = name;

            this.education = education;

            this.experience = experience;

            this.skills = skills;
        }

        private String getName(){
            return name;
        }

        private String getEducation(){
            return education;
        }

        private String getExperience(){
            return experience;
        }

        private List<String> getSkills(){
            return skills;
        }
    }
}

// care-taker that coordinates with orginator and memento/snapshot
class ResumeHistory{
    private Stack<ResumeEditor.ResumeSnapshotMemento> history;

    public ResumeHistory(){
        this.history = new Stack<>();
    }

    public void saveCheckpoint(ResumeEditor editor){
        ResumeEditor.ResumeSnapshotMemento checkpoint = editor.save();

        history.push(checkpoint);
    }

    public void restoreToPreviousCheckpoint(ResumeEditor editor){
        System.out.println("Restoring to checkpoint");
        
        if(!history.isEmpty()){
            editor.restore(history.pop());
        }
    }

}

class Memento{
    public static void main(String[] args) {
        ResumeHistory history = new ResumeHistory();

        ResumeEditor editor = new ResumeEditor();
        editor.setName("Bhaskar");
        editor.setEducation("Masters");
        editor.setExperience("Lecturer at NAU");
        editor.setSkills(List.of("Coding", "HHLD", "LLD"));
        editor.printResume();

        history.saveCheckpoint(editor);

        editor.setExperience("Engineer at Amazon");
        editor.setName("Veera Surya Bhaskar");
        editor.printResume();

        history.saveCheckpoint(editor);

        editor.setEducation("PHD");
        editor.printResume();

        history.restoreToPreviousCheckpoint(editor);

        editor.printResume();
    }
}