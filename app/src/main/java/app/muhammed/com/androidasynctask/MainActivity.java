package app.muhammed.com.androidasynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartButton;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mStartButton = findViewById(R.id.startThread);
        mResultTextView = findViewById(R.id.resultTextView);


        mStartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MyTask task = new MyTask();
        task.execute("Thread starting...");

    }


    class MyTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        String result = "";

        @Override
        protected String doInBackground(String... strings) {

            onProgressUpdate(strings[0]);

            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return result = "execution completed";
        }

        @Override
        protected void onPreExecute() {

            progressDialog.setMessage("Loading...!");
            progressDialog.show();

            result = "Thread on pre execute";

            mResultTextView.setText(result);


            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mResultTextView.setText(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {

            progressDialog.dismiss();
            mResultTextView.setText(s);
            super.onPostExecute(s);
        }
    }
}
