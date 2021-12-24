package id.unud.ac.inventoriperpus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    int rate = 0;
    EditText edJudul, edPenulis;
    RadioButton rbFiksi, rbNonFiksi;
    CheckBox cbScience, cbFantasy, cbDrama, cbAction;
    public TextView Judul, Rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edJudul = (EditText) findViewById(R.id.ed_judul);
        edPenulis = (EditText) findViewById(R.id.ed_penulis);

        rbFiksi = (RadioButton) findViewById(R.id.rb_fiksi);
        rbNonFiksi = (RadioButton) findViewById(R.id.rb_nonfiksi);

        cbScience = (CheckBox) findViewById(R.id.cb_science);
        cbFantasy = (CheckBox) findViewById(R.id.cb_fantasy);
        cbDrama = (CheckBox) findViewById(R.id.cb_drama);
        cbAction = (CheckBox) findViewById(R.id.cb_action);

        Judul = findViewById(R.id.txt_judul);
        final TextView textRate = findViewById(R.id.txt_ptrate);
        SeekBar seekBar = findViewById(R.id.seekBar);

        //seekbar
        textRate.setText(seekBar.getProgress() + "/" + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textRate.setText(progress + "/" + seekBar.getMax());
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                rate = rate + (progressValue - progress);
                progress = progressValue;
                textRate.setText(rate + "/10");
                Rate = textRate;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    public void tambahData(View view) {

        String judul = edJudul.getText().toString();
        String nama = edPenulis.getText().toString();
        String kategori = "";
        String genre = "";
        String rate = Rate.getText().toString();

        //radio button
        if (rbFiksi.isChecked()) {
            kategori += " Fiksi";
        } else if (rbNonFiksi.isChecked()) {
            kategori += " Non Fiksi";
        } else {

        }

        //checkbox
        if (cbScience.isChecked()) {
            genre += " Science";
        }if (cbFantasy.isChecked()) {
            genre += " Fantasy";
        }if (cbDrama.isChecked()) {
            genre += " Drama";
        }if (cbAction.isChecked()) {
            genre += " Action";
        }

        //Validasi
        if(judul.equals("") || nama.equals("")){
            Toast.makeText(this, "Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Proses Berhasil");
            builder.setMessage("Judul Buku\t\t\t\t: "+ String.valueOf(judul) + "\n" +
                    "Nama Penulis\t: " + String.valueOf(nama)+ "\n" +
                    "Kategori Buku\t:" + String.valueOf(kategori) + "\n" +
                    "Genre\t\t\t\t\t\t\t\t\t:"  + String.valueOf(genre) + "\n" +
                    "Rating\t\t\t\t\t\t\t\t: " + String.valueOf(rate) + "\n").setPositiveButton("OK",
                    (dialog, which) ->{
                        dialog.dismiss();
                    });

            AlertDialog dialoghasil = builder.create();
            dialoghasil.show();
       }

    }

}