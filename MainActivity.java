package com.belajar.tugaspbday5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etItemCode, etItemAmount;
    RadioGroup rgMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etItemCode = findViewById(R.id.etItemCode);
        etItemAmount = findViewById(R.id.etItemAmount);
        rgMember = findViewById(R.id.rgMember);
        Button btnProses = findViewById(R.id.btnProses);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesTransaksi();
            }
        });
    }

    private void prosesTransaksi() {
        String name = etName.getText().toString().trim();
        String itemCode = etItemCode.getText().toString().trim();
        String itemAmountStr = etItemAmount.getText().toString().trim();

        if (name.isEmpty() || itemCode.isEmpty() || itemAmountStr.isEmpty()) {
            Toast.makeText(this, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        int itemAmount = Integer.parseInt(itemAmountStr);

        double hargaBarang = 0;
        String namaBarang = "";
        switch (itemCode) {
            case "SGS":
                namaBarang = "SAMSUNG GALAXY S ";
                hargaBarang = 12999999;
                break;
            case "IPX":
                namaBarang = "IPHONE X ";
                hargaBarang = 5725300;
                break;
            case "O17":
                namaBarang = "OPPO A17 ";
                hargaBarang = 2500999;
                break;
            default:
                Toast.makeText(MainActivity.this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
                return;
        }

        double totalHarga = hargaBarang * itemAmount;
        int selectedId = rgMember.getCheckedRadioButtonId();
        double discountMember = 0;
        String membership = null;
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            membership = radioButton.getText().toString();
            switch (membership) {
                case "Emas":
                    discountMember = totalHarga * 0.1;
                    break;
                case "Perak":
                    discountMember = totalHarga * 0.05;
                    break;
                case "Biasa":
                    discountMember = totalHarga * 0.02;
                    break;
                case "Gold":
                    discountMember = totalHarga * 0.1;
                    break;
                case "Silver":
                    discountMember = totalHarga * 0.05;
                    break;
                case "Normal":
                    discountMember = totalHarga * 0.02;
                    break;
                case "ذهب":
                    discountMember = totalHarga * 0.1;
                    break;
                case "فضة":
                    discountMember = totalHarga * 0.05;
                    break;
                case "طبيعي":
                    discountMember = totalHarga * 0.02;
                    break;
            }
        }

        // Diskon tambahan jika total transaksi di atas 10 juta
        if (totalHarga > 10000000) {
            totalHarga -= 100000;
        }

        double discountHarga = (hargaBarang * itemAmount) - totalHarga;
        double jumlahBayar = totalHarga - discountMember;

        Intent intent = new Intent(this, HasilActivity.class);
        intent.putExtra("Name", name);
        intent.putExtra("Membership", membership);
        intent.putExtra("ItemCode", itemCode);
        intent.putExtra("NamaBarang", namaBarang);
        intent.putExtra("HargaBarang", hargaBarang);
        intent.putExtra("TotalHarga", totalHarga);
        intent.putExtra("DiscountHarga", discountHarga);
        intent.putExtra("DiscountMember", discountMember);
        intent.putExtra("JumlahBayar", jumlahBayar);
        startActivity(intent);
    }

    private void deleteInput() {
        etName.getText().clear();
        etItemCode.getText().clear();
        etItemAmount.getText().clear();
        rgMember.clearCheck();
    }
}
