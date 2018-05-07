package com.jdan.dialog.popiosalertdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IOSSheetDialog {
    private final Context mContext;
    private final Display display;
    private Dialog dialog;

    private List<SheetItem> sheetItemList;
    private boolean isHideTitle = false;
    private TextView mSheetTitleTv;
    private ScrollView mSheetContentSv;
    private LinearLayout mSheetContentLl;

    public IOSSheetDialog(Context context) {
        this.mContext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
    }

    public IOSSheetDialog builder() {
        //获取 Dialog 布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.sheet_dialog_layout, null);

        //设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        //获取自定义Dialog布局中的控件
        mSheetContentSv = view.findViewById(R.id.sheet_dialog_content_sv);
        mSheetContentLl = view.findViewById(R.id.sheet_dialog_content_ll);
        mSheetTitleTv = view.findViewById(R.id.sheet_dialog_title_tv);
        TextView mSheetCancelTv = view.findViewById(R.id.sheet_dialog_cancel_tv);

        mSheetCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(mContext, R.style.IOSSheetDialogStyle);
        dialog.setContentView(view);
        //设置位置
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams wm = window.getAttributes();
        wm.x = 0;
        wm.y = 0;
        window.setAttributes(wm);

        return this;
    }

    public IOSSheetDialog setTitle(String title) {
        isHideTitle = true;
        if ("".equals(title)) {
            mSheetTitleTv.setText(R.string.please_choose_model);
        } else {
            mSheetTitleTv.setText(title);
        }
        return this;
    }

    public IOSSheetDialog setTitle(int title) {
        return setTitle(mContext.getString(title));
    }

    public IOSSheetDialog setTitleColor(int color) {
        isHideTitle = true;
        mSheetTitleTv.setTextColor(color);
        return this;
    }

    public IOSSheetDialog setTitleSize(int size) {
        isHideTitle = true;
        mSheetTitleTv.setTextSize(size);
        return this;
    }

    /**
     * @param strItem  条目名称
     * @param listener
     * @return
     */
    public IOSSheetDialog addSheetItem(String strItem, OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem, listener));
        return this;
    }

    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色
     * @param listener
     * @return
     */
    public IOSSheetDialog addSheetItem(String strItem, SheetItemColor color,
                                       OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }

    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色
     * @param listener
     * @return
     */
    public IOSSheetDialog addSheetItem(String strItem, SheetItemColor color, int size,
                                       OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem, color, size, listener));
        return this;
    }

    /**
     * @param strItem 条目名称
     * @param color   条目字体颜色
     * @return
     */
    public IOSSheetDialog addSheetItem(String strItem, SheetItemColor color, int size, int gravity,
                                       OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem, color, size, gravity, listener));
        return this;
    }

    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public IOSSheetDialog addSheetItem(int strItem, OnSheetItemClickListener listener) {
        return addSheetItem(mContext.getString(strItem), listener);
    }

    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public IOSSheetDialog addSheetItem(int strItem, SheetItemColor color,
                                       OnSheetItemClickListener listener) {
        return addSheetItem(mContext.getString(strItem), color, listener);
    }

    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public IOSSheetDialog addSheetItem(int strItem, SheetItemColor color, int size,
                                       OnSheetItemClickListener listener) {
        return addSheetItem(mContext.getString(strItem), color, size, listener);
    }

    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public IOSSheetDialog addSheetItem(int strItem, SheetItemColor color, int size, int gravity,
                                       OnSheetItemClickListener listener) {
        return addSheetItem(mContext.getString(strItem), color, size, gravity, listener);
    }

    public void show() {
        setSheetItems();
        dialog.show();
    }

    /**
     * 设置条目布局
     */
    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }

        int size = sheetItemList.size();

        if (size >= 7) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mSheetContentSv.getLayoutParams();
            layoutParams.height = display.getHeight() / 2;
            mSheetContentSv.setLayoutParams(layoutParams);
        }
        mSheetTitleTv.setVisibility(isHideTitle ? View.GONE : View.VISIBLE);

        // 循环添加条目
        TextView textView = null;
        for (int i = 0; i < size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i);
            final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem.itemClickListener;

            textView = new TextView(mContext);
            textView.setText(sheetItem.name);
            textView.setTextSize(sheetItem.size);
            textView.setGravity(sheetItem.gravity);
            textView.setTextColor(Color.parseColor(sheetItem.color.getName()));

            // 背景图片
            if (size == 1) {
                if (!isHideTitle) {
                    textView.setBackgroundResource(R.drawable.sheet_bottom_selector);
                } else {
                    textView.setBackgroundResource(R.drawable.sheet_single_selector);
                }
            } else {
                if (!isHideTitle) {
                    if (i >= 0 && i < size - 1) {
                        textView.setBackgroundResource(R.drawable.sheet_middle_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.sheet_bottom_selector);
                    }
                } else {
                    if (i == 0) {
                        textView.setBackgroundResource(R.drawable.sheet_top_selector);
                    } else if (i < size - 1) {
                        textView.setBackgroundResource(R.drawable.sheet_middle_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.sheet_bottom_selector);
                    }
                }
            }

            // 高度
            float scale = mContext.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, height));

            // 点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickItem(index);
                    dialog.dismiss();
                }
            });

            mSheetContentLl.addView(textView);
        }
    }

    public IOSSheetDialog hideTitle() {
        isHideTitle = true;
        return this;
    }

    public IOSSheetDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public IOSSheetDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    private class SheetItem {
        String name;
        OnSheetItemClickListener itemClickListener;
        SheetItemColor color = SheetItemColor.Blue;
        int size = 16;
        int gravity = Gravity.CENTER;

        public SheetItem(String name,
                         OnSheetItemClickListener itemClickListener) {
            this(name, SheetItemColor.Blue, itemClickListener);
        }

        public SheetItem(String name, SheetItemColor color,
                         OnSheetItemClickListener itemClickListener) {
            this(name, color, 16, itemClickListener);
        }

        public SheetItem(String name, SheetItemColor color, int size,
                         OnSheetItemClickListener itemClickListener) {
            this(name, color, size, Gravity.CENTER, itemClickListener);
        }

        public SheetItem(String name, SheetItemColor color, int size, int gravity,
                         OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.size = size;
            this.gravity = gravity;
            this.itemClickListener = itemClickListener;
        }
    }

    public enum SheetItemColor {
        Blue("#037BFF"), Red("#FD4A2E"), Grey("#666666"), Black("#000000");

        private String name;

        private SheetItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
