package com.myp.cinema.ui.membercard;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.accountbalance.accountbalance;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;


/**会员卡列表
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MemberCardActivity extends MVPBaseActivity<MemberCardContract.View, MemberCardPresenter> implements
        MemberCardContract.View , AdapterView.OnItemClickListener, View.OnClickListener{

    List<CardBO> data =  new ArrayList<>();
    private CommonAdapter<CardBO> adapter;
    private ListView list;
    private Button addCard;
    private Button addCardButton;
    private RelativeLayout nocardlayout;
    private ScrollView sc;
    private RelativeLayout rlBindCard;
    private RelativeLayout rlOpenCard;
    private TextView cardRules2;
    private TextView cardRules;//开卡规则

    @Override
    protected int getLayout() {
        return R.layout.fra_card_list1;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("我的会员卡");
        list = (ListView)findViewById(R.id.lv);
        addCard = (Button)findViewById(R.id.add_card);
        addCardButton = (Button)findViewById(R.id.add_card_button);
        nocardlayout = (RelativeLayout) findViewById(R.id.no_card_layout);
        sc = (ScrollView)findViewById(R.id.sc);
        rlBindCard = findViewById(R.id.rlBindCard);
        rlOpenCard = findViewById(R.id.rlOpenCard);
        cardRules = findViewById(R.id.cardRules);
        cardRules2 = findViewById(R.id.cardRules2);
        addCard.setOnClickListener(this);
        addCardButton.setOnClickListener(this);
        list.setOnItemClickListener(this);
        rlBindCard.setOnClickListener(this);
        rlOpenCard.setOnClickListener(this);
        mPresenter.loadCardUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getOpenMoney();
    }

    private void adapter() {
        if (adapter != null){
            adapter.notifyDataSetChanged();
            return;
        }
        adapter = new CommonAdapter<CardBO>(this, R.layout.new_card_item_layout, data) {
            @Override
            protected void convert(ViewHolder helper, CardBO item, int position) {
//                helper.getView(R.id.card_check).setVisibility(View.GONE);
                BigDecimal bd = new BigDecimal(item.getBalance());
                String str = bd.toPlainString();
                helper.setText(R.id.cardPrice,str);
                helper.setText(R.id.card_num, String.format("NO.%s",item.getCardNumber()));
                if (item.getCardLevel().contains("至尊卡")){
                    helper.getView(R.id.linear_layout).setVisibility(View.VISIBLE);
                    helper.getView(R.id.gotoPay).setVisibility(View.VISIBLE);
                    helper.getView(R.id.rlCard).setBackground(getResources().getDrawable(R.drawable.kapian_perfect));
                }else if (item.getCardLevel().contains("战疫卡")){
                    helper.getView(R.id.linear_layout).setVisibility(View.VISIBLE);
                    helper.getView(R.id.gotoPay).setVisibility(View.VISIBLE);
                    helper.getView(R.id.rlCard).setBackground(getResources().getDrawable(R.drawable.war_card));
                }else if (item.getCardLevel().contains("集团99看过瘾")){
                    helper.getView(R.id.linear_layout).setVisibility(View.GONE);
                    helper.getView(R.id.gotoPay).setVisibility(View.GONE);
                    helper.getView(R.id.rlCard).setBackground(getResources().getDrawable(R.drawable.jiu_card));
                }else {
                    helper.getView(R.id.linear_layout).setVisibility(View.VISIBLE);
                    helper.getView(R.id.gotoPay).setVisibility(View.VISIBLE);
                    helper.getView(R.id.rlCard).setBackground(getResources().getDrawable(R.drawable.kapian));
                }
            }
        };
        list.setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (data.get(position).getCardLevel().contains("集团99看过瘾")){
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("cardBO", data.get(position));
        Intent intent = new Intent(MemberCardActivity.this, accountbalance.class);
        intent.putExtras(bundle);
        BigDecimal bd = new BigDecimal(data.get(position).getBalance());
        String code = bd.toPlainString();
        intent.putExtra("cardPrice", code);
        intent.putExtra("getCardNumber", data.get(position).getCardNumber());
        intent.putExtra("cardLevel",data.get(position).getCardLevel());
        startActivityForResult(intent, 1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 1:
            case 2:
                mPresenter.loadCardUser();
                break;
        }
    }
    @Override
    public void getCardList(List<CardBO> cardBOs) {
        if (cardBOs != null && cardBOs.size() != 0) {
            nocardlayout.setVisibility(View.GONE);
            sc.setVisibility(View.VISIBLE);
            data.clear();
            data.addAll(cardBOs);
            adapter();
        } else {
            sc.setVisibility(View.GONE);
            nocardlayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBindCard:
                Intent intent = new Intent(MemberCardActivity.this, AddCardActiivty.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.rlOpenCard:
                Intent intent2 = new Intent(MemberCardActivity.this, BindCard.class);
                startActivityForResult(intent2, 2);
                break;
        }
    }

    /**
     * 开卡说明
     */
    private void getOpenMoney(){
        HttpInterfaceIml.getCardNotice(MyApplication.cinemaBo.getCinemaId()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                stopProgress();
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShortToast(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                stopProgress();
                if (s != null && !s.equals("")){
                    cardRules.setVisibility(View.VISIBLE);
                    cardRules2.setVisibility(View.VISIBLE);
                    cardRules.setText(s);
                    cardRules2.setText(s);
                }else {
                    cardRules.setVisibility(View.GONE);
                    cardRules2.setVisibility(View.GONE);
                }
            }
        });
    }

}
