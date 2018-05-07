package com.jdan.dialog.popiosalertdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button popDialogBtn = findViewById(R.id.pop_dialog_btn);
        popDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                show();
                show2();
            }
        });
    }

    public void show(){
        new IOSPromptDialog(this).builder()
                .setTitle("nihao").setTitleSize(12).setTitleColor(R.color.color_f7602b)
                .setMsg("msg").setMsgSize(14)
                .setConfirmBtn("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "dianjiele ", Toast.LENGTH_SHORT).show();
                    }
                })

                .show();
    }

    public void show2(){
        IOSSheetDialog dialog = new IOSSheetDialog(this).builder();
        dialog.hideTitle();
        dialog.addSheetItem("nihao", new OnSheetItemClickListener() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(MainActivity.this, "aa"+position, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.addSheetItem("nihao1", IOSSheetDialog.SheetItemColor.Blue, new OnSheetItemClickListener() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(MainActivity.this, "aa"+position, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.addSheetItem("nihao3", IOSSheetDialog.SheetItemColor.Blue, new OnSheetItemClickListener() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(MainActivity.this, "aa"+position, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.addSheetItem("nihao2", IOSSheetDialog.SheetItemColor.Blue, new OnSheetItemClickListener() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(MainActivity.this, "aa"+position, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.addSheetItem("nihao", IOSSheetDialog.SheetItemColor.Blue, new OnSheetItemClickListener() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(MainActivity.this, "aa"+position, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
