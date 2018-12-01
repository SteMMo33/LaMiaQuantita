package com.stemmo.lamiaquantita;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText tvCrudoPatty = null;
    EditText tvCrudoSte = null;
    Context context;
    int nOldCrudoPatty;
    int nOldCrudoSte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        final TextView tvRisultato = (TextView) findViewById(R.id.txtPesoCalcolato);
        final EditText tvCotto = (EditText) findViewById(R.id.edtPesoCotto);

        tvCrudoPatty = (EditText) findViewById(R.id.edtCrudoPatty);
        tvCrudoSte = (EditText) findViewById(R.id.edtCrudoSte);
        Button btn = (Button) findViewById(R.id.btnCalcola);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTmp = tvCrudoPatty.getText().toString();
                if (strTmp.isEmpty()){
                    Toast.makeText( context, "Campo vuoto", Toast.LENGTH_SHORT ).show();
                    tvCrudoPatty.requestFocus();
                    return;
                }
                int nCrudoPatty = Integer.parseInt(strTmp);

                strTmp = tvCrudoSte.getText().toString();
                if (strTmp.isEmpty()){
                    Toast.makeText( context, "Campo vuoto", Toast.LENGTH_SHORT ).show();
                    tvCrudoSte.requestFocus();
                    return;
                }
                int nCrudoSte = Integer.parseInt(strTmp);

                strTmp = tvCotto.getText().toString();
                if (strTmp.isEmpty()){
                    Toast.makeText( context, "Campo vuoto", Toast.LENGTH_SHORT ).show();
                    tvCotto.requestFocus();
                    return;
                }
                int nCotto = Integer.parseInt(strTmp);

                // CrudoPatty : CrudoSte = CottoPatty : CottoSte(Risultato)
                double a = 1.0f+(double)nCrudoSte/(double)nCrudoPatty;
                int nCottoPatty = (int) (nCotto/a);
                int nCottoSte = nCotto-nCottoPatty;
                tvRisultato.setText(String.format("Patty: %s - Ste: %s", Integer.toString(nCottoPatty), Integer.toString(nCottoSte)));

                Toast.makeText( context, "Brava dadina!", Toast.LENGTH_SHORT).show();
            }
        });

        // Gestione delle preferenze
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        nOldCrudoSte = sharedPref.getInt(getString(R.string.ValCrudoSte), 85);
        tvCrudoSte.setText(String.valueOf(nOldCrudoSte));

        nOldCrudoPatty = sharedPref.getInt(getString(R.string.ValCrudoPatty), 65);
        tvCrudoPatty.setText(String.valueOf(nOldCrudoPatty));

        tvCotto.requestFocus();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Controlla se le quantità di crudo sono cambiate dall'ultima lettura
        boolean bChanged = false;

        String strTmp = tvCrudoPatty.getText().toString();
        int nCrudoPatty = Integer.parseInt(strTmp);
        bChanged |= nCrudoPatty != nOldCrudoPatty;

        strTmp = tvCrudoSte.getText().toString();
        int nCrudoSte = Integer.parseInt(strTmp);
        bChanged |= nCrudoSte != nOldCrudoSte;

        // Se si, le salva per la prossima sessione
        if (bChanged){
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.ValCrudoPatty), nCrudoPatty);
            editor.putInt(getString(R.string.ValCrudoSte), nCrudoSte);
            editor.apply();
        }
    }

}
