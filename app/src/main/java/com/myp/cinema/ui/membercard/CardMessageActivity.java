package com.myp.cinema.ui.membercard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.ui.accountbalance.accountbalance;

import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;

/**会员卡详情
 * Created by Administrator on 2018/3/2.
 */
public class CardMessageActivity extends BaseActivity {
    @Bind(R.id.card_num)
    TextView cardNum;
    @Bind(R.id.card_price)
    TextView cardPrice;
    @Bind(R.id.card_jifen)
    TextView cardJifen;
    @Bind(R.id.card_juanNum)
    TextView cardJuanNum;
    @Bind(R.id.card_qrCode)
    RelativeLayout cardQrCode;
    @Bind(R.id.my_balance)
    RelativeLayout mybalance;
    @Bind(R.id.rlCard)
    RelativeLayout rlCard;
    private CardBO cardBO;

    @Override
    protected int getLayout() {
        return R.layout.fra_card_message;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("会员卡");
        cardBO = (CardBO) getIntent().getExtras().getSerializable("cardBO");
        Log.d("data.get(position)", " " + cardBO.getBalance());
        listener();
        setData();
    }

    private void listener() {
        mybalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigDecimal bd = new BigDecimal(cardBO.getBalance());
                String code = bd.toPlainString();
                Intent intent = new Intent(CardMessageActivity.this, accountbalance.class);
                intent.putExtra("cardPrice", code);
                intent.putExtra("getCardNumber", cardBO.getCardNumber());
                intent.putExtra("cardLevel",cardBO.getCardLevel());
                startActivity(intent);
            }
        });
    }

    //    /**
//     * 设置页面数据显示
//     */
    private void setData() {
        BigDecimal bd = new BigDecimal(cardBO.getBalance());
        String code = bd.toPlainString();
        cardNum.setText("NO." + cardBO.getCardNumber());
        cardPrice.setText("¥ " + code);
        cardJifen.setText(cardBO.getAvailableJifen());
        if (cardBO.getCardLevel().contains("至尊卡")){
            rlCard.setBackground(getResources().getDrawable(R.drawable.kapian_perfect));
        }else if (cardBO.getCardLevel().contains("战疫卡")){
            rlCard.setBackground(getResources().getDrawable(R.drawable.war_card));
        }else if (cardBO.getCardLevel().contains("集团99看过瘾")){
            rlCard.setBackground(getResources().getDrawable(R.drawable.jiu_card));
        }else {
            rlCard.setBackground(getResources().getDrawable(R.drawable.kapian));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
