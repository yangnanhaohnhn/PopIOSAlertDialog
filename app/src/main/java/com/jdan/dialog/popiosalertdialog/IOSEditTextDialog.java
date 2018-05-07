package com.jdan.dialog.popiosalertdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *  弹出文本框
 */
public class IOSEditTextDialog {

    private final Context mContext;
    private final Display display;
    private Dialog dialog;


    private TextView titleTv;
    private EditText msgEt;
    private Button confirmBtn;
    private Button cancelBtn;

    public IOSEditTextDialog(Context context) {
        this.mContext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
    }

    public IOSEditTextDialog builder() {
        //获取 Dialog 布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_edittext_layout, null);

        LinearLayout promptLl = view.findViewById(R.id.prompt_ll);
        titleTv = view.findViewById(R.id.title_tv);
        msgEt = view.findViewById(R.id.msg_et);
        confirmBtn = view.findViewById(R.id.confirm_btn);
        cancelBtn = view.findViewById(R.id.cancel_btn);
        dialog = new Dialog(mContext, R.style.IOSDialogStyle);
        dialog.setContentView(view);

        //调整dialog背景大小
        promptLl.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public IOSEditTextDialog setTitleColor(int color){
        titleTv.setTextColor(color);
        return this;
    }
    public IOSEditTextDialog setTitleSize(int size){
        titleTv.setTextSize(size);
        return this;
    }

    public IOSEditTextDialog setTitle(String title) {
        if ("".equals(title)) {
            titleTv.setText(R.string.title);//titile
        } else {
            titleTv.setText(title);
        }
        return this;
    }

    public IOSEditTextDialog setTitle(int title) {
        return setTitle(mContext.getString(title));
    }

    public IOSEditTextDialog setEditTextHint(String hintStr){
        if ("".equals(msgEt)) {
            msgEt.setHint(R.string.please_input_content);//titile
        } else {
            msgEt.setHint(hintStr);
        }
        return this;
    }

    public IOSEditTextDialog setEditText(String text){
        if ("".equals(msgEt)) {
            msgEt.setText(R.string.content);
        } else {
            msgEt.setText(text);
        }
        return this;
    }

    public IOSEditTextDialog setEditText(int text) {
        return setEditText(mContext.getString(text));
    }

    public IOSEditTextDialog setEditTextHint(int hintStr) {
        return setEditTextHint(mContext.getString(hintStr));
    }

    public IOSEditTextDialog setConfirmBtn(String text,
                                           final OnClickConfirmListener listener) {
        if ("".equals(text)) {
            confirmBtn.setText(R.string.ok);//queding
        } else {
            confirmBtn.setText(text);
        }
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgStr = msgEt.getText().toString().trim();
                listener.onClickConfirm(msgStr);
                dialog.dismiss();
            }
        });
        return this;
    }

    public IOSEditTextDialog setCancelBtn(String text,
                                          final View.OnClickListener listener) {
        if ("".equals(text)) {
            cancelBtn.setText(R.string.cancel);
        } else {
            cancelBtn.setText(text);
        }
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }
    public IOSEditTextDialog setConfirmBtn(int text,
                                           final OnClickConfirmListener listener) {
        return setConfirmBtn(mContext.getString(text),listener);
    }

    public IOSEditTextDialog setCancelBtn(int text,
                                          final View.OnClickListener listener) {
        return setCancelBtn(mContext.getString(text),listener);
    }


    public void show() {
        dialog.show();
    }

    public IOSEditTextDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public IOSEditTextDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }
}
