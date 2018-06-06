package com.jdan.popiosdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 提示框
 */
public class IOSPromptDialog {

    private final Context mContext;
    private final Display display;
    private Dialog dialog;

    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showConfirmBtn = false;
    private boolean showCancelBtn = false;

    private TextView titleTv;
    private TextView msgTv;
    private Button confirmBtn;
    private Button cancelBtn;
    private View lineView;

    public IOSPromptDialog(Context context) {
        this.mContext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
    }

    public IOSPromptDialog builder() {
        //获取 Dialog 布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.prompt_dialog_layout, null);

        LinearLayout promptLl = view.findViewById(R.id.prompt_ll);
        titleTv = view.findViewById(R.id.prompt_title_tv);
        msgTv = view.findViewById(R.id.prompt_msg_tv);
        confirmBtn = view.findViewById(R.id.prompt_confirm_btn);
        cancelBtn = view.findViewById(R.id.prompt_cancel_btn);
        lineView = view.findViewById(R.id.line_img);
        dialog = new Dialog(mContext, R.style.IOSDialogStyle);
        dialog.setContentView(view);

        //调整dialog背景大小
        promptLl.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public IOSPromptDialog setTitleColor(int color){
        showTitle = true;
        titleTv.setTextColor(color);
        return this;
    }
    public IOSPromptDialog setTitleSize(int size){
        showTitle = true;
        titleTv.setTextSize(size);
        return this;
    }
    public IOSPromptDialog setMsgColor(int color){
        showMsg = true;
        msgTv.setTextColor(color);
        return this;
    }
    public IOSPromptDialog setMsgSize(int size){
        showMsg = true;
        msgTv.setTextSize(size);
        return this;
    }

    public IOSPromptDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            titleTv.setText(R.string.title);//titile
        } else {
            titleTv.setText(title);
        }
        return this;
    }

    public IOSPromptDialog setMsg(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            msgTv.setText(R.string.content);//content
        } else {
            msgTv.setText(msg);
        }
        return this;
    }
    public IOSPromptDialog setTitle(int title) {
        return setTitle(title);
    }

    public IOSPromptDialog setMsg(int msg) {
        return setMsg(msg);
    }
    public IOSPromptDialog setConfirmBtn(String text,
                                         final View.OnClickListener listener) {
        showConfirmBtn = true;
        if ("".equals(text)) {
            confirmBtn.setText(R.string.ok);//queding
        } else {
            confirmBtn.setText(text);
        }
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public IOSPromptDialog setCancelBtn(String text,
                                        final View.OnClickListener listener) {
        showCancelBtn = true;
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


    public void show() {
        setLayout();
        dialog.show();
    }

    private void setLayout() {
        show(titleTv);
        if (showMsg) {
            show(msgTv);
        } else {
            hide(msgTv);
            titleTv.setText(R.string.prompt);
        }


        show(confirmBtn);
        hide(lineView);
        if (!showConfirmBtn && !showCancelBtn) {
            confirmBtn.setText(R.string.ok);//queding
            hide(cancelBtn);
            confirmBtn.setBackgroundResource(R.drawable.dialog_single_selector);
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showConfirmBtn && showCancelBtn) {
            confirmBtn.setBackgroundResource(R.drawable.dialog_right_selector);
            hide(cancelBtn);
            cancelBtn.setBackgroundResource(R.drawable.dialog_left_selector);
            show(lineView);
        }

        if (showConfirmBtn && !showCancelBtn) {
            hide(cancelBtn);
            confirmBtn.setBackgroundResource(R.drawable.dialog_single_selector);
        }

        if (!showConfirmBtn && showCancelBtn) {
            hide(cancelBtn);
            cancelBtn.setBackgroundResource(R.drawable.dialog_single_selector);
        }

    }

    public IOSPromptDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public IOSPromptDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * 隐藏view
     *
     * @param view
     */
    private void hide(View view) {
        view.setVisibility(View.GONE);
    }

    /**
     * 显示view
     *
     * @param view
     */
    private void show(View view) {
        view.setVisibility(View.VISIBLE);
    }
}
