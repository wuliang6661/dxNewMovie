package com.myp.cinema.ui.pay.cardpay;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.config.LocalConfiguration;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.ConfirmPayBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.SubmitPrdectOrderBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.prodectorder.ProdectOrderSuccess;
import com.myp.cinema.ui.rechatge.rechatge;
import com.myp.cinema.ui.membercard.AddCardActiivty;
import com.myp.cinema.ui.orderconfrim.OrderSurcessActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.MD5;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 会员卡支付页面
 */

public class CardPayFragment extends MVPBaseFragment<CardPayContract.View, CardPayPresenter>
        implements CardPayContract.View, View.OnClickListener, AdapterView.OnItemClickListener {

    @Bind(R.id.add_card)
    Button addCard;
    @Bind(R.id.no_card_layout)
    LinearLayout noCardLayout;
    @Bind(R.id.recyle)
    ListView recyle;
    @Bind(R.id.add_card_list)
    Button addCardList;
    @Bind(R.id.card_layout)
    LinearLayout cardLayout;


    List<CardBO> cardBOs;
    CommonAdapter<CardBO> adapter;
    ConfirmPayBO orderBO;    //订单信息
    PayCardBO payCardBO;    //支付信息
    CardBO cardBO;    //选中支付的会员卡
    private SubmitPrdectOrderBO submitPrdectOrderBO;
    private String cardcode;
    private String cardPrice;
    private String cardNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_pay_card, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addCard.setOnClickListener(this);
        addCardList.setOnClickListener(this);

        mPresenter.loadCardUser();
        recyle.setOnItemClickListener(this);
    }


    private void setAdapter() {
        adapter = new CommonAdapter<CardBO>(getActivity(), R.layout.item_card_pay, cardBOs) {

            @Override
            protected void convert(ViewHolder helper, CardBO item, int position) {
                CheckBox box = helper.getView(R.id.card_check);
                if (position == mposition) {
                    box.setChecked(true);
                } else {
                    box.setChecked(false);
                }
                helper.setText(R.id.card_price, "¥ " + item.getBalance());
                helper.setText(R.id.card_num, item.getCardNumber());
            }

            int mposition = Integer.MAX_VALUE;

            public void setCheck(int position) {
                this.mposition = position;
            }

        };
        recyle.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_card_list:
            case R.id.add_card:
                Intent intent = new Intent(getActivity(), AddCardActiivty.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 1:
                mPresenter.loadCardUser();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRequestError(String msg) {
        if(msg.equals("会员卡余额不足")){
            showDialog2(msg);
        }else {
            LogUtils.showToast(msg);
        }
        stopProgress();
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
    }

    @Override
    public void getCardList(List<CardBO> cardBOs) {
        if (cardBOs.size() > 0) {
            noCardLayout.setVisibility(View.GONE);
            cardLayout.setVisibility(View.VISIBLE);
        } else {
            noCardLayout.setVisibility(View.VISIBLE);
            cardLayout.setVisibility(View.GONE);
        }
        this.cardBOs = cardBOs;
        setAdapter();
    }

    @Override
    public void getCardPrice(PayCardBO payCardBO) {
        this.payCardBO = payCardBO;
        showDialog();
    }

    @Override
    public void getPayCard(ResuBo pay) {
        if(pay.getResultX()==1){
            LogUtils.showToast("支付成功");
            if (orderBO != null) {
                Intent intent = new Intent(getActivity(),OrderSurcessActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("order", orderBO.getOrderNum());
                intent.putExtras(bundle);     //将bundle传入intent中。
                startActivity(intent);
            }else {
                Intent intent = new Intent(getActivity(),ProdectOrderSuccess.class);
                Bundle bundle = new Bundle();
                bundle.putString("order", submitPrdectOrderBO.getOrderNum());
                intent.putExtras(bundle);     //将bundle传入intent中。
                startActivity(intent);
            }
        }else {

        }

    }
    private void showDialog2(String getMessage) {
        LayoutInflater factory = LayoutInflater.from(getActivity());//提示框
        View dia = factory.inflate(R.layout.dialog2_card_pay, null);//这里必须是final的
        TextView cancle = (TextView) dia.findViewById(R.id.pay_cancle);
        TextView commit = (TextView) dia.findViewById(R.id.pay_yes);
        TextView texttt = (TextView) dia.findViewById(R.id.texttt);
        dialog = new AlertDialog.Builder(getActivity(), R.style.AlertDialog).create();
        texttt.setText(getMessage);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rechatge = new Intent(getActivity(), rechatge.class);
                LocalConfiguration.isShouye = "1";
                rechatge.putExtra("cardPrice", cardPrice);
                rechatge.putExtra("cardcode",  cardNumber);
                startActivity(rechatge);
            }
        });
        dialog.setView(dia);
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Double.parseDouble(payCardBO.getTotalPrice()) == 0 ){
            ToastUtils.showShortToast("0元支付不能使用会员卡");
            return;
        }
        showProgress("加载中...");
        this.cardBO = cardBOs.get(position);
        cardPrice = cardBOs.get(position).getBalance();
        cardNumber=cardBOs.get(position).getCardNumber();
        if (orderBO != null) {
            cardcode = orderBO.getOrderNum();
            mPresenter.loadCardPay(orderBO.getOrderNum(), cardBOs.get(position).getCardNumber());
        }else {
            cardcode = submitPrdectOrderBO.getOrderNum();
            mPresenter.loadCardPay(submitPrdectOrderBO.getOrderNum(), cardBOs.get(position).getCardNumber());
        }

    }


    /**
     * 电影票订单
     * @param orderBO
     */
    public void setOrderBO(ConfirmPayBO orderBO) {
        this.orderBO = orderBO;
    }

    /**
     * 食品订单
     * @param submitPrdectOrderBO
     */
    public void setProdectBO(SubmitPrdectOrderBO submitPrdectOrderBO){
        this.submitPrdectOrderBO = submitPrdectOrderBO;
    }

    AlertDialog dialog;

    /**
     * 显示支付弹窗
     */
    private void showDialog() {
        LayoutInflater factory = LayoutInflater.from(getActivity());//提示框
        View dia = factory.inflate(R.layout.dialog_card_pay, null);//这里必须是final的
        Button cancle = (Button) dia.findViewById(R.id.pay_cancle);
        Button commit = (Button) dia.findViewById(R.id.pay_yes);
        TextView price = (TextView) dia.findViewById(R.id.order_price);
        TextView poundagePrice = (TextView) dia.findViewById(R.id.poundage_price);
        final EditText passWord = (EditText) dia.findViewById(R.id.edit_password);
        price.setText("¥ " + payCardBO.getTotalPrice());
        poundagePrice.setText("¥ " + payCardBO.getTotalFee());
        dialog = new AlertDialog.Builder(getActivity(), R.style.AlertDialog).create();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = passWord.getText().toString().trim();
                if (StringUtils.isEmpty(pwd)) {
                    LogUtils.showToast("请输入密码！");
                } else {
                    dialog.dismiss();
                    showProgress("付款中...");
                    if (orderBO != null) {
                        mPresenter.payCard(orderBO.getOrderNum(), MD5.strToMd5Low32(pwd), cardBO.getCardNumber());
                    }else {
                        mPresenter.payCard(submitPrdectOrderBO.getOrderNum(), MD5.strToMd5Low32(pwd), cardBO.getCardNumber());
                    }
                }
            }
        });
        dialog.setView(dia);
        dialog.show();
    }

    /**
     * 如果订单已过期，关闭弹窗
     */
    public void dissDiaLog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
