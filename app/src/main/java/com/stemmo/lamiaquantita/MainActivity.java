package com.stemmo.lamiaquantita;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvRisultato = (TextView) findViewById(R.id.txtPesoCalcolato);
        final EditText tvCrudoPatty = (EditText) findViewById(R.id.edtCrudoPatty);
        final EditText tvCrudoSte = (EditText) findViewById(R.id.edtCrudoSte);
        final EditText tvCotto = (EditText) findViewById(R.id.edtPesoCotto);
        Button btn = (Button) findViewById(R.id.btnCalcola);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTmp = tvCrudoPatty.getText().toString();
                int nCrudoPatty = Integer.parseInt(strTmp);

                strTmp = tvCrudoSte.getText().toString();
                int nCrudoSte = Integer.parseInt(strTmp);

                strTmp = tvCotto.getText().toString();
                int nCotto = Integer.parseInt(strTmp);

                // CrudoPatty : CrudoSte = CottoPatty : CottoSte(Risultato)
                double a = 1.0f+(double)nCrudoSte/(double)nCrudoPatty;
                int nCottoPatty = (int) (nCotto/a);
                int nCottoSte = nCotto-nCottoPatty;
                tvRisultato.setText(String.format("Patty: %s\nSte: %s", Integer.toString(nCottoPatty), Integer.toString(nCottoSte)));
            }
        });
    }
}
