import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by andy on 1/28/2016.
 */
public class insertPrompt extends ActionBarActivity {

    EditText word_input;
    EditText definition_input;
    Intent intent;
    String word;
    String definition;
    Button addButton;
    dbHandler mydbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.insert_main);
        intent = getIntent();
        word = intent.getStringExtra(DictionaryActivity.EXTRA_MESSAGE);
        word_input = (EditText)findViewById(R.id.word_input);
        definition_input = (EditText)findViewById(R.id.definition_input);
        word_input.setText(word);
        addButton = (Button)findViewById(R.id.add);
        mydbHandler = new dbHandler(this, null, null, 1);
    }

    public void wordInsert(View view) {
        word = word_input.getText().toString();
        definition = definition_input.getText().toString();
        Word newWord = new Word();
        newWord.setWord(word);
        newWord.setDefinition(definition);
        mydbHandler.addDictionary(newWord);
        Intent intentApp = new Intent(this, DictionaryActivity.class);
        startActivity(intentApp);
    }
}
