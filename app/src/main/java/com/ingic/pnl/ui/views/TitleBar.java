package com.ingic.pnl.ui.views;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ingic.pnl.R;

public class TitleBar extends RelativeLayout {

    private TextView txtTitle;
    private ImageView btnLeft;
    private ImageView btnRight;
    private LinearLayout iv_search;
    private LinearLayout llSearch;
    private AnyEditTextView edt_search;
    private AnyTextView btnClear;


    private View.OnClickListener menuButtonListener;
    private OnClickListener backButtonListener;

    private Context context;


    public TitleBar(Context context) {
        super(context);
        this.context = context;
        initLayout(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
        if (attrs != null)
            initAttrs(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout(context);
        if (attrs != null)
            initAttrs(context, attrs);
    }

    public void showSearchBar(TextWatcher textWatcher, OnClickListener search, String hint) {
        llSearch.setVisibility(VISIBLE);
        edt_search.addTextChangedListener(textWatcher);
        edt_search.setHint(hint);
        iv_search.setOnClickListener(search);
    }

    public AnyEditTextView getEditTextViewSearch(int resouceId) {

        AnyEditTextView edt_search = (AnyEditTextView) findViewById(resouceId);
        return edt_search;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
    }

    private void bindViews() {

        txtTitle = (TextView) this.findViewById(R.id.txt_subHead);
        btnClear = (AnyTextView) this.findViewById(R.id.btn_clear);
        btnRight = (ImageView) this.findViewById(R.id.btnRight);
        btnLeft = (ImageView) this.findViewById(R.id.btnLeft);
        llSearch = (LinearLayout) this.findViewById(R.id.llSearch);
        edt_search = (AnyEditTextView) findViewById(R.id.edt_search);
        iv_search = (LinearLayout) this.findViewById(R.id.iv_search);


    }

    private void initLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header_main, this);
        bindViews();
    }

    public void hideButtons() {
        txtTitle.setVisibility(View.GONE);
        btnLeft.setVisibility(View.GONE);
        btnRight.setVisibility(View.GONE);
        llSearch.setVisibility(View.GONE);
        btnClear.setVisibility(View.GONE);
    }

    public void showBackButton() {
        btnLeft.setVisibility(View.VISIBLE);
        btnLeft.setImageResource(R.drawable.bcakbtn);
        btnLeft.setOnClickListener(backButtonListener);

    }

    public void showMenuButton() {
        btnLeft.setVisibility(View.VISIBLE);
        btnLeft.setOnClickListener(menuButtonListener);
        btnLeft.setImageResource(R.drawable.dropdown);
    }
    public void showClearButton(OnClickListener listener) {
        btnClear.setVisibility(View.VISIBLE);
        btnClear.setOnClickListener(listener);
    }
    public void setSubHeading(String heading) {
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(heading);

    }

    public void showTitleBar() {
        this.setVisibility(View.VISIBLE);
    }

    public void hideTitleBar() {
        this.setVisibility(View.GONE);
    }

    public void setMenuButtonListener(View.OnClickListener listener) {
        menuButtonListener = listener;
    }

    public void setBackButtonListener(View.OnClickListener listener) {
        backButtonListener = listener;
    }


}
