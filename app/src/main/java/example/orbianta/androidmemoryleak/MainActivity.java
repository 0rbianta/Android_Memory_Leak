package example.orbianta.androidmemoryleak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native");
    }

    private native void leak_memory();

    Handler timer = new Handler();
    EditText log_view;
    TextView title;
    String free_memory;
    Button run_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log_view=findViewById(R.id.log_view);
        run_btn=findViewById(R.id.run_btn);
        title=findViewById(R.id.title);



    }


    public void run_clicked(View v){
        free_memory = String.valueOf(Runtime.getRuntime().freeMemory()/99);
        title.setText("Leaking memory... Please wait.");
        run_btn.setVisibility(View.GONE);
        log_view.append("Current free memory: "+free_memory+"\nRunning memory leak payload. Device will be crash soon.\n");

        for(int i=0;i<100;i++)  timer.postDelayed(new Runnable() {@Override public void run() {
            free_memory = String.valueOf(Runtime.getRuntime().freeMemory()/99);
            log_view.append(free_memory+"\n"); //yea not works good :(
        }},500);

        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                leak_memory();
            }
        },3000);

    }



}