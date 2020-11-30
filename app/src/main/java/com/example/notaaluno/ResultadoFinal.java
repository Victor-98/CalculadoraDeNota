package com.example.notaaluno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ResultadoFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final2);

        Intent it = getIntent();
        // Certifica que intent pode ser recebido.
        if (it == null)
            return;

        Bundle params = it.getExtras();
        // Certifica que os parâmetros foram enviados corretamente.
        if (params == null)
            return;

        String nome = params.getString("nome");
        Double nota = params.getDouble("nota");
        Integer frequencia = params.getInt("frequencia");

        // Calcula os resultados
        String statusAprovacao = "";

        if (frequencia < 75) {
            statusAprovacao = "Reprovado por Falta";
        } else if (nota < 4) {
            statusAprovacao = "Reprovado por Nota";
        } else if (nota < 7) {
            statusAprovacao = "Final";
        } else {
            statusAprovacao = "Aprovado";
        }


        TextView textoResultadoDesc = findViewById(R.id.textoResultadoDesc);

        switch (statusAprovacao) {
            case "Reprovado por Falta":
                textoResultadoDesc.setText("Infelizmente, você foi reprovado por falta, " + nome + ".");
                break;

            case "Reprovado por Nota":
                textoResultadoDesc.setText("Infelizmente, você foi reprovado por nota, " + nome + ".");
                break;

            case "Final":
                textoResultadoDesc.setText("Você terá que realizar a prova final, " + nome + ".");
                break;

            case "Aprovado":
                textoResultadoDesc.setText("Parabens, você foi aprovado, " + nome + ".");
                break;
        }

        TextView textoResultadoNota = findViewById(R.id.textoResultadoNota);
        NumberFormat formatter = new DecimalFormat("#0.00");
        textoResultadoNota.setText("Nota : " + formatter.format(nota).toString());

        Log.i("minhaApp", "Mensagem : " + statusAprovacao);
    }
}