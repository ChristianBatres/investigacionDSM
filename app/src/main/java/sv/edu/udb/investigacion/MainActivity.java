package sv.edu.udb.investigacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private TextView etText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicializando tts
        tts = new TextToSpeech(this,onInitListener);
        //capturando texto
        etText = (TextView) findViewById(R.id.txttexto);
    }
    @Override
    protected void onDestroy() {
        //se detiene tss
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    //funcion para que el texto se reproduzca
    public void speak(View v){
        String text = etText.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH, null,text);
        Log.d("TTS", "Hablando");
    }
    TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS){
                //definiendo idioma de voz
                Locale Espaniol = new Locale("spa", "SV");
                int result = tts.setLanguage(Espaniol);

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.d("TTS", "Idioma no soportado");
                }
                else{
                    Log.d("TTS", "Text to Speach ");
                }
            }
            else{
                Log.d("TTS","Error en Text to Speach");
            }
        }
    };
}