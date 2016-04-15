/**
 * Created by andy on 1/23/2016.
 */
public class Word {

    private int id;
    private String word;
    private String definition;
    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setWord(String name) {
       this.word = name;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
