package com.belajar.tugaspbday5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import java.util.Locale;

public class HasilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        String name = getIntent().getStringExtra("Name");
        String membership = getIntent().getStringExtra("Membership");
        String itemCode = getIntent().getStringExtra("ItemCode");
        String namaBarang = getIntent().getStringExtra("NamaBarang");
        double hargaBarang = getIntent().getDoubleExtra("HargaBarang", 0);
        double totalHarga = getIntent().getDoubleExtra("TotalHarga", 0);
        double discountHarga = getIntent().getDoubleExtra("DiscountHarga", 0);
        double discountMember = getIntent().getDoubleExtra("DiscountMember", 0);
        double jumlahBayar = getIntent().getDoubleExtra("JumlahBayar", 0);

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        String hargaBarangStr = formatRupiah.format(hargaBarang);
        String totalHargaStr = formatRupiah.format(totalHarga);
        String discountHargaStr = formatRupiah.format(discountHarga);
        String discountMemberStr = formatRupiah.format(discountMember);
        String jumlahBayarStr = formatRupiah.format(jumlahBayar);

        TextView tvName = findViewById(R.id.tvName);
        tvName.setText(getString(R.string.welcome_message) + " " + name);

        TextView tvMemberType = findViewById(R.id.tvMemberType);
        tvMemberType.setText(getString(R.string.member_type) + " " + membership);

        TextView tvItemCode = findViewById(R.id.tvItemCode);
        tvItemCode.setText(getString(R.string.item_code) + " " + itemCode);

        TextView tvItemName = findViewById(R.id.tvItemName);
        tvItemName.setText(getString(R.string.item_name) + " " + namaBarang);

        TextView tvItemPrice = findViewById(R.id.tvItemPrice);
        tvItemPrice.setText(getString(R.string.item_price) + " " + hargaBarangStr);

        TextView tvTotalHarga = findViewById(R.id.tvTotalHarga);
        tvTotalHarga.setText(getString(R.string.total_price) + " " + totalHargaStr);

        TextView tvDiscount = findViewById(R.id.tvDiscount);
        tvDiscount.setText(getString(R.string.discount_price) + " " + discountHargaStr);

        TextView tvMemberDiscount = findViewById(R.id.tvMemberDiscount);
        tvMemberDiscount.setText(getString(R.string.member_discount) + " " + discountMemberStr);

        TextView tvJumlahBayar = findViewById(R.id.tvJumlahBayar);
        tvJumlahBayar.setText(getString(R.string.total_payment) + " " + jumlahBayarStr);

        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTransactionInfo(name, membership, itemCode, namaBarang, hargaBarangStr,
                        totalHargaStr, discountHargaStr, discountMemberStr, jumlahBayarStr);
            }
        });
    }

    private void shareTransactionInfo(String name, String membership, String itemCode,
                                      String namaBarang, String hargaBarang, String totalHarga,
                                      String discountHarga, String discountMember, String jumlahBayar) {
        String message = getString(R.string.transaction_detail) + "\n" +
                getString(R.string.name) + " " + name + "\n" +
                getString(R.string.member_type) + " " + membership + "\n" +
                getString(R.string.item_code) + " " + itemCode + "\n" +
                getString(R.string.item_name) + " " + namaBarang + "\n" +
                getString(R.string.item_price) + " " + hargaBarang + "\n" +
                getString(R.string.total_price) + " " + totalHarga + "\n" +
                getString(R.string.discount_price) + " " + discountHarga + "\n" +
                getString(R.string.member_discount) + " " + discountMember + "\n" +
                getString(R.string.total_payment) + " " + jumlahBayar;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, getString(R.string.share_via)));
    }
}
