package com.myp.cinema.ui.accountbalance;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.BaseBO;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.LunBoAndBO;
import com.myp.cinema.entity.OpenCardBO;
import com.myp.cinema.ui.detailed.detailed;
import com.myp.cinema.ui.personorder.PersonOrderActivity;
import com.myp.cinema.util.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscriber;

/**会员卡余额页面
 * Created by Administrator on 2018/1/24.
 */

public class accountbalance extends BaseActivity {

    @Bind(R.id.submit)
    Button submit;
    @Bind(R.id.yu)
    TextView yu;
    @Bind(R.id.rlCard)
    RelativeLayout rlCard;
    @Bind(R.id.card_jifen)
    TextView card_jifen;/* 积分 */
    @Bind(R.id.unbindCard)
    TextView unbindCard;
    @Bind(R.id.cardRules)
    TextView cardRules;//开卡规则

    private String cardcode;
    private String value;
    private TextView card;
    private String cardLevel;
    private CardBO cardBO;

    @Override
    protected int getLayout() {
        return R.layout.accountbalancetactivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("会员卡");
        card = (TextView)findViewById(R.id.card_num);
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            cardBO = (CardBO) getIntent().getExtras().getSerializable("cardBO");
            value = intent.getStringExtra("cardPrice");
            cardcode = intent.getStringExtra("getCardNumber");
            cardLevel = intent.getStringExtra("cardLevel");
            yu.setText(value);
            TextPaint paint = yu.getPaint();
            paint.setFakeBoldText(true);
            card.setText("NO." +cardcode);
            card_jifen.setText(cardBO.getAvailableJifen());
            if (cardLevel.contains("至尊卡")){
                rlCard.setBackground(getResources().getDrawable(R.drawable.kapian_perfect));
            }else if (cardLevel.contains("战疫卡")){
                rlCard.setBackground(getResources().getDrawable(R.drawable.war_card));
            }else if (cardLevel.contains("集团99看过瘾")){
                rlCard.setBackground(getResources().getDrawable(R.drawable.jiu_card));
            }else {
                rlCard.setBackground(getResources().getDrawable(R.drawable.kapian));
            }
        }
        getOpenMoney();
    }


    @OnClick(R.id.submit)
    public void onViewClicked() {
        Intent rechatge = new Intent(accountbalance.this, com.myp.cinema.ui.rechatge.rechatge.class);
        rechatge.putExtra("cardPrice", value);
        rechatge.putExtra("cardcode",  cardcode);
        if (cardLevel.contains("至尊卡")){
            rechatge.putExtra("cardType",  "至尊卡");
        }else if (cardLevel.contains("战疫卡")){
            rechatge.putExtra("cardType",  "战疫卡");
        }else if (cardLevel.contains("集团99看过瘾")){
            rechatge.putExtra("cardType",  "集团99看过瘾");
        }else {
            rechatge.putExtra("cardType",  "会员卡");
        }
//        Log.d("支付宝充值", "固定金额 " + cardcode);
        startActivity(rechatge);
    }

    @OnClick({R.id.my_balance, R.id.my_mingxi,R.id.unbindCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_balance:
                Intent rechatg = new Intent(accountbalance.this, detailed.class);
                rechatg.putExtra("mingxi", "1");
                rechatg.putExtra("cardcode", cardcode);
                startActivity(rechatg);
                break;
            case R.id.my_mingxi:
                Intent rechatge = new Intent(accountbalance.this, detailed.class);
                rechatge.putExtra("mingxi","2");
                rechatge.putExtra("cardcode", cardcode);
                startActivity(rechatge);
                break;
            case R.id.unbindCard:
                showDialog();
                break;
        }
    }

    private void showDialog(){
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.dialog_order_pay, null);//这里必须是final的
        TextView cancle = (TextView) view.findViewById(R.id.off_commit);
        TextView commit = (TextView) view.findViewById(R.id.commit);
        TextView title = view.findViewById(R.id.title);
        TextView message = view.findViewById(R.id.message);
        cancle.setText("取消");
        commit.setText("确定");
        title.setText("提示");
        message.setText("是否确认解绑？");
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //事件
                dialog.dismiss();
                showProgress("解绑中...");
                unBindCard(cardBO.getId());
            }
        });
        dialog.setView(view);
        dialog.show();
    }
    private void unBindCard(String id){
        HttpInterfaceIml.unBindCard(id).subscribe(new Subscriber<BaseBO>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                stopProgress();
                ToastUtils.showShortToast(e.getMessage());
            }

            @Override
            public void onNext(BaseBO baseBO) {
                stopProgress();
                if (baseBO.getStatus() == 1){
                    ToastUtils.showShortToast("解绑成功");
                    finish();
                }
            }
        });

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
                    cardRules.setText(s);
                }else {
                    cardRules.setVisibility(View.GONE);
                }
            }
        });
    }

}

