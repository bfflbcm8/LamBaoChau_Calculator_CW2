package com.example.LamBaoChauCalculatorCW2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvNumber,tvResult;
    MaterialButton buttonC;
    MaterialButton btDivision,btMultiply,btSubtract,btSum, btResult;
    MaterialButton btnumber0,btnumber1,btnumber2,btnumber3,btnumber4,btnumber5,
            btnumber6,btnumber7,btnumber8,btnumber9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNumber = findViewById(R.id.textview_numbers);
        tvResult =findViewById(R.id.textview_Result);

        Taskbt(buttonC,R.id.bt_C);
        Taskbt(btDivision,R.id.bt_Division);
        Taskbt(btMultiply,R.id.bt_Multiply);
        Taskbt(btSubtract,R.id.bt_Subtract);
        Taskbt(btSum,R.id.bt_Sum);
        Taskbt(btResult,R.id.bt_Result);
        Taskbt(btnumber0,R.id.bt_0);
        Taskbt(btnumber1,R.id.bt_1);
        Taskbt(btnumber2,R.id.bt_2);
        Taskbt(btnumber3,R.id.bt_3);
        Taskbt(btnumber4,R.id.bt_4);
        Taskbt(btnumber5,R.id.bt_5);
        Taskbt(btnumber6,R.id.bt_6);
        Taskbt(btnumber7,R.id.bt_7);
        Taskbt(btnumber8,R.id.bt_8);
        Taskbt(btnumber9,R.id.bt_9);

    }
    void Taskbt(MaterialButton tbtn,int id){
        tbtn = findViewById(id);
        tbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String clickbuttontextview = button.getText().toString();
        String textconnecttext = tvNumber.getText().toString();
        if(clickbuttontextview.equals("C")){
            tvNumber.setText("");
            tvResult.setText("0");
            return;
        }
        if(clickbuttontextview.equals("=")){
            tvNumber.setText(tvResult.getText());
            return;
        }

        textconnecttext = textconnecttext+clickbuttontextview;
        tvNumber.setText(textconnecttext);
        String finallyy = getResult(textconnecttext);
        if(!finallyy.equals("Error")){
            tvResult.setText(finallyy);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finallyy = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finallyy.endsWith(".0")){
                finallyy =finallyy.replace(".0","");
            }
            return finallyy;
        }catch (Exception e){
            return "Error";
        }
    }
}