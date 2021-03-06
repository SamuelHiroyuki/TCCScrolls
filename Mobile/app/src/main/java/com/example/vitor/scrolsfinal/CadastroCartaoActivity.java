package com.example.vitor.scrolsfinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitor.scrolsfinal.Classes.DAO;
import com.example.vitor.scrolsfinal.Classes.User;
import com.example.vitor.scrolsfinal.Database.DatabaseHelper;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.vitor.scrolsfinal.MainActivity.PREF_NAME;

public class CadastroCartaoActivity extends AppCompatActivity {
    EditText txtTitular,txtNumber, txtDate;
    DatabaseHelper helper;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        id = preferences.getInt("IdLoggedUser", 1);
        setContentView(R.layout.activity_cadastro_cartao);
        helper = new DatabaseHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        txtTitular = (EditText) findViewById(R.id.NomeCartãoForm);
        txtNumber = (EditText) findViewById(R.id.NumeroCartãoForm);
        txtDate = (EditText)findViewById(R.id.editValidade);
        txtNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtDate.setInputType(InputType.TYPE_CLASS_NUMBER);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN");
        MaskTextWatcher mkf = new MaskTextWatcher(txtDate, smf);
        txtDate.addTextChangedListener(mkf);

        smf = new SimpleMaskFormatter("NNNN NNNN NNNN NNNN");
        mkf = new MaskTextWatcher(txtNumber, smf);
        txtNumber.addTextChangedListener(mkf);
    }
    public void CadastrarCardClick (View v){
        tryCad();
    }
    public void tryCad() {
        String titular = txtTitular.getText().toString();
        String numeroCard = txtNumber.getText().toString();
        String validade = txtDate.getText().toString();



        txtTitular.setError(null);

        txtNumber.setError(null);
        txtDate.setError(null);


        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(titular)) {
            txtTitular.setError("Você não preencheu o nome do titular!");
            cancel = true;
            focusView = txtTitular;
        } else if (!isTitularValid(titular)) {
            txtTitular.setError("Nome do titular inválido");
            cancel = true;
            focusView = txtTitular;
        }
        if (TextUtils.isEmpty(numeroCard)) {
            txtNumber.setError("O campo é obrigatório");
            cancel = true;
            focusView = txtNumber;
        } else if (isNumberCardValid(numeroCard) == false) {
            txtNumber.setError("O número do cartão não é válido");
            cancel = true;
            focusView = txtNumber;
        }



        if (cancel) {
            //Código caso algum campo esteja inválido
            focusView.requestFocus();
        } else {
            cadCartao();
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        }
    }

    public void cadCartao() {


        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NomeCard", String.valueOf(txtTitular.getText()));
        values.put("NumeroCard", String.valueOf(txtNumber.getText()));
        values.put("ValidadeCard", String.valueOf(txtDate.getText()));

        long res = db.insert("Cartao", null, values);
        if (res != -1) {
            DAO dao = new DAO(getApplicationContext());
            int id = dao.RetornaCardId(txtTitular.getText().toString());
            values = new ContentValues();
            values.put("Id_Card", id);
            values.put("_IdUsuario", id); // pegar id do usuario
            Long resp =db.insert("UserCard", null, values);
            if(resp != -1){
                Toast.makeText(this, "Cartão cadastrado com sucesso", LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Um erro ocorreu!", LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Um erro ocorreu!", LENGTH_SHORT).show();
        }




    }

    public boolean isTitularValid(String titular) {
        return titular.length() > 0;
    }

    public boolean isNumberCardValid(String str) {
        return true;
      /*  int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            return true;
        } else {
            return false;
        }*/
    }

    /*public boolean isDateExpirityValid(String str) {

        DateFormat df = new SimpleDateFormat("MM/yy");
        Date dateobj = new Date();
        df.format(dateobj.getDate());
        Date date = Date.parse(str);
        if (dateobj.before(date)){
             return false;
         }


    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
