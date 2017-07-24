package com.sdwfqin.microtext.ui.about;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdwfqin.microtext.R;
import com.sdwfqin.microtext.model.bean.VersionBean;
import com.sdwfqin.microtext.widget.itemdecoration.DotItemDecoration;
import com.sdwfqin.microtext.widget.itemdecoration.SpanIndexListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutVersionActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_return_text)
    ImageView toolbarReturnText;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.version_recycler)
    RecyclerView versionRecycler;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private DotItemDecoration dotItemDecoration;
    private List<VersionBean> beanList;
    private AboutVersionAdapter aboutVersionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_version);
        ButterKnife.bind(this);

        toolbarReturnText.setVisibility(View.VISIBLE);
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitle.setText("版本信息");

        String[] code = getResources().getStringArray(R.array.version_code);
        String[] des = getResources().getStringArray(R.array.version_des);

        beanList = new ArrayList<VersionBean>();
        for (int i = code.length - 1; i >= 0; i--) {
            VersionBean versionBean = new VersionBean();
            versionBean.setCode(code[i]);
            versionBean.setDes(des[i]);
            beanList.add(versionBean);
        }

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        versionRecycler.setLayoutManager(staggeredGridLayoutManager);
        dotItemDecoration = new DotItemDecoration
                .Builder(this)
                .setOrientation(DotItemDecoration.VERTICAL)
                .setItemStyle(DotItemDecoration.STYLE_DRAW)
                .setTopDistance(20)
                .setItemInterVal(10)
                .setItemPaddingLeft(20)
                .setItemPaddingRight(20)
                .setDotColor(0xff03A9F4)
                .setDotRadius(3)
                .setLineColor(Color.BLACK)
                .setLineWidth(1)
                .setEndText("END")
                .setTextColor(Color.BLACK)
                .setTextSize(12)
                .setDotPaddingText(2)
                .setBottomDistance(40)
                .create();
        dotItemDecoration.setSpanIndexListener(new SpanIndexListener() {
            @Override
            public void onSpanIndexChange(View view, int spanIndex) {
                view.setBackgroundResource(spanIndex == 0 ? R.drawable.pop_left : R.drawable.pop_right);
            }
        });
        versionRecycler.addItemDecoration(dotItemDecoration);

        aboutVersionAdapter = new AboutVersionAdapter(R.layout.items_version, beanList);
        versionRecycler.setAdapter(aboutVersionAdapter);
    }

    @OnClick(R.id.toolbar_return_text)
    public void onViewClicked() {
        finish();
    }
}
