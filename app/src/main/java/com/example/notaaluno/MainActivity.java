package com.example.notaaluno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCalcularSituacao(View view) {
        Intent intent = new Intent(this, ResultadoFinal.class);

        // Inserir dados na intent
        EditText editNome = findViewById(R.id.editNome);
        String nome = editNome.getText().toString();

        EditText editFrequencia = findViewById(R.id.editFrequencia);
        String frequencia = editFrequencia.getText().toString();

        EditText editNota1 = findViewById(R.id.editNota1);
        String nota1 = editNota1.getText().toString();

        EditText editNota2 = findViewById(R.id.editNota2);
        String nota2 = editNota2.getText().toString();

        // Verifica se algum dos valores é vazio.
        if (nome.isEmpty() || frequencia.isEmpty() || nota1.isEmpty() || nota2.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Você deve preencher todos so campos.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        // Efetuar verificação de número
        if ( !isNumeric(frequencia) || !isNumeric(nota1) || !isNumeric(nota2) ) {
            Toast toast = Toast.makeText(getApplicationContext(), "Apenas números são permitidos nesses campos.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Integer intFrequencia = Integer.parseInt(frequencia);
        Double dblNota1 = Double.parseDouble(nota1);
        Double dblNota2 = Double.parseDouble(nota2);

        // Verificar intervalos
        if (  (intFrequencia > 100 || intFrequencia < 0) || (dblNota1 > 10 || dblNota1 < 0) || (dblNota2 > 10 || dblNota2 < 0)  ) {
            Toast toast = Toast.makeText(getApplicationContext(), "Você deve preencher números dentro do intervalo apropriado.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Double media = (dblNota1+dblNota2)/2;




        // Transfere parâmetros
        Intent it = new Intent(this, ResultadoFinal.class);
        Bundle params = new Bundle();
        params.putString("nome", nome);
        params.putDouble("nota", media);
        params.putInt("frequencia", intFrequencia);
        it.putExtras(params);
        startActivity(it);
    }
}