import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by andy on 1/21/2016.
 */
public class DictionaryActivity extends ActionBarActivity {

    EditText wordBox;
    TextView definitionBox;
    Button add;
    dbHandler mydbHandler;
    public final static String EXTRA_MESSAGE = "com.example.andy.internetdictionary.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView((R.layout.dictionary_main));
        wordBox = (EditText) findViewById(R.id.word);
        definitionBox = (TextView) findViewById(R.id.definition);
        add = (Button) findViewById(R.id.add_prompt);
        mydbHandler = new dbHandler(this, null, null, 1);

        populateList();
    }

    private void populateList() {
        String[] words = mydbHandler.getWords();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.index_main, words);

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    public void lookup(View view) {

        System.out.println("Search Phrase 1");
        Word word = mydbHandler.findWord(wordBox.getText().toString());
        System.out.println("Search Phrase 2");
        if(word != null) {
            definitionBox.setText(String.valueOf(word.getDefinition()));
            wordBox.setText("");
            add.setVisibility(View.INVISIBLE);
        } else {
            definitionBox.setText("No Match Founded!");
            add.setVisibility(View.VISIBLE);
        }
        System.out.println("Search Phrase 3");
        mydbHandler.close();
    }

    public void addPrompt(View view) {
        Intent intentApp = new Intent(this, insertPrompt.class);
        String message = (((EditText)findViewById(R.id.word)).getText()).toString();
        intentApp.putExtra(EXTRA_MESSAGE, message);
        startActivity(intentApp);
    }
}
