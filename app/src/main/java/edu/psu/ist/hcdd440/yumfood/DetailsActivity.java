package edu.psu.ist.hcdd440.yumfood;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import edu.psu.ist.hcdd440.yumfood.models.Ingredient;
import edu.psu.ist.hcdd440.yumfood.models.Recipe;

public class DetailsActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    private Button instructionSpeakButton, detailSpeakButton, ingredientSpeakButton;

    Recipe recipe;
    TextView txt_title, txt_author, txt_diets, txt_detail, txt_instruction, txt_ingredient;
    ImageView img_recipe;
    TextToSpeech t1;
    String textDiets, spokenInstructions, spokenDetails, textDetails, textIngredients, textInstructions;

    Ingredient[] ingredients = null;
    String[] diets = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_title = findViewById(R.id.text_detail_title);
        txt_author = findViewById(R.id.text_detail_author);
        txt_diets = findViewById(R.id.text_detail_diets);
        txt_detail = findViewById(R.id.text_detail_detail);
        txt_instruction = findViewById(R.id.text_detail_instruction);
        img_recipe = findViewById(R.id.img_detail_recipe);


        txt_ingredient = findViewById(R.id.text_detail_ingredients);


        recipe = (Recipe) getIntent().getSerializableExtra("data");

        txt_title.setText(recipe.getTitle());
        txt_title.setContentDescription(recipe.getTitle());

        txt_author.setText(recipe.getSourceName());
        txt_author.setContentDescription(recipe.getSourceName());

        textDiets = "";
        diets = recipe.getDiets();
        for (int i = 0; i < diets.length; i++) {
            textDiets = textDiets + diets[i] + ", ";
        }
        StringBuffer dietStringBuffer = new StringBuffer(textDiets);
        dietStringBuffer.deleteCharAt(dietStringBuffer.length() - 2);
        txt_diets.setText(dietStringBuffer);
        txt_diets.setContentDescription(dietStringBuffer);

        // txt_detail.setText(recipe.getSummary());

        textDetails = recipe.getSummary() + "<br><br>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txt_detail.setText(Html.fromHtml(textDetails, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txt_detail.setText(Html.fromHtml(textDetails));
        }

        ingredients = recipe.getExtendedIngredients();

        textIngredients = "";
        spokenInstructions = "";

        for (int i = 0; i < ingredients.length; i++) {
            textIngredients = textIngredients + ingredients[i].getOriginal() + "\n\n";
            spokenInstructions = textIngredients + ingredients[i].getOriginal() + ". ";
        }
        txt_ingredient.setText(textIngredients);


        textInstructions = recipe.getInstructions();
        textInstructions = textInstructions.replaceAll("\\.", ".<br><br>");
        textInstructions = textInstructions.replaceAll("<li>", "");

        //txt_content.setText(recipe.getInstructions());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txt_instruction.setText(Html.fromHtml(textInstructions, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txt_instruction.setText(Html.fromHtml(textInstructions));
        }

        Picasso.get().load(recipe.getImage()).into(img_recipe);
        img_recipe.setContentDescription(recipe.getTitle());


        txt_detail.setContentDescription(spokenDetails);
        txt_ingredient.setContentDescription(textIngredients);
        txt_instruction.setContentDescription(spokenInstructions);



        detailSpeakButton = findViewById(R.id.button_speak_details);
        ingredientSpeakButton = findViewById(R.id.button_speak_ingredients);
        instructionSpeakButton = findViewById(R.id.button_speak_instructions);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        detailSpeakButton.setEnabled(true);
                        ingredientSpeakButton.setEnabled(true);
                        instructionSpeakButton.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });


        detailSpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakDetails();
            }
        });

        ingredientSpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakIngredients();
            }
        });

        instructionSpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakInstructions();
            }
        });


    }


    private void speakDetails() {

        spokenDetails = recipe.getSummary();
        spokenDetails = spokenDetails.replaceAll("\\<[^>]*>", "");

        textToSpeech.speak(spokenDetails, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speakIngredients() {

        textToSpeech.speak(spokenInstructions, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speakInstructions() {

        spokenInstructions = recipe.getInstructions();
        spokenInstructions = spokenInstructions.replaceAll("\\<[^>]*>", "");

        textToSpeech.speak(spokenInstructions, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }





}